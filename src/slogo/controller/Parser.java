package slogo.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;
import slogo.controller.commands.ListNodeTail;

/**
 * Parser is the meat-and-potatoes of the SLogo Control layer. This class takes in a String of SLogo
 * code, breaks it up by whitespace, and then utilizes several other classes to build an AST, and
 * then pass it to a TurtleController for consumption.
 *
 * @author Marc Chmielewski
 */
public class Parser {

  private final TurtleController controller;
  private final Lexer lexer;
  private Queue<String> splitText;
  private Queue<Token> tokenizedText;
  private final Queue<Node> parsedNodeQueue;
  private final Stack<ListNode> pendingCompleteList;


  public Parser(TurtleController controller, String syntaxLang) {
    this.controller = controller;
    this.lexer = new Lexer(syntaxLang);
    this.splitText = new LinkedList<>();
    this.tokenizedText = new LinkedList<>();
    this.parsedNodeQueue = new LinkedList<>();
    this.pendingCompleteList = new Stack<>();
  }

  private void splitText(String text) {
    splitText = new LinkedList<>(Arrays.asList(text.split("\\s+")));
  }

  private void tokenizeText() throws IllegalArgumentException {
    Queue<Token> tokenList = new LinkedList<>();
    for (String curString : splitText) {
      try {
        tokenList.add(lexer.tokenize(curString));
      } catch (IllegalArgumentException e) {
        throw e;
      }
    }
    this.tokenizedText = tokenList;
  }

  private void mapTokensToNodes() {
    while (!tokenizedText.isEmpty()) {
      parsedNodeQueue.add(patternMatchToken(tokenizedText.poll(), splitText.poll()));
    }
  }

  private Node patternMatchToken(Token token, String text) {
    switch (token) {
      case COMMAND -> {
        String commandType = lexer.lexLangDefinedCommands(text);
        System.out.println(commandType);
        try {
          Class<?> commandClass = Class.forName("slogo.controller.commands."+commandType + "Command");
          return (Node) commandClass.getConstructor().newInstance();
        } catch (Exception e) {
          System.err.println("LOOKUP NO WORK!!!");
          // TODO: Might be a user-defined command, so we must check those!
        }
      }
      case CONSTANT -> {
        return new ConstantNode(Double.parseDouble(text));
      }
      case VARIABLE -> {
        if (!controller.getTurtle().getVars().containsKey(text)) {
          controller.getTurtle().getVars().setValue(text, 0);
        }
        return new VariableNode(text);
      }
      case LIST_START -> {
        // TODO: Create ListStartNode
        ListNodeHead listStartNode = new ListNodeHead();
        listStartNode.setNumParams(0);
        if(tokenizedText.isEmpty() || splitText.isEmpty()){
          //TODO: Throw error
          System.out.println("THIS IS AN ERROR");
        }
        Node innerChild = patternMatchToken(tokenizedText.poll(), splitText.poll());
        fillList(listStartNode, innerChild);
        return listStartNode;
      }
      case LIST_END -> {
        // this case is never called in new implementation
        //System.out.println("IN LIST END: SHOULD NEVER APPEAR");
        // TODO: Create ListEndNode
        Node listEndNode = new ListNodeTail();

        return listEndNode;
      }
    }
    return null;
  }

  private ListNodeHead fillList(ListNodeHead listHead, Node innerNode){
    //Start Base Case
    if(innerNode.getIsListEnd()){
      System.out.println("Recursion Finished");
      return listHead;
    }

    //End Base Case
    System.out.println("Adding Child");
    listHead.addInnerChild(innerNode);

    grandChildHandler(innerNode);

    if(tokenizedText.isEmpty()){
      //TODO: Create IllegalCommandException to throw
      return null;
    }

    Node nextChild = patternMatchToken(tokenizedText.poll(), splitText.poll());

    fillList(listHead, nextChild);

    //should never reach here
    //TODO: create IllegalCommandException
    return null;
  }

  private void grandChildHandler(Node innerNode){
    int numInnerGrandChildren = innerNode.getNumParams();

    for(int i = 0; i < numInnerGrandChildren; i++) {
      System.out.println("Adding GrandChild");
      Node grandChild = patternMatchToken(tokenizedText.poll(), splitText.poll());
      innerNode.addChild(grandChild);
      if(grandChild.getNumParams() > 0){
        grandChildHandler(grandChild);
      }
    }
  }

  private boolean handleCommentsAndBlankLines() {
    return (tokenizedText.contains(Token.COMMENT) || tokenizedText.isEmpty());
  }

  private Queue<Node> assembleCommandQueue() {
    Queue<Node> assembledNodeQueue = new LinkedList<>();
    while(!parsedNodeQueue.isEmpty()) {
      Stack<Node> pendingFilledArgs = new Stack<>();
      //Node rootNode = listCompatiblePoll();
      Node rootNode = parsedNodeQueue.poll();
      Node curNode = rootNode;
      //System.out.println("Pre-dfsHelper");
      dfsHelper(pendingFilledArgs, curNode);
      //System.out.println("Post-dfsHelper");
      while(!pendingFilledArgs.isEmpty()) {
        curNode = pendingFilledArgs.pop();
        dfsHelper(pendingFilledArgs, curNode);
      }
      assembledNodeQueue.add(rootNode);
    }
    return assembledNodeQueue;
  }


  private void dfsHelper(Stack<Node> pendingFilledArgs, Node curNode) {
    while (curNode.getNumParams() > curNode.getChildren().size() && !parsedNodeQueue.isEmpty()) {
      //Node childNode = listCompatiblePoll();
      Node childNode = parsedNodeQueue.poll();

      //System.out.println("Adding Child: " + childNode);

      curNode.addChild(childNode);
      if (childNode.getNumParams() > 0 && curNode.getNumParams() > curNode.getChildren().size()) {
        pendingFilledArgs.push(curNode);
        curNode = childNode;
      }
      else if (childNode.getNumParams() > 0) {
        curNode = childNode;
      }
    }
  }

//  private Node listCompatiblePoll() {
//    if(!parsedNodeQueue.isEmpty()) {
//
//      Node polledNode = parsedNodeQueue.poll();
//      if(polledNode.getListNodeType() == ListNodeType.START_LIST) {
//        pendingCompleteList.push((ListNode) polledNode);
//        ((ListNode) polledNode).addNodesToList(assembleCommandQueue());
//      }
//      else if(polledNode.getListNodeType() == ListNodeType.END_LIST) {
//        if(!pendingCompleteList.isEmpty()) {
//          pendingCompleteList.pop(); // Pop off the list we're completing
//        }
//      }
//      return polledNode;
//    }
//    return null;
//  }

  public void setSyntaxLang(String syntaxLang) {
    lexer.setLangSymbols(syntaxLang);
  }

  public void parseCommandString(String text) {
    splitText(text);
    tokenizeText();
    if (handleCommentsAndBlankLines()) {
      return; // If it's a comment line, return early. Comments have no commands.
    }
    mapTokensToNodes();
    //System.out.println("Pre-AssembleCommandQueue");
    Queue<Node> assembledNodeQueue = assembleCommandQueue();
    //System.out.println("Post-AssembleCommandQueue");
    for(Node node : assembledNodeQueue) {
      System.out.println(node);
    }
    controller.pushNodes(assembledNodeQueue);
    assembledNodeQueue.clear();
    // Clean up after we're done
  }

  public Consumer<String> receiveInputAction() {
    return command -> {
      parseCommandString(command);
      controller.setIsAllowedToExecute(true);
      controller.runCommands();
    };
  }
}

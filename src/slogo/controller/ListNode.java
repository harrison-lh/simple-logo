package slogo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import slogo.model.Turtle;

/**
 * ListNode is a concrete class that can hold a number of different Nodes and Node trees in a List.
 * This will be particularly useful for user-defined Commands and for other looping constructs.
 *
 * @author Marc Chmielewski
 * @author Harrison Huang
 */
public class ListNode extends Node {

  Collection<Node> nodeList;

  /**
   * Constructs a new ListNode with an empty List of Nodes.
   */
  public ListNode(ListNodeType type) {
    //setListNodeType(type);
    nodeList = new ArrayList<>();
  }

  /**
   * Constructs a new ListNode with a List of Nodes
   * @param nodeList The List of Nodes with which to construct the ListNode
   */
  public ListNode(ListNodeType type, List<Node> nodeList) {
    //setListNodeType(type);
    this.nodeList = nodeList;
  }

  /**
   * The List of Nodes that is contained within this ListNode
   *
   * @return The List of Nodes contained within this ListNode
   */
  public List<Node> getNodeList() {
    return (List<Node>) nodeList;
  }

  /**
   * Appends the passed Node to the end of this ListNode's nodeList.
   *
   * @param node The Node to append ot the nodeList
   */
  public void addNodeToList(Node node) {
    nodeList.add(node);
  }

  public void addNodesToList(Collection<Node> nodes) {
    nodeList.addAll(nodes);
  }

  /**
   * If this ListNode's nodeList contains the nodeToRemove, remove the first instance of it.
   *
   * @param nodeToRemove The Node to remove from the nodeList.
   *
   * @return Whether or not the operation successfully removed the first instance of the Node.
   */
  public boolean removeNodeFromList(Node nodeToRemove) {
    return nodeList.remove(nodeToRemove);
  }

  /**
   * Executes all Nodes in the list. Returns the value of the last command run.
   * @param turtle The turtle to be commanded
   * @return The value of the last command run, or 0 if none are run.
   */
  @Override
  public double execute(Turtle turtle) {
    double lastVal = 0;
    for (Node node : nodeList) {
      lastVal = node.execute(turtle);
    }
    return lastVal;
  }


}

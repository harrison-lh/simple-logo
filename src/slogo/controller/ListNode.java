package slogo.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * ListNode is a concrete class that can hold a number of different Nodes and Node trees in a List.
 * This will be particularly useful for user-defined Commands and for other looping constructs.
 *
 * @author Marc Chmielewski
 * @author Harrison Huang
 */
public class ListNode extends Node {

  List<Node> nodeList;

  public ListNode() {
    nodeList = new ArrayList<>();
  }

  public ListNode(List<Node> nodeList) {
    this.nodeList = nodeList;
  }

  public List<Node> getNodeList() {
    return nodeList;
  }

  public void addNodeToList(Node node) {
    nodeList.add(node);
  }

  public void removeNodeFromList(Node nodeToRemove) {
    if(nodeList.contains(nodeToRemove)) {
      nodeList.remove(nodeToRemove);
    }
  }
}

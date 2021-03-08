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

  /**
   * Constructs a new ListNode with an empty List of Nodes.
   */
  public ListNode() {
    nodeList = new ArrayList<>();
  }

  /**
   * Constructs a new ListNode with a List of Nodes
   * @param nodeList The List of Nodes with which to construct the ListNode
   */
  public ListNode(List<Node> nodeList) {
    this.nodeList = nodeList;
  }

  /**
   * The List of Nodes that is contained within this ListNode
   *
   * @return The List of Nodes contained within this ListNode
   */
  public List<Node> getNodeList() {
    return nodeList;
  }

  /**
   * Appends the passed Node to the end of this ListNode's nodeList.
   *
   * @param node The Node to append ot the nodeList
   */
  public void addNodeToList(Node node) {
    nodeList.add(node);
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
}

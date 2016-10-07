package edu.nyu.cs.pqs.bst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * Creates graph and allow users to use BFS or DFS to traverse the graph.
 * Provides different types of predicate for filtering data.
 * 
 * @author Li-Hao Liu <lhl302@nyu.edu>
 */

public class Graph {

  private List<Node> nodes = new ArrayList<Node>();

  public class Node {

    public Object data;
    public boolean visited = false;
    public List<Node> adjacentNodes = new LinkedList<Node>();

    public Node(Object data) {
      if (data == null)
        throw new IllegalArgumentException("Node data cannot be empty.");

      this.data = data;
    }

    /**
     * Puts adjacent node into adjacent list.
     * If the node has no adjacent, do not add null node in adjacent list.
     * 
     * @param node Adjacent node
     */
    public void addAdjacentNode(final Node node) {
      if (node == null)
        throw new IllegalArgumentException(
            "Cannot add null node as adjacent node.");
      adjacentNodes.add(node);
      node.adjacentNodes.add(this);
    }
    
  }

  public List<Node> getNodes() {
    return nodes;
  }

  /**
   * Implements BFS iterator for traversing the graph.
   */
  public class BFSIterator implements Iterator<Node> {
    Queue<Node> queue = new LinkedList<Node>();

    public BFSIterator(Node rootNode) {
      queue.add(rootNode);
      rootNode.visited = true;
    }

    @Override
    public boolean hasNext() {
      return !queue.isEmpty();
    }

    // Based on node to search its adjacent nodes and put it in queue.
    @Override
    public Node next() {
      if (!hasNext())
        throw new NoSuchElementException();
      Node n = (Node) queue.poll();
      for (Node adj : n.adjacentNodes) {
        if (!adj.visited) {
          adj.visited = true;
          queue.add(adj);
        }
      }
      return n;
    }

    @Override
    public void remove() {
      queue.remove();
    }
  }

  /**
   * Implements DFS iterator for traversing the graph.
   */
  public class DFSIterator implements Iterator<Node> {
    Stack<Node> stack = new Stack<Node>();

    public DFSIterator(Node rootNode) {
      stack.add(rootNode);
      rootNode.visited = true;
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    // Based on node to search its adjacent nodes and put it in stack.
    @Override
    public Node next() {
      if (!hasNext())
        throw new NoSuchElementException();
      Node n = stack.pop();
      for (Node adj : n.adjacentNodes) {
        if (!adj.visited) {
          adj.visited = true;
          stack.push(adj);
        }
      }
      return n;
    }

    @Override
    public void remove() {
      stack.pop();
    }

  }
}

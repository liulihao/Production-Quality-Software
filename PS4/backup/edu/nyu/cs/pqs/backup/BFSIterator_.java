package edu.nyu.cs.pqs.backup;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import edu.nyu.cs.pqs.bst.Graph;
import edu.nyu.cs.pqs.bst.Graph.Node;

public class BFSIterator_ implements Iterator<Node> {
  Queue<Node> q = new LinkedList<Node>();

  public BFSIterator_(Node rootNode) {
    q.add(rootNode);
    rootNode.visited = true;
  }

  @Override
  public boolean hasNext() {
    return !q.isEmpty();
  }

  @Override
  public Node next() {
    if (!hasNext())
      throw new NoSuchElementException();
    Node n = (Node) q.poll();
    for (Node adj : n.adjacentNodes) {
      if (!adj.visited) {
        adj.visited = true;
        q.add(adj);
      }
    }
    return n;
  }

  @Override
  public void remove() {
    q.remove();
  }
}

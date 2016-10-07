package edu.nyu.cs.pqs.backup;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import edu.nyu.cs.pqs.bst.Graph;
import edu.nyu.cs.pqs.bst.Graph.Node;

public class DFSIterator_ implements Iterator<Node>{
  Stack<Node> s = new Stack<Node>();
  
  public DFSIterator_(Node rootNode) {
    s.add(rootNode);
    rootNode.visited = true;
    while(!s.isEmpty()){
      Node n = s.pop();
      System.out.print(n.data +  " ");
      for(Node adj : n.adjacentNodes){
        if(!adj.visited){
          adj.visited = true;
          s.push(adj);
        }
      }
    }
  }

  @Override
  public boolean hasNext() {
    return !s.isEmpty();
  }

  @Override
  public Node next() {
    if (!hasNext())
      throw new NoSuchElementException();
    Node n = s.pop();
    for(Node adj : n.adjacentNodes){
      if(!adj.visited){
        adj.visited = true;
        s.push(adj);
      }
    }
    return n;
  }

  @Override
  public void remove() {
    s.pop();
    
  }
  
}

package edu.nyu.cs.pqs.backup;


import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

public class DFSIterator__<T> implements Iterator<T> {
  private Set<T> visited = new HashSet<>();
  private Stack<Iterator<T>> stack = new Stack<>();
  private Graph__<T> graph;
  private T nextElement;

  public DFSIterator__(Graph__<T> g, T v) {
    if (g.isVertexExist(v)) {
      this.stack.push(g.getAdjacent(v).iterator());
      this.graph = g;
      this.nextElement = v;
    } else {
      throw new IllegalArgumentException("Vertext does not exits");
    }
  }

  @Override
  public boolean hasNext() {
    return this.nextElement != null;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    try {
      this.visited.add(this.nextElement);
      return this.nextElement;
    } finally {
      findAdjacent();
    }
  }

  private void findAdjacent() {
    Iterator<T> adjacents = this.stack.peek();
    do {
      while (!adjacents.hasNext()) {
        this.stack.pop();
        if (this.stack.isEmpty()) {
          this.nextElement = null;
          return;
        }
        adjacents = this.stack.peek();
      }
      this.nextElement = adjacents.next();
    } while (this.visited.contains(this.nextElement));
    this.stack.push(graph.getAdjacent(this.nextElement).iterator());
  }

  @Override
  public void remove() {
    this.stack.pop();
  }
  
  public static void main(String[] args) {
    Graph__<Integer> graph = new Graph__<>();
    graph.addEdge(1,2);
    graph.addEdge(1,3);
    graph.addEdge(2,4);
    graph.addEdge(4,1);
    graph.addEdge(5,null);


    DFSIterator__<Integer> dfs = new DFSIterator__<>(graph,1);
    while (dfs.hasNext()){
        System.out.println(dfs.next());
    }
}

}

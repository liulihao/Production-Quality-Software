package edu.nyu.cs.pqs.backup;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

public class BFSIterator__<T> implements Iterator<T> {
  private Set<T> visited = new HashSet<>();
  private Queue<T> queue = new LinkedList<>();
  private Graph__<T> graph;

  public BFSIterator__(Graph__<T> g, T startingVertex) {
    if (g.isVertexExist(startingVertex)) {
      graph = g;
      queue.add(startingVertex);
      visited.add(startingVertex);
    } else {
      throw new IllegalArgumentException("Vertice does not exits");
    }
  }

  @Override
  public void remove() {
    queue.remove();
  }

  @Override
  public boolean hasNext() {
    return !queue.isEmpty();
  }

  @Override
  public T next() {
    if (!hasNext())
      throw new NoSuchElementException();
    T next = queue.remove();
    for (T adjacent : graph.getAdjacent(next)) {
      if (!visited.contains(adjacent)) {
        queue.add(adjacent);
        visited.add(adjacent);
      }
    }
    return next;
  }
}
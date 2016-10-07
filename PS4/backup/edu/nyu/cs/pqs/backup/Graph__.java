package edu.nyu.cs.pqs.backup;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph__<T> {

  private Map<T, Set<T>> map;

  public Graph__() {
    map = new HashMap<>();
  }

  public class Edge {
    int source, destination;
    String info;

    public Edge(int v, int w, String s) {
      source = v;
      destination = w;
      info = s;
    }
  }

  public class Node {
    List<Edge> AdjacencyList = new ArrayList<Edge>();
    Object value;

    public Node(Object obj) {
      this.value = obj;
    }
  }
  
  public void addNode(Node n){
    
  }

  public Graph__<T> addEdge(T v, T w) {
    if (v != null) {
      if (v == w || v.equals(w)) {
        throw new IllegalArgumentException(
            "Source and Destination can not be same");
      } else {
        Set<T> desitinations = map.get(v);
        if (desitinations == null) {
          desitinations = new HashSet<>();
        }
        if (w != null) {
          desitinations.add(w);
          Set<T> destinationsOfDestination = map.get(w);
          if (destinationsOfDestination == null) {
            map.put(w, new HashSet<T>());
          }
        }
        map.put(v, desitinations);
        System.out.println(map);
      }
    } else {
      throw new IllegalArgumentException("Invalid Source node");
    }
    return this;
  }

  /**
   * thread-safe set
   * 
   * @param v
   * @return
   */
  public Iterable<T> getAdjacent(T v) {
    final Set<T> adjacencies = this.map.get(v);
    if (adjacencies == null || adjacencies.isEmpty()) {
      return Collections.emptySet();
    } else {
      return Collections.synchronizedSet(adjacencies);
    }
  }

  public boolean isVertexExist(T v) {
    return map.containsKey(v);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Graph{");
    sb.append("map=").append(map);
    sb.append('}');
    return sb.toString();
  }
}
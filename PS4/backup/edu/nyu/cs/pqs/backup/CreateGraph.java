package edu.nyu.cs.pqs.backup;


import java.util.ArrayList;
import java.util.List;

public class CreateGraph {
  class Edge {
    int source, destination, weight;

    Edge(int s, int d, int w) {
      source = s;
      destination = d;
      weight = w;
    }
  }

  class Node {
    List<Edge> AdjacencyList = new ArrayList<Edge>();
  }

  class Graph {
    Node list[];
    int vertices;

    Graph(int v) {
      vertices = v;
      list = new Node[v];
      for (int i = 0; i < v; i++)
        list[i] = new Node();
    }

    public void addEdge(int s, int d, int w) {
      Edge e1 = new Edge(s, d, w);
      list[s].AdjacencyList.add(e1);
      // If it were an undirected graph add the
      // edge to source as well as destination
      // node
    }

    public void bfs() {
      bfs(0);
    }

    private void bfs(int s) {
      boolean visited[] = new boolean[vertices];
      visited[s] = true;
      ArrayList<Integer> queue = new ArrayList<Integer>();
      while (!queue.isEmpty()) {
        int next = queue.remove(0);
        System.out.println("Visited " + next + "node");
        for (int i = 0; i < list[next].AdjacencyList.size(); i++) {
          Edge e1 = list[next].AdjacencyList.get(i);
          if (!visited[e1.destination]) {
            visited[e1.destination] = true;
            queue.add(e1.destination);
          }
        }
      }
    }
  }
}

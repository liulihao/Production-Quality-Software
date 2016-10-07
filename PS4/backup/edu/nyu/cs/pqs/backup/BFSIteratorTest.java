package edu.nyu.cs.pqs.backup;


public class BFSIteratorTest {
  public static void main(String[] args) {
    Graph__<String> graph = new Graph__<>();
    
    //graph.addNode();
    graph.addEdge("a","b");
    graph.addEdge("a","c");
    graph.addEdge("b","d");
    graph.addEdge("b","e");
    graph.addEdge("e","h");
    graph.addEdge("c","f");
    graph.addEdge("c","g");

   BFSIterator__<String> bfs = new BFSIterator__<>(graph,"a");
    while (bfs.hasNext()){
        System.out.println(bfs.next());
    }
}
}

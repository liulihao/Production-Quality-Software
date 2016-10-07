package edu.nyu.cs.pqs.backup;


public class DFSIteratorTest {
  public static void main(String[] args) {
    Graph__<String> graph = new Graph__<>();
    
    graph.addEdge("a","b");
    graph.addEdge("a","c");
    graph.addEdge("b","d");
    graph.addEdge("b","e");
    graph.addEdge("e","h");
    graph.addEdge("c","f");
    graph.addEdge("c","g");


    DFSIterator__<String> dfs1 = new DFSIterator__<String>(graph,"a");
    DFSIterator__<String> dfs2 = new DFSIterator__<String>(graph,"a");
    while (dfs1.hasNext()){
        System.out.println(dfs1.next());
        while (dfs2.hasNext()){
          //System.out.println(dfs2.next());
          
      }
    }
}
}

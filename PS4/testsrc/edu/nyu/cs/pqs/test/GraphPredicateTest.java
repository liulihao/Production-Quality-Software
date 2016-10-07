package edu.nyu.cs.pqs.test;

import edu.nyu.cs.pqs.bst.Graph;
import edu.nyu.cs.pqs.bst.Graph.BFSIterator;
import edu.nyu.cs.pqs.bst.Graph.DFSIterator;
import edu.nyu.cs.pqs.bst.Graph.Node;
import edu.nyu.cs.pqs.bst.Predicate;
import edu.nyu.cs.pqs.bst.PredicateFactory;
import edu.nyu.cs.pqs.bst.PredicateIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test class for Graph and Predicate.
 * Code coverage : 94.1%
 * 
 * @author Li-Hao Liu <lhl302@nyu.edu>
 */

public class GraphPredicateTest {

  Graph g = new Graph();
  List<Citizen> list = new ArrayList<Citizen>();
  Iterator<Citizen> iterator;
  PredicateFactory<Citizen> predicateFactory = new PredicateFactory<Citizen>();
  Predicate<Citizen> elderlyCitizenPredicate = new ElderlyCitizenPredicate();
  Predicate<Citizen> nycCitizenPredicate = new NYCCitizenPredicate();
  Predicate<Citizen> tallCitizenPredicate = new TallCitizenPredicate();
  Predicate<Citizen> thinCitizenPredicate = new ThinCitizenPredicate();

  @Test(expected = IllegalArgumentException.class)
  public void testGraphNullAdjacent() {
    Node A = g.new Node("A");
    g.getNodes().add(A);
    A.addAdjacentNode(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGraphNullNode() {
    Node A = g.new Node("A");
    g.getNodes().add(A);
    Node B = g.new Node(null);
    g.getNodes().add(B);
  }

  @Test
  public void testBFSUndirectedGraph() {
    Node A = g.new Node("A");
    Node B = g.new Node("B");
    Node C = g.new Node("C");
    Node D = g.new Node("D");
    Node E = g.new Node("E");
    Node F = g.new Node("F");
    Node G = g.new Node("G");

    g.getNodes().add(A);
    g.getNodes().add(B);
    g.getNodes().add(C);
    g.getNodes().add(D);
    g.getNodes().add(E);
    g.getNodes().add(F);
    g.getNodes().add(G);

    A.addAdjacentNode(B);
    A.addAdjacentNode(C);
    B.addAdjacentNode(D);
    B.addAdjacentNode(E);
    C.addAdjacentNode(F);
    C.addAdjacentNode(G);

    Graph.BFSIterator bfs = g.new BFSIterator(A);

    assertEquals("A", bfs.next().data);
    assertEquals("B", bfs.next().data);
    assertEquals("C", bfs.next().data);
    assertEquals("D", bfs.next().data);
    assertEquals("E", bfs.next().data);
    assertEquals("F", bfs.next().data);
    assertEquals("G", bfs.next().data);
  }

  @Test
  public void testBFSDirectedGraph() {
    Node A = g.new Node("A");
    Node B = g.new Node("B");
    Node C = g.new Node("C");
    Node D = g.new Node("D");
    Node E = g.new Node("E");
    Node F = g.new Node("F");
    Node G = g.new Node("G");

    g.getNodes().add(A);
    g.getNodes().add(B);
    g.getNodes().add(C);
    g.getNodes().add(D);
    g.getNodes().add(E);
    g.getNodes().add(F);
    g.getNodes().add(G);

    A.addAdjacentNode(B);
    A.addAdjacentNode(C);
    B.addAdjacentNode(A);
    B.addAdjacentNode(D);
    B.addAdjacentNode(E);
    C.addAdjacentNode(A);
    C.addAdjacentNode(F);
    C.addAdjacentNode(G);
    D.addAdjacentNode(B);
    E.addAdjacentNode(B);
    F.addAdjacentNode(C);
    G.addAdjacentNode(C);

    Graph.BFSIterator bfs = g.new BFSIterator(A);

    assertEquals("A", bfs.next().data);
    assertEquals("B", bfs.next().data);
    assertEquals("C", bfs.next().data);
    assertEquals("D", bfs.next().data);
    assertEquals("E", bfs.next().data);
    assertEquals("F", bfs.next().data);
    assertEquals("G", bfs.next().data);
  }

  @Test(expected = NoSuchElementException.class)
  public void testBFSNoSuchElement() {
    Node A = g.new Node("A");
    Node B = g.new Node("B");

    g.getNodes().add(A);
    g.getNodes().add(B);

    Graph.BFSIterator bfs = g.new BFSIterator(A);

    bfs.next();
    bfs.next();
  }

  @Test
  public void testBFSPredicate() {

    Citizen testCitizen1 = new Citizen("Alex", 20, 170, 90, "Manhattan");
    Citizen testCitizen2 = new Citizen("Ben", 80, 180, 80, "Queens");
    Citizen testCitizen3 = new Citizen("Cathy", 70, 175, 55, "Bronx");
    Citizen testCitizen4 = new Citizen("David", 18, 190, 100, "Brooklyn");

    Node testNode1 = g.new Node(testCitizen1);
    Node testNode2 = g.new Node(testCitizen2);
    Node testNode3 = g.new Node(testCitizen3);
    Node testNode4 = g.new Node(testCitizen4);

    g.getNodes().add(testNode1);
    g.getNodes().add(testNode2);
    g.getNodes().add(testNode3);
    g.getNodes().add(testNode4);

    testNode1.addAdjacentNode(testNode2);
    testNode1.addAdjacentNode(testNode3);
    testNode3.addAdjacentNode(testNode4);

    BFSIterator bfs = g.new BFSIterator(testNode1);

    while (bfs.hasNext()) {
      list.add((Citizen) bfs.next().data);
    }
    iterator = list.iterator();
    iterator = new PredicateIterator<Citizen>(iterator,
        elderlyCitizenPredicate);
    Citizen expected = new Citizen("Ben", 80, 180, 80, "Queens");
    assertEquals(expected, iterator.next());
    expected = new Citizen("Cathy", 70, 175, 55, "Bronx");
    assertEquals(expected, iterator.next());
  }

  @Test
  public void testBFSOrPredicate() {

    Citizen testCitizen1 = new Citizen("Alex", 20, 170, 90, "Manhattan");
    Citizen testCitizen2 = new Citizen("Ben", 80, 180, 80, "Queens");
    Citizen testCitizen3 = new Citizen("Cathy", 70, 175, 55, "Bronx");
    Citizen testCitizen4 = new Citizen("David", 18, 190, 100, "Brooklyn");

    Node testNode1 = g.new Node(testCitizen1);
    Node testNode2 = g.new Node(testCitizen2);
    Node testNode3 = g.new Node(testCitizen3);
    Node testNode4 = g.new Node(testCitizen4);

    g.getNodes().add(testNode1);
    g.getNodes().add(testNode2);
    g.getNodes().add(testNode3);
    g.getNodes().add(testNode4);

    testNode1.addAdjacentNode(testNode2);
    testNode1.addAdjacentNode(testNode3);
    testNode3.addAdjacentNode(testNode4);

    BFSIterator bfs = g.new BFSIterator(testNode1);

    while (bfs.hasNext()) {
      list.add((Citizen) bfs.next().data);
    }
    iterator = list.iterator();

    Predicate<Citizen> orPredicate = predicateFactory
        .or(elderlyCitizenPredicate, nycCitizenPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, orPredicate);

    Citizen expected = new Citizen("Alex", 20, 170, 90, "Manhattan");
    assertEquals(expected, iterator.next());
    expected = new Citizen("Ben", 80, 180, 80, "Queens");
    assertEquals(expected, iterator.next());
    expected = new Citizen("Cathy", 70, 175, 55, "Bronx");
    assertEquals(expected, iterator.next());
  }

  @Test(expected = NoSuchElementException.class)
  public void testBFSAndPredicate() {

    Citizen testCitizen1 = new Citizen("Alex", 20, 170, 90, "Manhattan");
    Citizen testCitizen2 = new Citizen("Ben", 80, 180, 80, "Queens");
    Citizen testCitizen3 = new Citizen("Cathy", 70, 175, 55, "Bronx");
    Citizen testCitizen4 = new Citizen("David", 18, 190, 100, "Brooklyn");

    Node testNode1 = g.new Node(testCitizen1);
    Node testNode2 = g.new Node(testCitizen2);
    Node testNode3 = g.new Node(testCitizen3);
    Node testNode4 = g.new Node(testCitizen4);

    g.getNodes().add(testNode1);
    g.getNodes().add(testNode2);
    g.getNodes().add(testNode3);
    g.getNodes().add(testNode4);

    testNode1.addAdjacentNode(testNode2);
    testNode1.addAdjacentNode(testNode3);
    testNode3.addAdjacentNode(testNode4);

    BFSIterator bfs = g.new BFSIterator(testNode1);

    while (bfs.hasNext()) {
      list.add((Citizen) bfs.next().data);
    }

    iterator = list.iterator();
    Predicate<Citizen> andPredicate = predicateFactory
        .and(elderlyCitizenPredicate, nycCitizenPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, andPredicate);
    iterator.next();
  }

  @Test
  public void testBFSNotPredicate() {

    Citizen testCitizen1 = new Citizen("Alex", 20, 170, 90, "Manhattan");
    Citizen testCitizen2 = new Citizen("Ben", 80, 180, 80, "Queens");
    Citizen testCitizen3 = new Citizen("Cathy", 70, 175, 55, "Bronx");
    Citizen testCitizen4 = new Citizen("David", 18, 190, 100, "Brooklyn");

    Node testNode1 = g.new Node(testCitizen1);
    Node testNode2 = g.new Node(testCitizen2);
    Node testNode3 = g.new Node(testCitizen3);
    Node testNode4 = g.new Node(testCitizen4);

    g.getNodes().add(testNode1);
    g.getNodes().add(testNode2);
    g.getNodes().add(testNode3);
    g.getNodes().add(testNode4);

    testNode1.addAdjacentNode(testNode2);
    testNode1.addAdjacentNode(testNode3);
    testNode3.addAdjacentNode(testNode4);

    BFSIterator bfs = g.new BFSIterator(testNode1);

    while (bfs.hasNext()) {
      list.add((Citizen) bfs.next().data);
    }

    iterator = list.iterator();
    Predicate<Citizen> notPredicate = predicateFactory
        .not(elderlyCitizenPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, notPredicate);

    Citizen expected = new Citizen("Alex", 20, 170, 90, "Manhattan");
    assertEquals(expected, iterator.next());
    expected = new Citizen("David", 18, 190, 100, "Brooklyn");
    assertEquals(expected, iterator.next());
  }

  @Test
  public void testDFSUndirected() {
    Node A = g.new Node("A");
    Node B = g.new Node("B");
    Node C = g.new Node("C");
    Node D = g.new Node("D");
    Node E = g.new Node("E");
    Node F = g.new Node("F");
    Node G = g.new Node("G");

    g.getNodes().add(A);
    g.getNodes().add(B);
    g.getNodes().add(C);
    g.getNodes().add(D);
    g.getNodes().add(E);
    g.getNodes().add(F);
    g.getNodes().add(G);

    C.addAdjacentNode(G);
    C.addAdjacentNode(F);
    B.addAdjacentNode(E);
    B.addAdjacentNode(D);
    A.addAdjacentNode(C);
    A.addAdjacentNode(B);

    Graph.DFSIterator dfs = g.new DFSIterator(A);

    assertEquals("A", dfs.next().data);
    assertEquals("B", dfs.next().data);
    assertEquals("D", dfs.next().data);
    assertEquals("E", dfs.next().data);
    assertEquals("C", dfs.next().data);
    assertEquals("F", dfs.next().data);
    assertEquals("G", dfs.next().data);
  }

  @Test
  public void testDFSDirected() {
    Node A = g.new Node("A");
    Node B = g.new Node("B");
    Node C = g.new Node("C");
    Node D = g.new Node("D");
    Node E = g.new Node("E");
    Node F = g.new Node("F");
    Node G = g.new Node("G");

    g.getNodes().add(A);
    g.getNodes().add(B);
    g.getNodes().add(C);
    g.getNodes().add(D);
    g.getNodes().add(E);
    g.getNodes().add(F);
    g.getNodes().add(G);

    G.addAdjacentNode(C);
    F.addAdjacentNode(C);
    E.addAdjacentNode(B);
    D.addAdjacentNode(B);
    C.addAdjacentNode(G);
    C.addAdjacentNode(F);
    C.addAdjacentNode(A);
    B.addAdjacentNode(E);
    B.addAdjacentNode(D);
    B.addAdjacentNode(A);
    A.addAdjacentNode(C);
    A.addAdjacentNode(B);

    Graph.DFSIterator dfs = g.new DFSIterator(A);

    assertEquals("A", dfs.next().data);
    assertEquals("B", dfs.next().data);
    assertEquals("D", dfs.next().data);
    assertEquals("E", dfs.next().data);
    assertEquals("C", dfs.next().data);
    assertEquals("F", dfs.next().data);
    assertEquals("G", dfs.next().data);
  }

  @Test(expected = NoSuchElementException.class)
  public void testDFSNoSuchElement() {
    Node A = g.new Node("A");
    Node B = g.new Node("B");

    g.getNodes().add(A);
    g.getNodes().add(B);

    Graph.DFSIterator dfs = g.new DFSIterator(A);

    dfs.next();
    dfs.next();
  }

  @Test
  public void testDFSPredicate() {

    Citizen testCitizen1 = new Citizen("Alex", 20, 170, 90, "Manhattan");
    Citizen testCitizen2 = new Citizen("Ben", 80, 180, 80, "Queens");
    Citizen testCitizen3 = new Citizen("Cathy", 70, 175, 55, "Bronx");
    Citizen testCitizen4 = new Citizen("David", 18, 190, 100, "Brooklyn");

    Node testNode1 = g.new Node(testCitizen1);
    Node testNode2 = g.new Node(testCitizen2);
    Node testNode3 = g.new Node(testCitizen3);
    Node testNode4 = g.new Node(testCitizen4);

    g.getNodes().add(testNode1);
    g.getNodes().add(testNode2);
    g.getNodes().add(testNode3);
    g.getNodes().add(testNode4);

    testNode1.addAdjacentNode(testNode2);
    testNode1.addAdjacentNode(testNode3);
    testNode3.addAdjacentNode(testNode4);

    DFSIterator dfs = g.new DFSIterator(testNode1);
    Iterator<Citizen> iterator;
    while (dfs.hasNext()) {
      list.add((Citizen) dfs.next().data);
    }
    iterator = list.iterator();
    iterator = new PredicateIterator<Citizen>(iterator,
        new ElderlyCitizenPredicate());
    Citizen expected = new Citizen("Cathy", 70, 175, 55, "Bronx");
    assertEquals(expected, iterator.next());
    expected = new Citizen("Ben", 80, 180, 80, "Queens");
    assertEquals(expected, iterator.next());
  }

  @Test
  public void testDFSOrPredicate() {

    Citizen testCitizen1 = new Citizen("Alex", 20, 170, 90, "Manhattan");
    Citizen testCitizen2 = new Citizen("Ben", 80, 180, 80, "Queens");
    Citizen testCitizen3 = new Citizen("Cathy", 70, 175, 55, "Bronx");
    Citizen testCitizen4 = new Citizen("David", 18, 190, 100, "Brooklyn");

    Node testNode1 = g.new Node(testCitizen1);
    Node testNode2 = g.new Node(testCitizen2);
    Node testNode3 = g.new Node(testCitizen3);
    Node testNode4 = g.new Node(testCitizen4);

    g.getNodes().add(testNode1);
    g.getNodes().add(testNode2);
    g.getNodes().add(testNode3);
    g.getNodes().add(testNode4);

    testNode1.addAdjacentNode(testNode2);
    testNode1.addAdjacentNode(testNode3);
    testNode3.addAdjacentNode(testNode4);

    DFSIterator dfs = g.new DFSIterator(testNode1);

    while (dfs.hasNext()) {
      list.add((Citizen) dfs.next().data);
    }

    iterator = list.iterator();
    Predicate<Citizen> orPredicate = predicateFactory
        .or(elderlyCitizenPredicate, nycCitizenPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, orPredicate);

    Citizen expected = new Citizen("Alex", 20, 170, 90, "Manhattan");
    assertEquals(expected, iterator.next());
    expected = new Citizen("Cathy", 70, 175, 55, "Bronx");
    assertEquals(expected, iterator.next());
    expected = new Citizen("Ben", 80, 180, 80, "Queens");
    assertEquals(expected, iterator.next());
  }

  @Test
  public void testDFSAndPredicate() {

    Citizen testCitizen1 = new Citizen("Alex", 20, 170, 90, "Manhattan");
    Citizen testCitizen2 = new Citizen("Ben", 80, 180, 40, "Queens");
    Citizen testCitizen3 = new Citizen("Cathy", 70, 175, 55, "Bronx");
    Citizen testCitizen4 = new Citizen("David", 18, 185, 55, "Brooklyn");

    Node testNode1 = g.new Node(testCitizen1);
    Node testNode2 = g.new Node(testCitizen2);
    Node testNode3 = g.new Node(testCitizen3);
    Node testNode4 = g.new Node(testCitizen4);

    g.getNodes().add(testNode1);
    g.getNodes().add(testNode2);
    g.getNodes().add(testNode3);
    g.getNodes().add(testNode4);

    testNode1.addAdjacentNode(testNode2);
    testNode1.addAdjacentNode(testNode3);
    testNode3.addAdjacentNode(testNode4);

    DFSIterator dfs = g.new DFSIterator(testNode1);

    while (dfs.hasNext()) {
      list.add((Citizen) dfs.next().data);
    }

    iterator = list.iterator();
    Predicate<Citizen> andPredicate = predicateFactory
        .and(tallCitizenPredicate, thinCitizenPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, andPredicate);

    Citizen expected = new Citizen("David", 18, 185, 55, "Brooklyn");
    assertEquals(expected, iterator.next());
    expected = new Citizen("Ben", 80, 180, 40, "Queens");
    assertEquals(expected, iterator.next());
  }

  @Test
  public void testDFSNotPredicate() {

    Citizen testCitizen1 = new Citizen("Alex", 20, 170, 90, "Manhattan");
    Citizen testCitizen2 = new Citizen("Ben", 80, 180, 80, "Queens");
    Citizen testCitizen3 = new Citizen("Cathy", 70, 175, 55, "Bronx");
    Citizen testCitizen4 = new Citizen("David", 18, 190, 100, "Brooklyn");

    Node testNode1 = g.new Node(testCitizen1);
    Node testNode2 = g.new Node(testCitizen2);
    Node testNode3 = g.new Node(testCitizen3);
    Node testNode4 = g.new Node(testCitizen4);

    g.getNodes().add(testNode1);
    g.getNodes().add(testNode2);
    g.getNodes().add(testNode3);
    g.getNodes().add(testNode4);

    testNode1.addAdjacentNode(testNode2);
    testNode1.addAdjacentNode(testNode3);
    testNode3.addAdjacentNode(testNode4);

    DFSIterator dfs = g.new DFSIterator(testNode1);

    while (dfs.hasNext()) {
      list.add((Citizen) dfs.next().data);
    }

    iterator = list.iterator();
    Predicate<Citizen> notPredicate = predicateFactory
        .not(elderlyCitizenPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, notPredicate);

    Citizen expected = new Citizen("Alex", 20, 170, 90, "Manhattan");
    assertEquals(expected, iterator.next());
    expected = new Citizen("David", 18, 190, 100, "Brooklyn");
    assertEquals(expected, iterator.next());
  }

  @After
  public void cleanUp() {
    list.clear();
    iterator = null;
  }

}

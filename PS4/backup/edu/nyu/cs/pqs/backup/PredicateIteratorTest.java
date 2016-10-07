package edu.nyu.cs.pqs.backup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.nyu.cs.pqs.bst.Predicate;
import edu.nyu.cs.pqs.bst.PredicateFactory;
import edu.nyu.cs.pqs.bst.PredicateIterator;
import edu.nyu.cs.pqs.test.Citizen;
import edu.nyu.cs.pqs.test.ElderlyCitizenPredicate;
import edu.nyu.cs.pqs.test.NYCCitizenPredicate;
import edu.nyu.cs.pqs.test.TallCitizenPredicate;
import edu.nyu.cs.pqs.test.ThinCitizenPredicate;

public class PredicateIteratorTest {

  List<Citizen> list = new ArrayList<Citizen>();
  Iterator<Citizen> iterator;
  PredicateFactory<Citizen> PredicateFactory = new PredicateFactory<Citizen>();
  Predicate<Citizen> twentyOnePredicate = new ElderlyCitizenPredicate();
  Predicate<Citizen> kevinPredicate = new NYCCitizenPredicate();
  Predicate<Citizen> tallPredicate = new TallCitizenPredicate();
  Predicate<Citizen> thinPredicate = new ThinCitizenPredicate();

  @Before
  public void setup() {
    list.add(new Citizen("Jacky Chen", 15, 7.1, 180, "New York"));
    list.add(new Citizen("Kevin Smith", 16, 8, 98, "New York"));
    list.add(new Citizen("Jimmy Jordan", 17, 5.5, 120, "New York"));
    list.add(new Citizen("Kobe Bryant", 87, 6.5, 130, "New York"));
    list.add(new Citizen("Kevin Obama", 44, 6.8, 188, "New York"));
    list.add(new Citizen("Alex James", 9, 3.9, 200, "New York"));
    list.add(new Citizen("James Bond", 28, 4.5, 143, "New York"));
    list.add(new Citizen("Monkey D Lufy", 65, 7.1, 132, "New York"));
    list.add(new Citizen("Kevin", 36, 7.6, 199, "New York"));
    iterator = list.iterator();
  }

  @After
  public void cleanUp() {
    list.clear();
    iterator = null;
  }

  @Test
  public void testOnePredicate() {
    iterator = new PredicateIterator<Citizen>(iterator,
        new ElderlyCitizenPredicate());
    Citizen expect = new Citizen("Kobe Bryant", 87, 6.5, 130, "New York");
    assertEquals(expect, iterator.next());
    expect = new Citizen("Kevin Obama", 44, 6.8, 188, "New York");
    assertEquals(expect, iterator.next());
  }

  @Test
  public void testNotPredicate() {
    Predicate<Citizen> Predicate = PredicateFactory.not(twentyOnePredicate);
    iterator = new PredicateIterator<Citizen>(iterator, Predicate);
    Citizen expect = new Citizen("Jacky Chen", 15, 7.1, 180, "New York");
    assertEquals(expect, iterator.next());
    expect = new Citizen("Kevin Smith", 16, 8, 98, "New York");
    assertEquals(expect, iterator.next());
    iterator.next();
    iterator.next();
    assertFalse(iterator.hasNext());
  }

  @Test
  public void testAndPredicate() {
    Predicate<Citizen> Predicate = PredicateFactory.and(twentyOnePredicate,
        tallPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, Predicate);
    Citizen expect = new Citizen("Kobe Bryant", 87, 6.5, 130, "New York");
    assertEquals(expect, iterator.next());
    expect = new Citizen("Kevin Obama", 44, 6.8, 188, "New York");
    assertEquals(expect, iterator.next());
    assertTrue(iterator.hasNext());
  }

  @Test
  public void testOrPredicate() {
    Predicate<Citizen> Predicate = PredicateFactory.or(kevinPredicate,
        thinPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, Predicate);
    Citizen expect = new Citizen("Kevin Smith", 16, 8, 98, "New York");
    assertEquals(expect, iterator.next());
    expect = new Citizen("Jimmy Jordan", 17, 5.5, 120, "New York");
    assertEquals(expect, iterator.next());
    expect = new Citizen("Kobe Bryant", 87, 6.5, 130, "New York");
    assertEquals(expect, iterator.next());
    expect = new Citizen("Kevin Obama", 44, 6.8, 188, "New York");
    assertEquals(expect, iterator.next());
    expect = new Citizen("James Bond", 28, 4.5, 143, "New York");
    assertEquals(expect, iterator.next());
    assertTrue(iterator.hasNext());
  }

  @Test
  public void testUnsupportedOperationException() {
    Predicate<Citizen> Predicate = PredicateFactory.or(kevinPredicate,
        thinPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, Predicate);
    iterator.next();
    iterator.remove();
  }

  @Test(expected = NoSuchElementException.class)
  public void testNoSuchElementException() {
    Predicate<Citizen> Predicate = PredicateFactory.and(kevinPredicate,
        thinPredicate);
    iterator = new PredicateIterator<Citizen>(iterator, Predicate);
    iterator.next();
    iterator.next();
  }

  @Test
  public void testRandomPredicate() {
    Predicate<Citizen> Predicate = PredicateFactory.or(kevinPredicate,
        thinPredicate);
    Predicate = PredicateFactory.not(Predicate);
    iterator = new PredicateIterator<Citizen>(iterator, Predicate);
    Citizen expect = new Citizen("Jacky Chen", 15, 7.1, 180, "New York");
    assertEquals(expect, iterator.next());
    expect = new Citizen("Alex James", 9, 3.9, 200, "New York");
    assertEquals(expect, iterator.next());
    assertFalse(iterator.hasNext());
  }

}

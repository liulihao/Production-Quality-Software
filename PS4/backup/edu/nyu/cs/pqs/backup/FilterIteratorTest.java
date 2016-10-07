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

import edu.nyu.cs.pqs.bst.PredicateIterator;
import edu.nyu.cs.pqs.test.ElderlyCitizenPredicate;
import edu.nyu.cs.pqs.test.NYCCitizenPredicate;
import edu.nyu.cs.pqs.test.TallCitizenPredicate;
import edu.nyu.cs.pqs.test.ThinCitizenPredicate;
import edu.nyu.cs.pqs.bst.Predicate;
import edu.nyu.cs.pqs.bst.PredicateFactory;

public class FilterIteratorTest {

	List<Customer> list = new ArrayList<Customer>();
	Iterator<Customer> iterator;
	PredicateFactory<Customer> PredicateFactory = new PredicateFactory<Customer>();
	Predicate<Customer> twentyOnePredicate = new ElderlyCitizenPredicate();
	Predicate<Customer> kevinPredicate = new NYCCitizenPredicate();
	Predicate<Customer> tallPredicate = new TallCitizenPredicate();
	Predicate<Customer> thinPredicate = new ThinCitizenPredicate();

	@Before
	public void setup() {
		list.add(new Customer("Jacky Chen", "0123", 15, 7.1, 180));
		list.add(new Customer("Kevin Smith", "0345", 16, 8, 98));
		list.add(new Customer("Jimmy Jordan", "2354", 17, 5.5, 120));
		list.add(new Customer("Kobe Bryant", "6562", 87, 6.5, 130));
		list.add(new Customer("Kevin Obama", "6547", 44, 6.8, 188));
		list.add(new Customer("Alex James", "9786", 9, 3.9, 200));
		list.add(new Customer("James Bond", "2345", 28, 4.5, 143));
		list.add(new Customer("Monkey D Lufy", "1111", 65, 7.1, 132));
		list.add(new Customer("Kevin", "5345", 36, 7.6, 199));
		iterator = list.iterator();
	}

	@After
	public void cleanUp() {
		list.clear();
		iterator = null;
	}

	@Test
	public void testOnePredicate() {
		iterator = new PredicateIterator<Customer>(iterator, new ElderlyCitizenPredicate());
		Customer expect = new Customer("Kobe Bryant", "6562", 87, 6.5, 130);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kevin Obama", "6547", 44, 6.8, 188);
		assertEquals(expect, iterator.next());
	}

	@Test
	public void testNotPredicate() {
		Predicate<Customer> Predicate = PredicateFactory.not(twentyOnePredicate);
		iterator = new PredicateIterator<Customer>(iterator, Predicate);
		Customer expect = new Customer("Jacky Chen", "0123", 15, 7.1, 180);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kevin Smith", "0345", 16, 8, 98);
		assertEquals(expect, iterator.next());
		iterator.next();
		iterator.next();
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testAndPredicate() {
		Predicate<Customer> Predicate = PredicateFactory.and(twentyOnePredicate,
				tallPredicate);
		iterator = new PredicateIterator<Customer>(iterator, Predicate);
		Customer expect = new Customer("Kobe Bryant", "6562", 87, 6.5, 130);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kevin Obama", "6547", 44, 6.8, 188);
		assertEquals(expect, iterator.next());
		assertTrue(iterator.hasNext());
	}

	@Test
	public void testOrPredicate() {
		Predicate<Customer> Predicate = PredicateFactory.or(kevinPredicate,
				thinPredicate);
		iterator = new PredicateIterator<Customer>(iterator, Predicate);
		Customer expect = new Customer("Kevin Smith", "0345", 16, 8, 98);
		assertEquals(expect, iterator.next());
		expect = new Customer("Jimmy Jordan", "2354", 17, 5.5, 120);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kobe Bryant", "6562", 87, 6.5, 130);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kevin Obama", "6547", 44, 6.8, 188);
		assertEquals(expect, iterator.next());
		expect = new Customer("James Bond", "2345", 28, 4.5, 143);
		assertEquals(expect, iterator.next());
		assertTrue(iterator.hasNext());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testUnsupportedOperationException(){
		Predicate<Customer> Predicate = PredicateFactory.or(kevinPredicate,
				thinPredicate);
		iterator = new PredicateIterator<Customer>(iterator, Predicate);
		iterator.next();
		iterator.remove();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testNoSuchElementException(){
		Predicate<Customer> Predicate = PredicateFactory.and(kevinPredicate,
				thinPredicate);
		iterator = new PredicateIterator<Customer>(iterator, Predicate);
		iterator.next();
		iterator.next();
	}
	
	@Test
	public void testRandomPredicate() {
		Predicate<Customer> Predicate = PredicateFactory.or(kevinPredicate,
				thinPredicate);
		Predicate = PredicateFactory.not(Predicate);
		iterator = new PredicateIterator<Customer>(iterator, Predicate);
		Customer expect = new Customer("Jacky Chen", "0123", 15, 7.1, 180);
		assertEquals(expect, iterator.next());
		expect = new Customer("Alex James", "9786", 9, 3.9, 200);
		assertEquals(expect, iterator.next());
		assertFalse(iterator.hasNext());
	}

}

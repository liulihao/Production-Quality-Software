package edu.nyu.cs.pqs.bst;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PredicateIterator<T> implements Iterator<T> {

  private Iterator<T> iterator;
  private Predicate<T> predicate;
  private T nextItem;
  private boolean nextIsValid;

  public PredicateIterator(Iterator<T> iterator, Predicate<T> predicate) {
    this.iterator = iterator;
    this.predicate = predicate;
  }

  @Override
  public boolean hasNext() {
    if (nextIsValid) {
      return true;
    }
    while (iterator.hasNext()) {
      nextItem = iterator.next();
      if (predicate.accept(nextItem)) {
        nextIsValid = true;
        return true;
      }
    }
    return false;
  }

  @Override
  public T next() {
    if (nextIsValid) {
      nextIsValid = false;
      T val = nextItem;
      nextItem = null;
      return val;
    }
    if (hasNext()) {
      return next();
    }
    throw new NoSuchElementException();
  }

  @Override
  public void remove() {
    iterator.remove();
  }

}
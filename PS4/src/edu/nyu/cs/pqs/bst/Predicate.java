package edu.nyu.cs.pqs.bst;

public interface Predicate<T> {
  boolean accept(T item);
}

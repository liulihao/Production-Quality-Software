package edu.nyu.cs.pqs.bst;

public class OrPredicate<T> implements Predicate<T> {
  Predicate<T> first;
  Predicate<T> second;

  public OrPredicate(Predicate<T> first, Predicate<T> second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public boolean accept(T item) {
    return first.accept(item) || second.accept(item);
  }
}

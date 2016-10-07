package edu.nyu.cs.pqs.bst;

public class NotPredicate<T> implements Predicate<T> {
  Predicate<T> predicate;

  public NotPredicate(Predicate<T> predicate) {
    this.predicate = predicate;
  }

  @Override
  public boolean accept(T item) {
    return !predicate.accept(item);
  }
}

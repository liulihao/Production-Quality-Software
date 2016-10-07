package edu.nyu.cs.pqs.bst;

public class AndPredicate<T> implements Predicate<T> {
  Predicate<T> first;
  Predicate<T> second;

  /**
   * @param first predicate
   * @param second predicate
   */
  public AndPredicate(Predicate<T> first, Predicate<T> second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public boolean accept(T item) {
    return first.accept(item) && second.accept(item);
  }
}

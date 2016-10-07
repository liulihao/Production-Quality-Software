package edu.nyu.cs.pqs.bst;

/**
 * Creates different types of predicate for filtering data.
 * 
 * @author Li-Hao Liu <lhl302@nyu.edu>
 */

public class PredicateFactory<T> {
  /**
   * @param first predicate
   * @param second predicate
   * @return new predicate with first and second predicate
   */
  public AndPredicate<T> and(Predicate<T> first, Predicate<T> second) {
    return new AndPredicate<T>(first, second);
  }

  /**
   * @param first predicate
   * @param second predicate
   * @return new predicate with first or second predicate
   */
  public OrPredicate<T> or(Predicate<T> first, Predicate<T> second) {
    return new OrPredicate<T>(first, second);
  }

  /**
   * @param predicate
   * @return new predicate that is opposite to predicate
   */
  public NotPredicate<T> not(Predicate<T> predicate) {
    return new NotPredicate<T>(predicate);
  }
}

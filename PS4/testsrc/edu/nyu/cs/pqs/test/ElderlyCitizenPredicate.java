package edu.nyu.cs.pqs.test;

import edu.nyu.cs.pqs.bst.Predicate;

public class ElderlyCitizenPredicate implements Predicate<Citizen> {

  @Override
  public boolean accept(Citizen item) {
    if (item != null && item.getAge() >= 65) {
      return true;
    }
    return false;
  }

}

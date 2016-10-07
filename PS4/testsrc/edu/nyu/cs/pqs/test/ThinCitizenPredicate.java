package edu.nyu.cs.pqs.test;

import edu.nyu.cs.pqs.bst.Predicate;

public class ThinCitizenPredicate implements Predicate<Citizen> {

  @Override
  public boolean accept(Citizen item) {
    if (item != null && item.getWeight() < 60) {
      return true;
    }
    return false;
  }

}

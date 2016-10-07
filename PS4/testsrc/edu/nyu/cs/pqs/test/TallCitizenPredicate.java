package edu.nyu.cs.pqs.test;

import edu.nyu.cs.pqs.bst.Predicate;

public class TallCitizenPredicate implements Predicate<Citizen> {

  @Override
  public boolean accept(Citizen item) {
    if (item != null && item.getHeight() >= 180) {
      return true;
    }
    return false;
  }

}

package edu.nyu.cs.pqs.test;

import edu.nyu.cs.pqs.bst.Predicate;

public class NYCCitizenPredicate implements Predicate<Citizen> {

  @Override
  public boolean accept(Citizen item) {
    if (item != null && item.getResident().equals("Manhattan")) {
      return true;
    }
    return false;
  }

}

package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for Stopwatch objects.
 * It maintains references to all created Stopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {
  private static final List<Stopwatch> stopwatchList = new ArrayList<Stopwatch>();
  private static final Object lock = new Object();

  /**
   * Creates and returns a new Stopwatch object.
   * 
   * @param id The identifier of the new object.
   * @return The new Stopwatch object.
   * @throws IllegalArgumentException
   *           if <code>id</code> is empty, null, or already taken.
   */
  public static Stopwatch getStopwatch(String id) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("ID is empty or null");
    }
    synchronized (lock) {
      Stopwatch stopwatch = new StopwatchImpl(id);
      if (stopwatchList.contains(stopwatch)) {
        throw new IllegalArgumentException("ID is already taken");
      } else {
        stopwatchList.add(stopwatch);
        return stopwatch;
      }
    }
  }

  /**
   * Returns a list of all created stopwatches.
   * 
   * @return a List of all creates Stopwatch objects. Returns an empty
   *         list if no stopwatches have been created.
   */
  public static List<Stopwatch> getStopwatches() {
    return stopwatchList;
  }

}

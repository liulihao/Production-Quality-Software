package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.Stopwatch;
import edu.nyu.pqs.stopwatch.util.StopwatchStatus;

/**
 * StopwatchImpl is a thread-safe class that implements Stopwatch.
 *
 * @author Li-Hao Liu <lhl302@nyu.edu>
 */
public class StopwatchImpl implements Stopwatch {

  private final String id;
  private final List<Long> lapTimes;
  private final Object lock = new Object();
  private StopwatchStatus stopwatchStatus;
  private long startTime = 0;
  private boolean addTime = false;
  private long lastLap = 0;

  /**
   * An instance of the StopwatcImpl can be created by passing the ID.
   * ID cannot be empty or null.
   * 
   * @param id ID of the stopwatch.
   * @throws IllegalArgumentException if id is empty or null.
   */
  public StopwatchImpl(String id) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("ID is empty or null");
    }
    lapTimes = new ArrayList<Long>();
    this.id = id;
    stopwatchStatus = StopwatchStatus.START;
  }

  /**
   * Gets current time in milliseconds.
   * 
   * @return current time.
   */
  private long getTime() {
    return System.currentTimeMillis();
  }

  /**
   * Returns ID of this stopwatch.
   * 
   * @return id ID of this stopwatch. Will never be empty or null.
   */

  public String getId() {
    return id;
  }

  /**
   * Starts the stopwatch.
   * If StopwatchStatus is RUN, then throw IllegalStateException.
   * If StopwatchStatus is START or RESET, then set startTime as system time.
   * If StopwatchStatus is STOP, it means user paused the stopwatch before, so
   * need to delete last lap time.
   * 
   * @throws IllegalStateException thrown when the stopwatch is already running.
   */

  public void start() {
    synchronized (lock) {
      if (stopwatchStatus == StopwatchStatus.RUN) {
        throw new IllegalStateException("Stopwatch is already running");
      } else if (stopwatchStatus == StopwatchStatus.START
          || stopwatchStatus == StopwatchStatus.RESET) {
        //startTime = getTime();
      } else if (stopwatchStatus == StopwatchStatus.STOP) {
        //startTime = getTime();
        addTime = true;
        lastLap = lapTimes.get(lapTimes.size() - 1);
        lapTimes.remove(lapTimes.size() - 1);
      }
      startTime = getTime();
      stopwatchStatus = StopwatchStatus.RUN;
    }
  }

  /**
   * Stores the time elapsed since the last time lap() was called
   * or since start() was called if this is the first lap.
   * 
   * @throws IllegalStateException thrown when the stopwatch isn't running.
   */

  public void lap() {
    synchronized (lock) {
      if (stopwatchStatus != StopwatchStatus.RUN) {
        throw new IllegalStateException("Stopwatch isn't running");
      }
      long lapTime = 0;
      long endTime = getTime();
      if (addTime) {
        lapTime = endTime - startTime + lastLap;
        addTime = false;
      } else {
        lapTime = endTime - startTime;
      }
      startTime = getTime();
      //addTime = false;
      lapTimes.add(lapTime);
    }
  }

  /**
   * Stops the stopwatch (and records one final lap).
   * 
   * @throws IllegalStateException thrown when the stopwatch isn't running.
   */

  public void stop() {
    synchronized (lock) {
      lap();
      stopwatchStatus = StopwatchStatus.STOP;
    }
  }

  /**
   * Resets the stopwatch. If the stopwatch is running, this method stops the
   * watch and resets it. This also clears all recorded laps.
   * If user resets stopwatch while it is still running, call stop() to stop the
   * stopwatch first.
   */

  public void reset() {
    synchronized (lock) {
      if (stopwatchStatus == StopwatchStatus.RUN) {
        stop();
      }
      lapTimes.clear();
      stopwatchStatus = StopwatchStatus.RESET;
    }
  }

  /**
   * Returns a list of lap times (in milliseconds). This method can be called at
   * any time and will not throw an exception.
   * 
   * @return a list of recorded lap times or an empty list.
   */

  public List<Long> getLapTimes() {
    synchronized (lock) {
      return new ArrayList<Long>(lapTimes);
    }
  }

  public String toString() {
    return "ID:" + id + ", Laps:"
        + lapTimes.toString();
  }

  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Stopwatch)) {
      return false;
    }
    Stopwatch watch = (StopwatchImpl) obj;
    return watch.getId().equals(this.getId());
  }

  public int hashCode() {
    int result = 17;
    result = 31 * result + id.hashCode();
    return result;
  }
}

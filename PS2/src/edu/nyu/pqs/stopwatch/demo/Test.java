package edu.nyu.pqs.stopwatch.demo;

import java.util.List;
import java.util.logging.Logger;

import edu.nyu.pqs.stopwatch.api.Stopwatch;
import edu.nyu.pqs.stopwatch.impl.StopwatchFactory;

public class Test {

  private static final Logger logger = Logger
      .getLogger("edu.nyu.pqs.ps4.demo.Test");

  private static void test() {
    Runnable runnable = new Runnable() {
      public void run() {
        Stopwatch sw1 = StopwatchFactory.getStopwatch("1");
        // System.out.println(Thread.currentThread().getId());
        sw1.start();
        for (int i = 0; i < 10; i++) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException ignored) {
          }
          sw1.lap();
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        Stopwatch sw3 = StopwatchFactory.getStopwatch("3");
        sw3.start();

        Stopwatch sw2 = StopwatchFactory.getStopwatch("2");
        sw2.start();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        sw2.stop();
        try {
          Thread.sleep(400);
        } catch (InterruptedException ignored) {
        }
        sw2.start();
        try {
          Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
        sw2.lap();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        sw2.stop();
        sw2.start();
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException ignored) {
//        }
        sw2.stop();
        // stopwatch.reset();
        sw1.stop();

        // String sw4 = null;
        // Stopwatch stopwatch4 = StopwatchFactory.getStopwatch(sw4);

        Stopwatch sw5 = StopwatchFactory.getStopwatch("5");
        sw5.start();
        sw5.lap();
        sw5.lap();
        sw5.stop();
        // sw5.reset();
        sw3.lap();

        List<Long> times = sw1.getLapTimes();
        logger.info(times.toString());
        logger.info(StopwatchFactory.getStopwatches().toString().toString());
      }
    };
    Thread thinkerThread = new Thread(runnable);
    thinkerThread.start();
  }

  public static void main(String[] args) {
    test();
  }
}

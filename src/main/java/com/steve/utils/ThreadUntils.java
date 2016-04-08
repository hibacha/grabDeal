package com.steve.utils;

import java.util.concurrent.TimeUnit;

public class ThreadUntils {

  public static void sleep(TimeUnit unit, Integer timeout) {
    try {
      unit.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

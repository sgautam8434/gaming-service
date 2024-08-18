package com.intuit.gaming_service.utils;

import java.util.Random;
import java.util.UUID;

public class BasicUtils {

  public static Long createRandomID() {

    Random random = new Random();
    return random.nextLong();
  }
}

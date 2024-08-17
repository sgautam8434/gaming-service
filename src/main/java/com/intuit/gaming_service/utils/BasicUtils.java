package com.intuit.gaming_service.utils;

import java.util.UUID;

public class BasicUtils {

  public static Long createRandomUUID() {

    UUID uuid = UUID.randomUUID();
    String uuidString = uuid.toString().replace("-", "");
    String uuidSubstring = uuidString.substring(0, 8);
    return Long.parseLong(uuidSubstring, 8);
  }
}

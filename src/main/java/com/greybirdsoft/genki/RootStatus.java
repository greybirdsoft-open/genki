package com.greybirdsoft.genki;

public enum RootStatus {
  ROOT_GRANT(1), ROOT_DENIED(-1), ROOT_NOT_ANSWERED(0);

  private int value;

  RootStatus(int value) {
    this.value = value;
  }

  public static RootStatus fromInt(int value) {
    switch (value) {
      case -1:
        return ROOT_DENIED;
      case 1:
        return ROOT_GRANT;
      case 0:
        return ROOT_NOT_ANSWERED;
      default:
        throw new IllegalArgumentException("RootAccess value can only be -1, 0 or 1");
    }
  }

  public int getValue() {
    return value;
  }
}

package com.mdimension.jchronic.handlers;

public class HandlerPattern {
  private boolean _optional;
  
  public HandlerPattern(boolean optional) {
    _optional = optional;
  }
  
  public boolean isOptional() {
    return _optional;
  }
}

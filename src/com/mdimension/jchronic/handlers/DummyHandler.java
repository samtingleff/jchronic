package com.mdimension.jchronic.handlers;

import java.util.List;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Token;

public class DummyHandler implements IHandler {
  public Span handle(List<Token> tokens, Options options) {
    return null;
  }
}

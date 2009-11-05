package com.mdimension.jchronic.handlers;

import java.util.List;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Token;

public class ORSRHandler extends ORRHandler {

  public Span handle(List<Token> tokens, Options options) {
    Span outerSpan = Handler.getAnchor(tokens.subList(3, 4), options);
    return handle(tokens.subList(0, 2), outerSpan, options);
  }

}

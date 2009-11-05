package com.mdimension.jchronic.handlers;

import java.util.List;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Token;

public class SRPAHandler extends SRPHandler {

  @Override
  public Span handle(List<Token> tokens, Options options) {
    Span anchorSpan = Handler.getAnchor(tokens.subList(3, tokens.size()), options);
    return super.handle(tokens, anchorSpan, options);
  }

}

package com.mdimension.jchronic.handlers;

import java.util.List;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Token;

public class RHandler implements IHandler {

  public Span handle(List<Token> tokens, Options options) {
    List<Token> ddTokens = Handler.dealiasAndDisambiguateTimes(tokens, options);
    return Handler.getAnchor(ddTokens, options);
  }

}

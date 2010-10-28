package com.mdimension.jchronic.handlers;

import java.util.List;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.repeaters.RepeaterMonthName;
import com.mdimension.jchronic.tags.ScalarDay;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Token;

public class RmnSdHandler extends MDHandler {
  public Span handle(List<Token> tokens, Options options) {
    return handle(tokens.get(0).getTag(RepeaterMonthName.class), tokens.get(1).getTag(ScalarDay.class), tokens.subList(2, tokens.size()), options);
  }
}

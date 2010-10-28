package com.mdimension.jchronic.handlers;

import java.util.Calendar;
import java.util.List;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.tags.ScalarDay;
import com.mdimension.jchronic.tags.ScalarMonth;
import com.mdimension.jchronic.utils.Time;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Token;

public class SmSdHandler implements IHandler {
  public Span handle(List<Token> tokens, Options options) {
    int month = tokens.get(0).getTag(ScalarMonth.class).getType().intValue();
    int day = tokens.get(1).getTag(ScalarDay.class).getType().intValue();
    Calendar start = Time.construct(options.getNow().get(Calendar.YEAR), month, day);
    Calendar end = Time.cloneAndAdd(start, Calendar.DAY_OF_MONTH, 1);
    return new Span(start, end);
  }

}

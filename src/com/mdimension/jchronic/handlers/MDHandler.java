package com.mdimension.jchronic.handlers;

import java.util.Calendar;
import java.util.List;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.repeaters.Repeater;
import com.mdimension.jchronic.tags.Tag;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;
import com.mdimension.jchronic.utils.Token;

public abstract class MDHandler implements IHandler {
  public Span handle(Repeater<?> month, Tag<Integer> day, List<Token> timeTokens, Options options) {
    month.setStart((Calendar) options.getNow().clone());
    Span span = month.thisSpan(options.getContext());
    Calendar dayStart = Time.construct(span.getBeginCalendar().get(Calendar.YEAR), span.getBeginCalendar().get(Calendar.MONTH) + 1, day.getType().intValue());
    return Handler.dayOrTime(dayStart, timeTokens, options);
  }
}

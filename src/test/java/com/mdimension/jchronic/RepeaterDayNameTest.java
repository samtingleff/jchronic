package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterDayName;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;
import com.mdimension.jchronic.utils.Token;

@RunWith(JUnit4.class)
public class RepeaterDayNameTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testMatch() {
    Token token = new Token("saturday");
    RepeaterDayName repeater = RepeaterDayName.scan(token);
    Assert.assertEquals(RepeaterDayName.DayName.SATURDAY, repeater.getType());

    token = new Token("sunday");
    repeater = RepeaterDayName.scan(token);
    Assert.assertEquals(RepeaterDayName.DayName.SUNDAY, repeater.getType());
  }

  public void testNextFuture() {
    Span span;
    
    RepeaterDayName mondays = new RepeaterDayName(RepeaterDayName.DayName.MONDAY);
    mondays.setStart(_now);
    span = mondays.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 21), span.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 22), span.getEndCalendar());

    span = mondays.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 28), span.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 29), span.getEndCalendar());
  }

  public void testNextPast() {
    Span span;
    
    RepeaterDayName mondays = new RepeaterDayName(RepeaterDayName.DayName.MONDAY);
    mondays.setStart(_now);
    span = mondays.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 14), span.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 15), span.getEndCalendar());

    span = mondays.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 7), span.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 8), span.getEndCalendar());
  }
}

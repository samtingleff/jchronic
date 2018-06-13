package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterMonth;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class RepeaterMonthTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testOffset() {
    Span span = new Span(_now, Calendar.SECOND, 60);

    Span offsetSpan;
    offsetSpan = new RepeaterMonth().getOffset(span, 1, Pointer.PointerType.FUTURE);

    Assert.assertEquals(Time.construct(2006, 9, 16, 14), offsetSpan.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 9, 16, 14, 1), offsetSpan.getEndCalendar());

    offsetSpan = new RepeaterMonth().getOffset(span, 1, Pointer.PointerType.PAST);

    Assert.assertEquals(Time.construct(2006, 7, 16, 14), offsetSpan.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 7, 16, 14, 1), offsetSpan.getEndCalendar());
  }
}

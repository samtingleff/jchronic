package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterWeekend;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class RepeaterWeekendTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testNextFuture() {
    RepeaterWeekend weekends = new RepeaterWeekend();
    weekends.setStart(_now);

    Span nextWeekend = weekends.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 19), nextWeekend.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 21), nextWeekend.getEndCalendar());
  }

  @Test
  public void testNextPast() {
    RepeaterWeekend weekends = new RepeaterWeekend();
    weekends.setStart(_now);
    Span lastWeekend = weekends.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 12), lastWeekend.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 14), lastWeekend.getEndCalendar());
  }

  @Test
  public void testThisFuture() {
    RepeaterWeekend weekends = new RepeaterWeekend();
    weekends.setStart(_now);

    Span thisWeekend = weekends.thisSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 19), thisWeekend.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 21), thisWeekend.getEndCalendar());
  }

  @Test
  public void testThisPast() {
    RepeaterWeekend weekends = new RepeaterWeekend();
    weekends.setStart(_now);

    Span thisWeekend = weekends.thisSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 12), thisWeekend.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 14), thisWeekend.getEndCalendar());
  }

  @Test
  public void testThisNone() {
    RepeaterWeekend weekends = new RepeaterWeekend();
    weekends.setStart(_now);

    Span thisWeekend = weekends.thisSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 19), thisWeekend.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 21), thisWeekend.getEndCalendar());
  }

  @Test
  public void testOffset() {
    Span span = new Span(_now, Calendar.SECOND, 1);

    Span offsetSpan;

    offsetSpan = new RepeaterWeekend().getOffset(span, 3, Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 9, 2), offsetSpan.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 9, 2, 0, 0, 1), offsetSpan.getEndCalendar());

    offsetSpan = new RepeaterWeekend().getOffset(span, 1, Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 12), offsetSpan.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 12, 0, 0, 1), offsetSpan.getEndCalendar());

    offsetSpan = new RepeaterWeekend().getOffset(span, 0, Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 12), offsetSpan.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 12, 0, 0, 1), offsetSpan.getEndCalendar());
  }
}

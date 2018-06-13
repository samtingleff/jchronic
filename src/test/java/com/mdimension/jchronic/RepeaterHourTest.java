package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterHour;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class RepeaterHourTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testNextFuture() {
    RepeaterHour hours = new RepeaterHour();
    hours.setStart(_now);

    Span nextHour = hours.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 16, 15), nextHour.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16, 16), nextHour.getEndCalendar());

    Span nextNextHour = hours.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 16, 16), nextNextHour.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16, 17), nextNextHour.getEndCalendar());
  }

  @Test
  public void testNextPast() {
    RepeaterHour hours = new RepeaterHour();
    hours.setStart(_now);
    Span lastHour = hours.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 16, 13), lastHour.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16, 14), lastHour.getEndCalendar());

    Span lastLastHour = hours.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 16, 12), lastLastHour.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16, 13), lastLastHour.getEndCalendar());
  }

  @Test
  public void testThis() {
    _now = Time.construct(2006, 8, 16, 14, 30);

    RepeaterHour hours = new RepeaterHour();
    hours.setStart(_now);

    Span thisHour;
    thisHour = hours.thisSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 16, 14, 31), thisHour.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16, 15), thisHour.getEndCalendar());

    thisHour = hours.thisSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 16, 14), thisHour.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16, 14, 30), thisHour.getEndCalendar());
  }

  @Test
  public void testOffset() {
      Span span = new Span(_now, Calendar.SECOND, 1);

      Span offsetSpan;
      offsetSpan = new RepeaterHour().getOffset(span, 3, Pointer.PointerType.FUTURE);

      Assert.assertEquals(Time.construct(2006, 8, 16, 17), offsetSpan.getBeginCalendar());
      Assert.assertEquals(Time.construct(2006, 8, 16, 17, 0, 1), offsetSpan.getEndCalendar());

      offsetSpan = new RepeaterHour().getOffset(span, 24, Pointer.PointerType.PAST);

      Assert.assertEquals(Time.construct(2006, 8, 15, 14), offsetSpan.getBeginCalendar());
      Assert.assertEquals(Time.construct(2006, 8, 15, 14, 0, 1), offsetSpan.getEndCalendar());
  }
}

package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterFortnight;
import com.mdimension.jchronic.repeaters.RepeaterWeek;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class RepeaterFortnightTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testNextFuture() {
    RepeaterFortnight fortnights = new RepeaterFortnight();
    fortnights.setStart(_now);

    Span nextFortnight = fortnights.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 20), nextFortnight.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 9, 3), nextFortnight.getEndCalendar());

    Span nextNextFortnight = fortnights.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 9, 3), nextNextFortnight.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 9, 17), nextNextFortnight.getEndCalendar());
  }

  @Test
  public void testNextPast() {
    RepeaterFortnight fortnights = new RepeaterFortnight();
    fortnights.setStart(_now);
    Span lastFortnight = fortnights.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 7, 30), lastFortnight.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 13), lastFortnight.getEndCalendar());

    Span lastLastFortnight = fortnights.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 7, 16), lastLastFortnight.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 7, 30), lastLastFortnight.getEndCalendar());
  }

  @Test
  public void testThisFuture() {
    RepeaterFortnight fortnights = new RepeaterFortnight();
    fortnights.setStart(_now);

    Span thisFortnight = fortnights.thisSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 16, 15), thisFortnight.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 27), thisFortnight.getEndCalendar());
  }

  @Test
  public void testThisPast() {
    RepeaterFortnight fortnights = new RepeaterFortnight();
    fortnights.setStart(_now);

    Span thisFortnight = fortnights.thisSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 13, 0), thisFortnight.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16, 14), thisFortnight.getEndCalendar());
  }

  @Test
  public void testOffset() {
      Span span = new Span(_now, Calendar.SECOND, 1);

      Span offsetSpan = new RepeaterWeek().getOffset(span, 3, Pointer.PointerType.FUTURE);

      Assert.assertEquals(Time.construct(2006, 9, 6, 14), offsetSpan.getBeginCalendar());
      Assert.assertEquals(Time.construct(2006, 9, 6, 14, 0, 1), offsetSpan.getEndCalendar());
  }
}

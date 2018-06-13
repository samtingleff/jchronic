package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterWeek;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class RepeaterWeekTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testNextFuture() {
    RepeaterWeek weeks = new RepeaterWeek();
    weeks.setStart(_now);

    Span nextWeek = weeks.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 20), nextWeek.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 27), nextWeek.getEndCalendar());

    Span nextNextWeek = weeks.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 27), nextNextWeek.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 9, 3), nextNextWeek.getEndCalendar());
  }

  @Test
  public void testNextPast() {
    RepeaterWeek weeks = new RepeaterWeek();
    weeks.setStart(_now);
    Span lastWeek = weeks.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 6), lastWeek.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 13), lastWeek.getEndCalendar());

    Span lastLastWeek = weeks.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 7, 30), lastLastWeek.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 6), lastLastWeek.getEndCalendar());
  }

  @Test
  public void testThisFuture() {
    RepeaterWeek weeks = new RepeaterWeek();
    weeks.setStart(_now);

    Span thisWeek = weeks.thisSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 16, 15), thisWeek.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 20), thisWeek.getEndCalendar());
  }

  @Test
  public void testThisPast() {
    RepeaterWeek weeks = new RepeaterWeek();
    weeks.setStart(_now);

    Span thisWeek = weeks.thisSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 8, 13, 0), thisWeek.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16, 14), thisWeek.getEndCalendar());
  }

  @Test
  public void testOffset() {
    Span span = new Span(_now, Calendar.SECOND, 1);

    Span offsetSpan = new RepeaterWeek().getOffset(span, 3, Pointer.PointerType.FUTURE);

    Assert.assertEquals(Time.construct(2006, 9, 6, 14), offsetSpan.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 9, 6, 14, 0, 1), offsetSpan.getEndCalendar());
  }
}

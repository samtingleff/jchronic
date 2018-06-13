package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterYear;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class RepeaterYearTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testNextFuture() {
    RepeaterYear years = new RepeaterYear();
    years.setStart(_now);

    Span nextYear = years.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2007, 1, 1), nextYear.getBeginCalendar());
    Assert.assertEquals(Time.construct(2008, 1, 1), nextYear.getEndCalendar());

    Span nextNextYear = years.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2008, 1, 1), nextNextYear.getBeginCalendar());
    Assert.assertEquals(Time.construct(2009, 1, 1), nextNextYear.getEndCalendar());
  }

  @Test
  public void testNextPast() {
    RepeaterYear years = new RepeaterYear();
    years.setStart(_now);
    Span lastYear = years.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2005, 1, 1), lastYear.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 1, 1), lastYear.getEndCalendar());

    Span lastLastYear = years.nextSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2004, 1, 1), lastLastYear.getBeginCalendar());
    Assert.assertEquals(Time.construct(2005, 1, 1), lastLastYear.getEndCalendar());
  }

  @Test
  public void testThis() {
    RepeaterYear years = new RepeaterYear();
    years.setStart(_now);

    Span thisYear;
    thisYear = years.thisSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 8, 17), thisYear.getBeginCalendar());
    Assert.assertEquals(Time.construct(2007, 1, 1), thisYear.getEndCalendar());

    thisYear = years.thisSpan(Pointer.PointerType.PAST);
    Assert.assertEquals(Time.construct(2006, 1, 1), thisYear.getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 16), thisYear.getEndCalendar());
  }

  @Test
  public void testOffset() {
      Span span = new Span(_now, Calendar.SECOND, 1);

      Span offsetSpan;
      offsetSpan = new RepeaterYear().getOffset(span, 3, Pointer.PointerType.FUTURE);

      Assert.assertEquals(Time.construct(2009, 8, 16, 14), offsetSpan.getBeginCalendar());
      Assert.assertEquals(Time.construct(2009, 8, 16, 14, 0, 1), offsetSpan.getEndCalendar());

      offsetSpan = new RepeaterYear().getOffset(span, 10, Pointer.PointerType.PAST);

      Assert.assertEquals(Time.construct(1996, 8, 16, 14), offsetSpan.getBeginCalendar());
      Assert.assertEquals(Time.construct(1996, 8, 16, 14, 0, 1), offsetSpan.getEndCalendar());
  }
}

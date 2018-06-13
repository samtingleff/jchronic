package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterMonthName;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class RepeaterMonthNameTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testNext() {
    RepeaterMonthName mays = new RepeaterMonthName(RepeaterMonthName.MonthName.MAY);
    mays.setStart(_now);
    
    Span nextMay = mays.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2007, 5), nextMay.getBeginCalendar());
    Assert.assertEquals(Time.construct(2007, 6), nextMay.getEndCalendar());

    Span nextNextMay = mays.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2008, 5), nextNextMay.getBeginCalendar());
    Assert.assertEquals(Time.construct(2008, 6), nextNextMay.getEndCalendar());

    RepeaterMonthName decembers = new RepeaterMonthName(RepeaterMonthName.MonthName.DECEMBER);
    decembers.setStart(_now);
    
    Span nextDecember = decembers.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2006, 12), nextDecember.getBeginCalendar());
    Assert.assertEquals(Time.construct(2007, 1), nextDecember.getEndCalendar());

    mays = new RepeaterMonthName(RepeaterMonthName.MonthName.MAY);
    mays.setStart(_now);
    
    Assert.assertEquals(Time.construct(2006, 5), mays.nextSpan(Pointer.PointerType.PAST).getBeginCalendar());
    Assert.assertEquals(Time.construct(2005, 5), mays.nextSpan(Pointer.PointerType.PAST).getBeginCalendar());
  }

  @Test
  public void testThis() {
    RepeaterMonthName octobers = new RepeaterMonthName(RepeaterMonthName.MonthName.MAY);
    octobers.setStart(_now);
    
    Span nextMay = octobers.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2007, 5), nextMay.getBeginCalendar());
    Assert.assertEquals(Time.construct(2007, 6), nextMay.getEndCalendar());

    Span nextNextMay = octobers.nextSpan(Pointer.PointerType.FUTURE);
    Assert.assertEquals(Time.construct(2008, 5), nextNextMay.getBeginCalendar());
    Assert.assertEquals(Time.construct(2008, 6), nextNextMay.getEndCalendar());
  }
}

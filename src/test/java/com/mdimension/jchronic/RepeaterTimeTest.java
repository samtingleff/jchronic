package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.repeaters.RepeaterTime;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class RepeaterTimeTest {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testNextFuture() {
    RepeaterTime t;

    t = new RepeaterTime("4:00");
    t.setStart(_now);

    Assert.assertEquals(Time.construct(2006, 8, 16, 16), t.nextSpan(Pointer.PointerType.FUTURE).getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 17, 4), t.nextSpan(Pointer.PointerType.FUTURE).getBeginCalendar());

    t = new RepeaterTime("13:00");
    t.setStart(_now);

    Assert.assertEquals(Time.construct(2006, 8, 17, 13), t.nextSpan(Pointer.PointerType.FUTURE).getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 18, 13), t.nextSpan(Pointer.PointerType.FUTURE).getBeginCalendar());

    t = new RepeaterTime("0400");
    t.setStart(_now);

    Assert.assertEquals(Time.construct(2006, 8, 17, 4), t.nextSpan(Pointer.PointerType.FUTURE).getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 18, 4), t.nextSpan(Pointer.PointerType.FUTURE).getBeginCalendar());
  }

  @Test
  public void testNextPast() {
    RepeaterTime t;
    t = new RepeaterTime("4:00");
    t.setStart(_now);

    Assert.assertEquals(Time.construct(2006, 8, 16, 4), t.nextSpan(Pointer.PointerType.PAST).getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 15, 16), t.nextSpan(Pointer.PointerType.PAST).getBeginCalendar());

    t = new RepeaterTime("13:00");
    t.setStart(_now);

    Assert.assertEquals(Time.construct(2006, 8, 16, 13), t.nextSpan(Pointer.PointerType.PAST).getBeginCalendar());
    Assert.assertEquals(Time.construct(2006, 8, 15, 13), t.nextSpan(Pointer.PointerType.PAST).getBeginCalendar());
  }

  @Test
  public void testType() {
    RepeaterTime t1;
    t1 = new RepeaterTime("4");
    Assert.assertEquals(14400, t1.getType().intValue());

    t1 = new RepeaterTime("14");
    Assert.assertEquals(50400, t1.getType().intValue());

    t1 = new RepeaterTime("4:00");
    Assert.assertEquals(14400, t1.getType().intValue());

    t1 = new RepeaterTime("4:30");
    Assert.assertEquals(16200, t1.getType().intValue());

    t1 = new RepeaterTime("1400");
    Assert.assertEquals(50400, t1.getType().intValue());

    t1 = new RepeaterTime("0400");
    Assert.assertEquals(14400, t1.getType().intValue());

    t1 = new RepeaterTime("04");
    Assert.assertEquals(14400, t1.getType().intValue());

    t1 = new RepeaterTime("400");
    Assert.assertEquals(14400, t1.getType().intValue());
  }
}

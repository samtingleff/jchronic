package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.utils.Span;
import com.mdimension.jchronic.utils.Time;

@RunWith(JUnit4.class)
public class SpanTestCase {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testSpanWidth() {
    Span span = new Span(Time.construct(2006, 8, 16, 0), Time.construct(2006, 8, 17, 0));
    Assert.assertEquals(60 * 60 * 24, span.getWidth());
  }

  @Test
  public void testSpanMath() {
    Span span = new Span(1, 2);
    Assert.assertEquals(2, span.add(1).getBegin());
    Assert.assertEquals(3, span.add(1).getEnd());
    Assert.assertEquals(0, span.subtract(1).getBegin());
    Assert.assertEquals(1, span.subtract(1).getEnd());
  }
}

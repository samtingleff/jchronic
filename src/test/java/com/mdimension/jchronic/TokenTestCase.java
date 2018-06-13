package com.mdimension.jchronic;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mdimension.jchronic.tags.Scalar;
import com.mdimension.jchronic.tags.StringTag;
import com.mdimension.jchronic.utils.Time;
import com.mdimension.jchronic.utils.Token;

@RunWith(JUnit4.class)
public class TokenTestCase {
  private Calendar _now;

  @Before
  public void setUp() throws Exception {
    _now = Time.construct(2006, 8, 16, 14, 0, 0, 0);
  }

  @Test
  public void testToken() {
    Token token = new Token("foo");
    Assert.assertEquals(0, token.getTags().size());
    Assert.assertFalse(token.isTagged());
    token.tag(new StringTag("mytag"));
    Assert.assertEquals(1, token.getTags().size());
    Assert.assertTrue(token.isTagged());
    Assert.assertEquals(StringTag.class, token.getTag(StringTag.class).getClass());
    token.tag(new Scalar(Integer.valueOf(5)));
    Assert.assertEquals(2, token.getTags().size());
    token.untag(StringTag.class);
    Assert.assertEquals(1, token.getTags().size());
    Assert.assertEquals("foo", token.getWord());
  }
}

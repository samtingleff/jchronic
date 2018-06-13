package com.mdimension.jchronic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	RepeaterMonthNameTest.class,
	RepeaterYearTest.class,
	RepeaterDayNameTest.class,
	ParserTest.class,
	RepeaterTimeTest.class,
	TokenTestCase.class,
	RepeaterMonthTest.class,
	RepeaterWeekTest.class,
	ChronicTestCase.class,
	RepeaterHourTest.class,
	RepeaterFortnightTest.class,
	SpanTestCase.class,
	HandlerTestCase.class,
	RepeaterWeekendTest.class,
	NumerizerTestCase.class
})
public class ChronicTestSuite {
}

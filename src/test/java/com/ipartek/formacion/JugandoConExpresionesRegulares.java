package com.ipartek.formacion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JugandoConExpresionesRegulares {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemplazar() {
		final Pattern patron = Pattern.compile("a*b");

		Matcher matcher = patron.matcher("a()b");
		assertTrue(matcher.find());

		matcher = patron.matcher("a1111c");
		assertFalse(matcher.find());

		matcher = patron.matcher("aaab");
		assertTrue(matcher.matches());
	}

}

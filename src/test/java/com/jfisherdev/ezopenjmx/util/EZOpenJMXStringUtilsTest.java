package com.jfisherdev.ezopenjmx.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Josh Fisher
 */
public class EZOpenJMXStringUtilsTest {

    @Test
    public void testCheckNotNullOrEmptyForNonNullOrEmptyString() throws Exception {
        final String string = "x";

        assertEquals(string, EZOpenJMXStringUtils.checkNotNullOrEmpty(string));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotNullOrEmptyForNull() throws Exception {
        EZOpenJMXStringUtils.checkNotNullOrEmpty(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotNullOrEmptyForEmptyString() throws Exception {
        EZOpenJMXStringUtils.checkNotNullOrEmpty("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotNullOrEmptyForWhitespace() throws Exception {
        EZOpenJMXStringUtils.checkNotNullOrEmpty(" ");
    }

    @Test
    public void testIsNullOrEmptyForNonNullOrEmptyString() throws Exception {
        final String string = "x";

        assertFalse(EZOpenJMXStringUtils.isNullOrEmpty(string));
    }

    @Test
    public void testIsNullOrEmptyForNull() throws Exception {
        assertTrue(EZOpenJMXStringUtils.isNullOrEmpty(null));
    }

    @Test
    public void testIsNullOrEmptyForEmptyString() throws Exception {
        assertTrue(EZOpenJMXStringUtils.isNullOrEmpty(""));
    }

    @Test
    public void testIsNullOrEmptyForWhitespace() throws Exception {
        assertTrue(EZOpenJMXStringUtils.isNullOrEmpty(" "));
    }

    @Test
    public void testTrimToEmpty() throws Exception {
        final String string = "  abc  ";

        assertEquals(string.trim(), EZOpenJMXStringUtils.trimToEmpty(string));
    }

    @Test
    public void testTrimToEmptyForNull() throws Exception {
        assertEquals("", EZOpenJMXStringUtils.trimToEmpty(""));
    }


}
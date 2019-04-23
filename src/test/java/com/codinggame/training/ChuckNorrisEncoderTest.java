package com.codinggame.training;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codinggame.training.ChuckNorrisEncoder.charEncode;
import static com.codinggame.training.ChuckNorrisEncoder.encode;
import static com.codinggame.training.ChuckNorrisEncoder.to7bit;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;


class ChuckNorrisEncoderTest
{
    @Test
    void shouldReturnNullIfProvidedWithNull() {
        assertNull(encode(null));
    }

    @Test
    void shouldReturnEmptyStringIfProvidedWithEmptyString() {
        assertThat(encode("")).isEqualTo("");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_0()
    {
        // '\u0000'
        assertThat(charEncode(Integer.toBinaryString('\u0000'))).isEqualTo("00 0");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_1()
    {
        // '\u0001'
        assertThat(charEncode(Integer.toBinaryString('\u0001'))).isEqualTo("0 0");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_10()
    {
        // '\u0002'
        assertThat(charEncode(Integer.toBinaryString('\u0002'))).isEqualTo("0 0 00 0");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_11()
    {
        // '\u0003'
        assertThat(charEncode(Integer.toBinaryString('\u0003'))).isEqualTo("0 00");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_100()
    {
        // '\u0004'
        assertThat(charEncode(Integer.toBinaryString('\u0004'))).isEqualTo("0 0 00 00");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_101()
    {
        // '\u0005'
        assertThat(charEncode(Integer.toBinaryString('\u0005'))).isEqualTo("0 0 00 0 0 0");
    }



    @Test
    @Tag("single_char")
    void shouldCharEncode_A()
    {
        // A 01000001
        assertThat(charEncode(Integer.toBinaryString('A'))).isEqualTo("0 0 00 00000 0 0");
    }


    @Test
    @Tag("single_char")
    void shouldCharEncode_B()
    {
        // B 01000010
        assertThat(charEncode(Integer.toBinaryString('B'))).isEqualTo("0 0 00 0000 0 0 00 0");
    }


    @Test
    @Tag("single_char")
    void shouldCharEncode_C()
    {
        // C 01000011
        assertThat(charEncode(Integer.toBinaryString('C'))).isEqualTo("0 0 00 0000 0 00");
    }


    @Test
    @Tag("single_char")
    void shouldCharEncode_M()
    {
        // M 01001101
        assertThat(charEncode(Integer.toBinaryString('M'))).isEqualTo("0 0 00 00 0 00 00 0 0 0");
    }

    @Test
    @Tag("single_char")
    void shouldEncode_0()
    {
        // '\u0000'
        assertThat(encode("\u0000")).isEqualTo("00 0000000");
    }

    @Test
    @Tag("single_char")
    void shouldEncode_1()
    {
        // '\u0001'
        assertThat(encode("\u0001")).isEqualTo("00 000000 0 0");
    }

    @Test
    @Tag("single_char")
    void shouldEncode_10()
    {
        // '\u0002'
        assertThat(encode("\u0002")).isEqualTo("00 00000 0 0 00 0");
    }

    @Test
    @Tag("single_char")
    void shouldEncode_11()
    {
        // '\u0003'
        assertThat(encode("\u0003")).isEqualTo("00 00000 0 00");
    }

    @Test
    @Tag("single_char")
    void shouldEncode_100()
    {
        // '\u0004'
        assertThat(encode("\u0004")).isEqualTo("00 0000 0 0 00 00");
    }

    @Test
    void shouldEncode_101()
    {
        // '\u0005'
        assertThat(encode("\u0005")).isEqualTo("00 0000 0 0 00 0 0 0");
    }

    @Test
    void shouldEncode_A()
    {
        // A 1000001
        assertThat(encode("A")).isEqualTo("0 0 00 00000 0 0");
    }

    @Test
    void shouldEncode_B()
    {
        // B 1000010
        assertThat(encode("B")).isEqualTo("0 0 00 0000 0 0 00 0");
    }

    @Test
    void shouldEncode_C()
    {
        // C 1000011
        assertThat(encode("C")).isEqualTo("0 0 00 0000 0 00");
    }

    @Test
    void shouldEncode_M()
    {
        // M 1001101
        assertThat(encode("M")).isEqualTo("0 0 00 00 0 00 00 0 0 0");
    }

    @Test
    void shouldEncode_PERCENT()
    {
        // % 0100101
        assertThat(encode("%")).isEqualTo("00 0 0 0 00 00 0 0 00 0 0 0");
    }

    @Test
    void shouldEncode_CC()
    {
        // CC 10000111000011
        assertThat(encode("CC")).isEqualTo("0 0 00 0000 0 000 00 0000 0 00");
    }

    @Test
    void shouldGet7bitRepresentationOf_PERCENT()
    {
        // % 0100101
        final String binaryPercent = Integer.toBinaryString("%".charAt(0));
        assertThat(binaryPercent).isEqualTo("100101");
        assertThat(to7bit(binaryPercent)).isEqualTo("0100101");
    }
}

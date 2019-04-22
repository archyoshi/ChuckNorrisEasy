package com.codinggame.training;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codinggame.training.ChuckNorrisEncoder.charEncode;
import static com.codinggame.training.ChuckNorrisEncoder.encode;
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
    void shouldEncode_A()
    {
        // A 01000001
        assertThat(encode("A")).isEqualTo("0 0 00 00000 0 0");
    }

    @Test
    void shouldEncode_B()
    {
        // B 01000010
        assertThat(encode("B")).isEqualTo("0 0 00 0000 0 0 00 0");
    }

    @Test
    void shouldEncode_C()
    {
        // C 01000011
        assertThat(encode("C")).isEqualTo("0 0 00 0000 0 00");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_0()
    {
        // '\u0000'
        assertThat(charEncode('\u0000')).isEqualTo("00 0");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_1()
    {
        // '\u0001'
        assertThat(charEncode('\u0001')).isEqualTo("0 0");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_10()
    {
        // '\u0002'
        assertThat(charEncode('\u0002')).isEqualTo("0 0 00 0");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_11()
    {
        // '\u0003'
        assertThat(charEncode('\u0003')).isEqualTo("0 00");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_100()
    {
        // '\u0004'
        assertThat(charEncode('\u0004')).isEqualTo("0 0 00 00");
    }

    @Test
    @Tag("single_char")
    void shouldCharEncode_101()
    {
        // '\u0005'
        assertThat(charEncode('\u0005')).isEqualTo("0 0 00 0 0 0");
    }



    @Test
    @Tag("single_char")
    void shouldCharEncode_A()
    {
        // A 01000001
        assertThat(charEncode('A')).isEqualTo("0 0 00 00000 0 0");
    }
}

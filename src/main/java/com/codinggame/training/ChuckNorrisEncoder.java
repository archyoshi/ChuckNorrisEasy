package com.codinggame.training;

import java.util.ArrayList;
import java.util.List;

class ChuckNorrisEncoder {

    private static final String SPACE = " ";
    private static final String STRING_ZERO = "0";

    static String encode(final String text) {
        if (null == text || text.isEmpty())
            return text;
        else {
            return charEncode(text.chars()
                    .mapToObj(Integer::toBinaryString)
                    .map(ChuckNorrisEncoder::to7bit)
                    .reduce((s, t) -> s + t)
                    .orElse(""));
        }
    }

    static String to7bit(final String binaryText) {
        final int missingBits = 7 - binaryText.length();
        return missingBits > 0 ? "0".repeat(missingBits) + binaryText : binaryText;
    }

    static String charEncode(final String binaryText) {
        final List<String> encodedParts = new ArrayList<>();
        Bit previousBit = Bit.of(binaryText.charAt(0)).flip();
        Bit currentBit;

        for (int i = 0, bitRepetitions; i < binaryText.length(); i++) {
            currentBit = Bit.of(binaryText.charAt(i));
            if (previousBit == currentBit) {
                bitRepetitions = binaryText.substring(i).indexOf(previousBit.flip().toChar());
                bitRepetitions = bitRepetitions > 0 ? bitRepetitions : 1;
                encodedParts.set(encodedParts.size() - 1, encodedParts.get(encodedParts.size() - 1) + STRING_ZERO.repeat(bitRepetitions));
                i += bitRepetitions - 1;
            } else {
                encodedParts.add(STRING_ZERO + (currentBit.isZero() ? STRING_ZERO : "") + SPACE + STRING_ZERO);
            }
            previousBit = currentBit;
        }

        return encodedParts.stream().reduce((s, t) -> s + SPACE + t).orElse("");
    }

    private enum Bit {
        ZERO(false),
        ONE(true);

        private final boolean value;

        Bit(final boolean value) {
            this.value = value;
        }

        static Bit of(char character) {
            return '0' == character ? ZERO : ONE;
        }

        boolean isZero() {
            return !value;
        }

        char toChar() {
            return isZero() ? '0' : '1';
        }

        Bit flip() {
            return isZero() ? ONE : ZERO;
        }
    }
}

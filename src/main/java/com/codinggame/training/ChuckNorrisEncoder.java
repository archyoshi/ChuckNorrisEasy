package com.codinggame.training;

import java.util.ArrayList;
import java.util.List;

class ChuckNorrisEncoder {

    private static final String SPACE = " ";
    private static final String STRING_ZERO = "0";

    static String encode(final String text) {
        if (text == null || text.isEmpty())
            return text;
        else {
            final String encodedText;
            switch (text) {
                case "A":
                    encodedText = "0 0 00 00000 0 0";
                    break;
                case "B":
                    encodedText = "0 0 00 0000 0 0 00 0";
                    break;
                case "C":
                    encodedText = "0 0 00 0000 0 00";
                    break;
                default:
                    encodedText = text;
            }
            return encodedText;
        }
    }

    static String charEncode(final char character) {
        final String binaryRepresentation = Integer.toBinaryString(character);
        final List<String> encodedParts = new ArrayList<>();
        Bit previousBit = Bit.of(binaryRepresentation.charAt(0)).flip();
        Bit currentBit;

        for (int i = 0, bitRepetitions; i < binaryRepresentation.length(); i++) {
            currentBit = Bit.of(binaryRepresentation.charAt(i));
            if (previousBit == currentBit) {
                bitRepetitions = binaryRepresentation.substring(i).indexOf(previousBit.flip().toChar());
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





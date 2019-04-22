package com.codinggame.training;

import java.util.ArrayList;
import java.util.List;

class ChuckNorrisEncoder {
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
        System.out.println(binaryRepresentation);

        final List<String> encodedParts = new ArrayList<>();
        final StringBuilder encodingBuilder = new StringBuilder();
        Bit previousBit = Bit.of(binaryRepresentation.charAt(0)).flip();
        Bit currentBit;
        int sameBitsCount;

        for (int i = 0; i < binaryRepresentation.length(); i++) {
            encodingBuilder.delete(0, encodingBuilder.length());
            currentBit = Bit.of(binaryRepresentation.charAt(i));
            if (previousBit == currentBit) {
                sameBitsCount = binaryRepresentation.substring(i).indexOf(previousBit.flip().toChar());
                sameBitsCount = sameBitsCount > 0 ? sameBitsCount : 1;
                encodedParts.set(encodedParts.size() - 1, encodedParts.get(encodedParts.size() - 1) + "0".repeat(sameBitsCount));
                i += sameBitsCount - 1;
            } else {
                encodingBuilder.append("0");
                if (Bit.ZERO == currentBit)
                    encodingBuilder.append("0");
                encodingBuilder.append(" ").append("0");
                encodedParts.add(encodingBuilder.toString());
            }
            previousBit = currentBit;
        }


        return encodedParts.stream().reduce((s, t) -> s + " " + t).orElse("");
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

        char toChar() {
            return this == ZERO ? '0' : '1';
        }

        Bit flip() {
            return !this.value ? ONE : ZERO;
        }
    }
}





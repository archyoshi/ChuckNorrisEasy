package com.codinggame.training;

import java.util.ArrayList;
import java.util.List;

class ChuckNorrisEncoder
{
    static String encode(final String text) {
        if(text == null || text.isEmpty())
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

    static String charEncode(final char character){
        final StringBuilder representationBuilder = new StringBuilder();
        final String binaryRepresentation = Integer.toBinaryString(character);
        final List<String> parts = new ArrayList<>();
//        System.out.println(binaryRepresentation);
        char previousChar = 0;
        for (int i = 0; i < binaryRepresentation.length(); i++) {
            if (i != 0 && previousChar == binaryRepresentation.charAt(i)) {
                representationBuilder.delete(0, representationBuilder.length());
                parts.set(i - 1, parts.get(i - 1) + "0");
            } else {
                representationBuilder.delete(0, representationBuilder.length());
                representationBuilder.append("0");
                if ('0' == binaryRepresentation.charAt(i))
                    representationBuilder.append("0");
                representationBuilder.append(" ").append("0");
                parts.add(representationBuilder.toString());
            }
            previousChar = binaryRepresentation.charAt(i);
        }
        return parts.stream().reduce((s, t) -> s + " " + t).orElse("");
    }
}

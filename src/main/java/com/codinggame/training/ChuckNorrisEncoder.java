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
        final String binaryRepresentation = Integer.toBinaryString(character);
//        System.out.println(binaryRepresentation);


        final List<String> encodedParts = new ArrayList<>();
        final StringBuilder encodingBuilder = new StringBuilder();
        char previousChar = 0;
        for (int i = 0, sameBitCounter = 0; i < binaryRepresentation.length(); i++) {
            encodingBuilder.delete(0, encodingBuilder.length());
            if (i != 0 && previousChar == binaryRepresentation.charAt(i)) {
                sameBitCounter++;
                encodedParts.set(i - sameBitCounter, encodedParts.get(i - sameBitCounter) + "0");
            } else {
                sameBitCounter = 0;
                encodingBuilder.append("0");
                if ('0' == binaryRepresentation.charAt(i))
                    encodingBuilder.append("0");
                encodingBuilder.append(" ").append("0");
                encodedParts.add(encodingBuilder.toString());
            }
            previousChar = binaryRepresentation.charAt(i);
        }


        return encodedParts.stream().reduce((s, t) -> s + " " + t).orElse("");
    }
}

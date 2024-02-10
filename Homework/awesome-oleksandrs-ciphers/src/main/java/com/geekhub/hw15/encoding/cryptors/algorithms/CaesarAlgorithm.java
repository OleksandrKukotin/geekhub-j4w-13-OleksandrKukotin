package com.geekhub.hw15.encoding.cryptors.algorithms;

import com.geekhub.hw15.encoding.cryptors.Decryptor;
import com.geekhub.hw15.encoding.cryptors.Encryptor;

import java.util.function.UnaryOperator;

public class CaesarAlgorithm implements Encryptor, Decryptor {

    private final int shift;

    public CaesarAlgorithm(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String inputMessage) {
        UnaryOperator<Character> encodeShift = (Character character) -> (char) (character + shift);
        return shiftInput(inputMessage, encodeShift);
    }

    @Override
    public String decrypt(String inputMessage) {
        UnaryOperator<Character> decodeShift = (Character character) -> (char) (character - shift);
        return shiftInput(inputMessage, decodeShift);
    }

    private String shiftInput(String inputMessage, UnaryOperator<Character> shiftFunction) {
        if (inputMessage.isBlank()) {
            return "";
        }

        return inputMessage.chars()
            .mapToObj(character -> (char) character)
            .map(shiftFunction)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }
}

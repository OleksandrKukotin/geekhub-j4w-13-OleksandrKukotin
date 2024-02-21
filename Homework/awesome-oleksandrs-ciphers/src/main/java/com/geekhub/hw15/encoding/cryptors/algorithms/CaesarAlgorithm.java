package com.geekhub.hw15.encoding.cryptors.algorithms;

import com.geekhub.hw15.encoding.cryptors.Decryptor;
import com.geekhub.hw15.encoding.cryptors.Encryptor;
import com.geekhub.hw15.exception.WrongKeyException;

import java.util.function.UnaryOperator;

public class CaesarAlgorithm implements Encryptor, Decryptor {

    private final int shift;

    public CaesarAlgorithm(int shift) {
        checkKey(shift);
        this.shift = shift;
    }

    private void checkKey(int shift) {
        if (shift == 0) {
            throw new WrongKeyException("Zero key in Caesar cipher makes no sense, try another integer");
        }
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

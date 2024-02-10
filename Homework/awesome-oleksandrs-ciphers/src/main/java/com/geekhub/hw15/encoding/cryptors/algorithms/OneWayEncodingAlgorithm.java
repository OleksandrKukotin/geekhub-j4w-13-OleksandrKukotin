package com.geekhub.hw15.encoding.cryptors.algorithms;

import com.geekhub.hw15.encoding.cryptors.Encryptor;
import com.geekhub.hw15.exception.EncodingOperationException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OneWayEncodingAlgorithm implements Encryptor {

    @Override
    public String encrypt(String inputMessage) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] encodedBytes = digest.digest(inputMessage.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new EncodingOperationException(e.getMessage(), e);
        }
    }
}

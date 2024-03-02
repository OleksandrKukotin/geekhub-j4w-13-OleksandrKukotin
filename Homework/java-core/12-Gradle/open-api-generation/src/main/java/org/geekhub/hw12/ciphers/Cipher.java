package org.geekhub.hw12.ciphers;

public interface Cipher<T> {

    String encode(T encodeData);

    String decode(T decodeData);

}

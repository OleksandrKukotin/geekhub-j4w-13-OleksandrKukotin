package org.geekhub.hw11.config;

import com.geekhub.hw15.encoding.EncodingService;
import com.geekhub.hw15.encoding.cryptors.DecryptorFactory;
import com.geekhub.hw15.encoding.cryptors.EncryptorFactory;
import com.geekhub.hw15.encoding.cryptors.algorithms.CaesarAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.OneWayEncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.VigenereAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncodingServiceConfig {

    @Bean
    EncryptorFactory encryptorFactory(CaesarAlgorithm caesarAlgorithm,
                                      VigenereAlgorithm vigenereAlgorithm,
                                      OneWayEncodingAlgorithm oneWayEncodingAlgorithm) {
        return new EncryptorFactory(caesarAlgorithm, vigenereAlgorithm, oneWayEncodingAlgorithm);
    }

    @Bean
    DecryptorFactory decryptorFactory(CaesarAlgorithm caesarAlgorithm, VigenereAlgorithm vigenereAlgorithm) {
        return new DecryptorFactory(caesarAlgorithm, vigenereAlgorithm);
    }

    @Bean
    EncodingService encodingService(EncryptorFactory encryptorFactory, DecryptorFactory decryptorFactory) {
        return new EncodingService(encryptorFactory, decryptorFactory);
    }
}

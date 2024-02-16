package org.geekhub.hw11.config;

import com.geekhub.hw15.encoding.EncodingService;
import com.geekhub.hw15.encoding.cryptors.DecryptorFactory;
import com.geekhub.hw15.encoding.cryptors.EncryptorFactory;
import com.geekhub.hw15.encoding.cryptors.algorithms.CaesarAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.OneWayEncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.VigenereAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncodingServiceConfig {

    @Bean
    CaesarAlgorithm getCaesarAlgorithm(@Value("${caesar.shift}")  int shift) {
        return new CaesarAlgorithm(shift);
    }

    @Bean
    VigenereAlgorithm getVigenereAlgorithm(@Value("${vigenere.keyword}") String keyword) {
        return new VigenereAlgorithm(keyword);
    }
    @Bean
    EncodingService encodingServiceBean(CaesarAlgorithm caesarAlgorithm, VigenereAlgorithm vigenereAlgorithm) {
        return new EncodingService(
            new EncryptorFactory(caesarAlgorithm, vigenereAlgorithm, new OneWayEncodingAlgorithm()),
            new DecryptorFactory(caesarAlgorithm, vigenereAlgorithm));
    }
}

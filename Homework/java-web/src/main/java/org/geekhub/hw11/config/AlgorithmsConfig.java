package org.geekhub.hw11.config;

import com.geekhub.hw15.encoding.cryptors.algorithms.CaesarAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.OneWayEncodingAlgorithm;
import com.geekhub.hw15.encoding.cryptors.algorithms.VigenereAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgorithmsConfig {

    @Bean
    CaesarAlgorithm caesarAlgorithm(@Value("${caesar.shift}")  int shift) {
        return new CaesarAlgorithm(shift);
    }

    @Bean
    VigenereAlgorithm vigenereAlgorithm(@Value("${vigenere.keyword}") String keyword) {
        return new VigenereAlgorithm(keyword);
    }

    @Bean
    OneWayEncodingAlgorithm oneWayEncodingAlgorithm() {
        return new OneWayEncodingAlgorithm();
    }
}

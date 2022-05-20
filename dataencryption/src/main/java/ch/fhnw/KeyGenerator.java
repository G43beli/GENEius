package ch.fhnw;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGenerator {

    public static KeyPair createKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }

}

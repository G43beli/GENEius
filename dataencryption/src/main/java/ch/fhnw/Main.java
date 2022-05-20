package ch.fhnw;

import javax.xml.crypto.Data;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Main {

    public static void main(String [] args) {

        try {
            // create key pair
            //KeyPair kp = KeyGenerator.createKeyPair();
            //KeyUtil.storeKeyPair(kp, "xyz", "/Users/davidherzig/temp");

            // load key from disk
            PublicKey pubKey = (PublicKey)KeyUtil.loadKey("/user/public_key.der", KeyUtil.PUBLIC_KEY);
            PrivateKey prvKey = (PrivateKey)KeyUtil.loadKey("/user/private_key.der", KeyUtil.PRIVATE_KEY);

            // encrypt
            //DataEncryption.encryptFile("/Users/davidherzig/temp/test.txt", pubKey);

            // decrypt
            DataEncryption.decryptFile("/users/message.txt", prvKey);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

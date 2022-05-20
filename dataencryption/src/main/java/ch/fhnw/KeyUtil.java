package ch.fhnw;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyUtil {

    public static void storeKeyPair(KeyPair keyPair, String name, String path) throws Exception {
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        try (FileOutputStream fos = new FileOutputStream(path + "/" + name + "_public.key")) {
            fos.write(publicKey.getEncoded());
        }
        try (FileOutputStream fos = new FileOutputStream(path + "/" + name + "_private.key")) {
            fos.write(privateKey.getEncoded());
        }
    }

    public final static int PUBLIC_KEY = 0;
    public final static int PRIVATE_KEY = 1;

    public static Key loadKey(String keyFile, int keyType) throws Exception {
        File keyFileObj = new File(keyFile);
        byte[] keyBytes = Files.readAllBytes(keyFileObj.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        if (keyType == PUBLIC_KEY) {
            EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            return keyFactory.generatePublic(keySpec);
        } else if (keyType == PRIVATE_KEY) {
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            return keyFactory.generatePrivate(keySpec);
        } else {
            throw new Exception("Invalid Key Type");
        }
    }

}

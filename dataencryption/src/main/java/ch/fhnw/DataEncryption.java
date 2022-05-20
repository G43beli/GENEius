package ch.fhnw;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DataEncryption {


    public static void encryptFile(String file, PublicKey key) throws Exception {
        File f = new File(file);
        byte[] fileBytes = Files.readAllBytes(f.toPath());
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedFileBytes = encryptCipher.doFinal(fileBytes);
        try (FileOutputStream stream = new FileOutputStream(f)) {
            stream.write(encryptedFileBytes);
        }
    }

    public static void decryptFile(String file, PrivateKey key) throws Exception {
        File f = new File(file);
        byte[] encryptedFileBytes = Files.readAllBytes(f.toPath());
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedFileBytes = decryptCipher.doFinal(encryptedFileBytes);
        try (FileOutputStream stream = new FileOutputStream(f)) {
            stream.write(decryptedFileBytes);
        }
    }

}

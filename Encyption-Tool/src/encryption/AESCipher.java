package encryption;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESCipher implements Encryptor {
    private Key key;
    private Cipher cipher;

    public AESCipher(String keyString) {
        byte[] keyBytes = keyString.getBytes();
        key = new SecretKeySpec(keyBytes, "AES");

        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (Exception e) {
            System.err.println("Error creating AES cipher: " + e.getMessage());
        }
    }

    @Override
    public String encrypt(String message) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] messageBytes = message.getBytes();
            byte[] encryptedBytes = cipher.doFinal(messageBytes);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.err.println("Error encrypting message: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String decrypt(String cipherText) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
            byte[] decryptedBytes = cipher.doFinal(cipherBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.err.println("Error decrypting message: " + e.getMessage());
            return null;
        }
    }
}

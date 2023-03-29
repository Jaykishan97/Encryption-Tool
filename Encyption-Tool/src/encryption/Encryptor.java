package encryption;

public interface Encryptor {
    String encrypt(String message);
    String decrypt(String cipher);
}

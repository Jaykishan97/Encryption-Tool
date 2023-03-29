package encryption;

public class CaesarCipher implements Encryptor {
    private int shift;

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String message) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) ((c + shift - 'A') % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ((c + shift - 'a') % 26 + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String cipher) {
        CaesarCipher decryptor = new CaesarCipher(26 - shift);
        return decryptor.encrypt(cipher);
    }
}

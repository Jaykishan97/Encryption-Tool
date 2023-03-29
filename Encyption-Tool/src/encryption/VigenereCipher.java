package encryption;

public class VigenereCipher implements Encryptor {
    private String key;

    public VigenereCipher(String key) {
        this.key = key.toUpperCase();
    }

    @Override
    public String encrypt(String message) {
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char shift = key.charAt(keyIndex);
                int shiftAmount = shift - 'A';
                if (Character.isLowerCase(c)) {
                    result.append((char) ((c + shiftAmount - 'a') % 26 + 'a'));
                } else {
                    result.append((char) ((c + shiftAmount - 'A') % 26 + 'A'));
                }
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String cipher) {
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;
        for (char c : cipher.toCharArray()) {
            if (Character.isLetter(c)) {
                char shift = key.charAt(keyIndex);
                int shiftAmount = shift - 'A';
                if (Character.isLowerCase(c)) {
                    result.append((char) ((c - shiftAmount - 'a' + 26) % 26 + 'a'));
                } else {
                    result.append((char) ((c - shiftAmount - 'A' + 26) % 26 + 'A'));
                }
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}


package encryption;

import java.util.Scanner;

public class EncryptorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an encryption algorithm:");
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Vigenere Cipher");
        System.out.println("3. Playfair Cipher");
        System.out.println("4. Advanced Encryption Standard (AES)");
        System.out.print("Enter the algorithm number: ");
        int algorithm = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Encryptor encryptor = null;
        switch (algorithm) {
            case 1:
                System.out.print("Enter the Caesar Cipher key (an integer between 1 and 25): ");
                int caesarKey = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                encryptor = new CaesarCipher(caesarKey);
                break;
            case 2:
                System.out.print("Enter the Vigenere Cipher key (a string of letters): ");
                String vigenereKey = scanner.nextLine();
                encryptor = new VigenereCipher(vigenereKey);
                break;
            case 3:
                System.out.print("Enter the Playfair Cipher key (a string of letters): ");
                String playfairKey = scanner.nextLine();
                encryptor = new PlayfairCipher(playfairKey);
                break;
            case 4:
                System.out.print("Enter the AES key (a string of 16, 24, or 32 characters): ");
                String aesKey = scanner.nextLine();
                encryptor = new AESCipher(aesKey);
                break;
            default:
                System.out.println("Invalid algorithm number");
                System.exit(0);
        }

        System.out.print("Enter the message you want to encrypt: ");
        String message = scanner.nextLine();

        String cipher = encryptor.encrypt(message);
        System.out.println("The encrypted message is: " + cipher);

        System.out.print("Enter the cipher you want to decrypt: ");
        cipher = scanner.nextLine();

        message = encryptor.decrypt(cipher);
        System.out.println("The decrypted message is: " + message);
    }
}

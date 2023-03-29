package encryption;

import java.util.Arrays;

public class PlayfairCipher implements Encryptor {
    private char[][] matrix;

    public PlayfairCipher(String key) {
        key = key.replaceAll("[^a-zA-Z]", "").toUpperCase();
        StringBuilder keyBuilder = new StringBuilder(key);
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') {
                continue;
            }
            if (keyBuilder.indexOf(Character.toString(c)) == -1) {
                keyBuilder.append(c);
            }
        }
        key = keyBuilder.toString();

        matrix = new char[5][5];
        int row = 0, col = 0;
        for (char c : key.toCharArray()) {
            matrix[row][col] = c;
            if (++col == 5) {
                row++;
                col = 0;
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') {
                continue;
            }
            if (key.indexOf(c) == -1) {
                matrix[row][col] = c;
                if (++col == 5) {
                    row++;
                    col = 0;
                }
            }
        }
    }

    @Override
    public String encrypt(String message) {
        StringBuilder result = new StringBuilder();
        message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();
        if (message.length() % 2 != 0) {
            message += "X";
        }
        for (int i = 0; i < message.length(); i += 2) {
            char a = message.charAt(i);
            char b = message.charAt(i + 1);
            int[] posA = findPosition(a);
            int[] posB = findPosition(b);
            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                result.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                result.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    
    @Override
    public String decrypt(String cipher) {
        StringBuilder result = new StringBuilder();
        cipher = cipher.replaceAll("[^a-zA-Z]", "").toUpperCase();
        for (int i = 0; i < cipher.length(); i += 2) {
            char a = cipher.charAt(i);
            char b = cipher.charAt(i + 1);
            int[] posA = findPosition(a);
            int[] posB = findPosition(b);
            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + 4) % 5]);
                result.append(matrix[posB[0]][(posB[1] + 4) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + 4) % 5][posA[1]]);
                result.append(matrix[(posB[0] + 4) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    private int[] findPosition(char c) {
        int[] pos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }
}

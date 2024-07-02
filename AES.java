public class AES {

    static int[][] sbox = {
            { 0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76 },
            { 0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0 },
            { 0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15 },
            { 0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75 },
            { 0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84 },
            { 0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF },
            { 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8 },
            { 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2 },
            { 0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73 },
            { 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB },
            { 0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79 },
            { 0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08 },
            { 0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A },
            { 0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E },
            { 0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF },
            { 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16 }
    };

    private static final int[] rcon = {
            0x00000000, 0x01000000, 0x02000000, 0x04000000,
            0x08000000, 0x10000000, 0x20000000, 0x40000000,
            0x80000000, 0x1B000000, 0x36000000
    };

    private static final int[][] mixColumnsMatrix = {
            { 0x02, 0x03, 0x01, 0x01 },
            { 0x01, 0x02, 0x03, 0x01 },
            { 0x01, 0x01, 0x02, 0x03 },
            { 0x03, 0x01, 0x01, 0x02 }
    };

    public static void main(String args[]) {
        int[] key = {
                0x2B7E1516, 0x28AED2A6, 0xABF71588, 0x09CF4F3C
        };
        int[][][] expandedKeys = keyExpansion(key);

        int[][] state = {
                { 0x32, 0x43, 0xf6, 0xa8 },
                { 0x88, 0x5a, 0x30, 0x8d },
                { 0x31, 0x31, 0x98, 0xa2 },
                { 0xe0, 0x37, 0x07, 0x34 }
        };

        // Initial round
        addRoundKey(state, expandedKeys[0]);

        // 9 main rounds
        for (int round = 1; round < 10; round++) {
            subBytes(state);
            shiftRows(state);
            mixColumns(state);
            addRoundKey(state, expandedKeys[round]);
        }

        // Final round
        subBytes(state);
        shiftRows(state);
        addRoundKey(state, expandedKeys[10]);

        // Print final state
        System.out.println("Encrypted state:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%02x ", state[i][j]);
            }
            System.out.println();
        }
    }

    public static void subBytes(int[][] state) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = state[i][j];
                state[i][j] = sbox[(value >> 4) & 0x0F][value & 0x0F];
            }
        }
    }

    public static void shiftRows(int[][] state) {
        for (int i = 1; i < 4; i++) {
            int[] row = new int[4];
            for (int j = 0; j < 4; j++) {
                row[j] = state[i][(j + i) % 4];
            }
            for (int j = 0; j < 4; j++) {
                state[i][j] = row[j];
            }
        }
    }

    public static void mixColumns(int[][] state) {
        int[][] temp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = galoisMult(state[0][j], mixColumnsMatrix[i][0]) ^
                        galoisMult(state[1][j], mixColumnsMatrix[i][1]) ^
                        galoisMult(state[2][j], mixColumnsMatrix[i][2]) ^
                        galoisMult(state[3][j], mixColumnsMatrix[i][3]);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                state[i][j] = temp[i][j];
            }
        }
    }

    public static int galoisMult(int a, int b) {
        int p = 0;
        int hiBitSet;
        for (int counter = 0; counter < 8; counter++) {
            if ((b & 1) != 0) {
                p ^= a;
            }
            hiBitSet = (a & 0x80);
            a <<= 1;
            if (hiBitSet != 0) {
                a ^= 0x1b;
            }
            b >>= 1;
        }
        return p;
    }

    public static void addRoundKey(int[][] state, int[][] key) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                state[i][j] ^= key[j][i];
            }
        }
    }

    public static int[][][] keyExpansion(int[] key) {
        int[][][] expandedKey = new int[11][4][4];

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                expandedKey[0][col][row] = (key[row] >> (8 * (3 - col))) & 0xFF;
            }
        }

        for (int round = 1; round < 11; round++) {
            int[] temp = new int[4];
            for (int col = 0; col < 4; col++) {
                temp[col] = expandedKey[round - 1][col][3];
            }

            temp = subWord(rotWord(temp));

            for (int col = 0; col < 4; col++) {
                temp[col] ^= (rcon[round] >> (24 - col * 8)) & 0xFF;
            }

            for (int row = 0; row < 4; row++) {
                expandedKey[round][0][row] = expandedKey[round - 1][0][row] ^ temp[row];
            }

            for (int col = 1; col < 4; col++) {
                for (int row = 0; row < 4; row++) {
                    expandedKey[round][col][row] = expandedKey[round][col - 1][row] ^ expandedKey[round - 1][col][row];
                }
            }
        }

        return expandedKey;
    }

    public static int[] rotWord(int[] word) {
        int[] rotatedWord = new int[4];
        for (int i = 0; i < 4; i++) {
            rotatedWord[i] = word[(i + 1) % 4];
        }
        return rotatedWord;
    }

    public static int[] subWord(int[] word) {
        int[] subbedWord = new int[4];
        for (int i = 0; i < 4; i++) {
            subbedWord[i] = sbox[(word[i] >> 4) & 0x0F][word[i] & 0x0F];
        }
        return subbedWord;
    }
}

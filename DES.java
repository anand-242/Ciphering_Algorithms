import java.util.HashMap;

public class DES {

    // Hexadecimal to binary conversion
    public static String hex2bin(String s) {
        HashMap<Character, String> mp = new HashMap<>();
        mp.put('0', "0000");
        mp.put('1', "0001");
        mp.put('2', "0010");
        mp.put('3', "0011");
        mp.put('4', "0100");
        mp.put('5', "0101");
        mp.put('6', "0110");
        mp.put('7', "0111");
        mp.put('8', "1000");
        mp.put('9', "1001");
        mp.put('A', "1010");
        mp.put('B', "1011");
        mp.put('C', "1100");
        mp.put('D', "1101");
        mp.put('E', "1110");
        mp.put('F', "1111");
        String bin = "";
        for (int i = 0; i < s.length(); i++) {
            bin += mp.get(s.charAt(i));
        }
        return bin;
    }

    // Binary to hexadecimal conversion
    public static String bin2hex(String s) {
        HashMap<String, Character> mp = new HashMap<>();
        mp.put("0000", '0');
        mp.put("0001", '1');
        mp.put("0010", '2');
        mp.put("0011", '3');
        mp.put("0100", '4');
        mp.put("0101", '5');
        mp.put("0110", '6');
        mp.put("0111", '7');
        mp.put("1000", '8');
        mp.put("1001", '9');
        mp.put("1010", 'A');
        mp.put("1011", 'B');
        mp.put("1100", 'C');
        mp.put("1101", 'D');
        mp.put("1110", 'E');
        mp.put("1111", 'F');
        String hex = "";
        for (int i = 0; i < s.length(); i += 4) {
            String ch = "" + s.charAt(i) + s.charAt(i + 1) + s.charAt(i + 2) + s.charAt(i + 3);
            hex += mp.get(ch);
        }
        return hex;
    }

    // Binary to decimal conversion
    public static int bin2dec(String binary) {
        return Integer.parseInt(binary, 2);
    }

    // Decimal to binary conversion
    public static String dec2bin(int num) {
        String res = Integer.toBinaryString(num);
        while (res.length() % 4 != 0) {
            res = "0" + res;
        }
        return res;
    }

    // Permute function to rearrange the bits
    public static String permute(String k, int[] arr, int n) {
        String permutation = "";
        for (int i = 0; i < n; i++) {
            permutation += k.charAt(arr[i] - 1);
        }
        return permutation;
    }

    // Shifting the bits towards left by nth shifts
    public static String shiftLeft(String k, int nthShifts) {
        String s = k.substring(nthShifts) + k.substring(0, nthShifts);
        return s;
    }

    // XOR of two strings of binary numbers a and b
    public static String xor(String a, String b) {
        String ans = "";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                ans += "0";
            } else {
                ans += "1";
            }
        }
        return ans;
    }

    // DES Tables
    static int[] initialPerm = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    static int[] expD = {
            32, 1, 2, 3, 4, 5, 4, 5,
            6, 7, 8, 9, 8, 9, 10, 11,
            12, 13, 12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21, 20, 21,
            22, 23, 24, 25, 24, 25, 26, 27,
            28, 29, 28, 29, 30, 31, 32, 1
    };

    static int[] per = {
            16, 7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2, 8, 24, 14,
            32, 27, 3, 9,
            19, 13, 30, 6,
            22, 11, 4, 25
    };

    static int[][][] sbox = { {
            { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
            { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
            { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
            { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
    }, {
            { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
            { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
            { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
            { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
    }, {
            { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
            { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
            { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
            { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
    }, {
            { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
            { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
            { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
            { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
    }, {
            { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
            { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
            { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
            { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }
    }, {
            { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
            { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
            { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
            { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }
    }, {
            { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
            { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
            { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
            { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
    }, {
            { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
            { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
            { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
            { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }
    } };

    static int[] finalPerm = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
    };

    public static String encrypt(String pt, String key) {
        // Initial Permutation
        pt = permute(pt, initialPerm, 64);
        System.out.println("After initial permutation: " + bin2hex(pt));

        // Splitting
        String left = pt.substring(0, 32);
        String right = pt.substring(32, 64);
        System.out.println("After splitting: L0=" + bin2hex(left) + " R0=" + bin2hex(right));

        for (int i = 0; i < 16; i++) {
            // Expansion D-box
            String rightExpanded = permute(right, expD, 48);

            // XOR RoundKey[i] and rightExpanded
            String xorResult = xor(rightExpanded, key);

            // S-boxes
            String sboxStr = "";
            for (int j = 0; j < 8; j++) {
                int row = bin2dec("" + xorResult.charAt(j * 6) + xorResult.charAt(j * 6 + 5));
                int col = bin2dec("" + xorResult.charAt(j * 6 + 1) + xorResult.charAt(j * 6 + 2)
                        + xorResult.charAt(j * 6 + 3) + xorResult.charAt(j * 6 + 4));
                int val = sbox[j][row][col];
                sboxStr += dec2bin(val);
            }

            // Straight D-box
            sboxStr = permute(sboxStr, per, 32);

            // XOR Left and sboxStr
            String result = xor(left, sboxStr);
            left = result;

            // Swapper
            if (i != 15) {
                String temp = left;
                left = right;
                right = temp;
                System.out.println("Round " + (i + 1) + " " + bin2hex(left) + " " + bin2hex(right) + " " + key);
            }
        }

        // Combination
        String combine = left + right;

        // Final Permutation
        String cipherText = permute(combine, finalPerm, 64);
        return cipherText;
    }

    public static void main(String[] args) {
        System.out.println("enter the string for encryption");
        String pt = "123456ABCD132536";
        String key = "AABB09182736CCDD";

        System.out.println("Plain Text: " + pt);
        String bin_pt = hex2bin(pt);
        String bin_key = hex2bin(key);

        String cipherText = encrypt(bin_pt, bin_key);
        System.out.println("Cipher Text: " + bin2hex(cipherText));
    }
}

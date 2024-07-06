public class ModifiedDES {

    public static void main(String[] args) {
        // Example message "Hi"
        String message = "Hi";
        // Convert message to binary string
        String binaryMessage = stringToBinary(message);
        // Ensure binary message is 16-bit
        if (binaryMessage.length() < 16) {
            binaryMessage = String.format("%16s", binaryMessage).replace(' ', '0');
        } else if (binaryMessage.length() > 16) {
            binaryMessage = binaryMessage.substring(0, 16);
        }

        System.out.println("Original Binary Message: " + binaryMessage);
        // Step 1: Initial Permutation (Reverse the order of data)
        String permutedMessage = new StringBuilder(binaryMessage).reverse().toString();
        System.out.println("Initial Permutation: " + permutedMessage);

        // Step 2: Divide the 16-bit block into two 8-bit parts
        String LPT = permutedMessage.substring(0, 8);
        String RPT = permutedMessage.substring(8, 16);
        System.out.println("LPT: " + LPT + ", RPT: " + RPT);

        // Example key (16-bit)
        String key = "0010011010110110";
        // Convert the 16-bit key to a 12-bit key
        String key12Bit = convert16BitKeyTo12Bit(key);
        System.out.println("12-bit Key: " + key12Bit);

        // Perform 4 rounds of encryption
        for (int i = 0; i < 4; i++) {
            // Expansion Permutation: Expand RPT to 12-bit
            String expandedRPT = expand8BitTo12Bit(RPT);
            // XOR operation between expanded RPT and the key
            String xorResult = xorBinaryStrings(expandedRPT, key12Bit);
            // Apply S-Box substitution
            String substituted = sBoxSubstitution(xorResult);
            // Perform P-Box permutation (swap two consecutive bits)
            String permuted = pBoxPermutation(substituted);
            // XOR operation between LPT and permuted RPT, then swap
            String newRPT = xorBinaryStrings(LPT, permuted);
            LPT = RPT;
            RPT = newRPT;
        }

        // Combine LPT and RPT
        String combined = LPT + RPT;
        // Step 5: Final Permutation (Reverse the order of data)
        String finalPermutation = new StringBuilder(combined).reverse().toString();
        System.out.println("Encrypted Message: " + finalPermutation);

        // Step 6: Digital Signature
        String digitalSignature = generateDigitalSignature(finalPermutation);
        System.out.println("Digital Signature: " + digitalSignature);
    }

    private static String stringToBinary(String input) {
        StringBuilder binary = new StringBuilder();
        for (char character : input.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(character)).replaceAll(" ", "0"));
        }
        return binary.toString();
    }

    private static String convert16BitKeyTo12Bit(String key) {
        return key.substring(0, 3) + key.substring(4, 7) + key.substring(8, 11) + key.substring(12, 15);
    }

    private static String expand8BitTo12Bit(String RPT) {
        // Expansion Permutation: Repeat certain bits to expand to 12 bits
        return RPT.charAt(0) + "" + RPT.charAt(1) + RPT.charAt(3) + RPT.charAt(2) + RPT.charAt(3) + RPT.charAt(2)
                + RPT.charAt(4) + RPT.charAt(5) + RPT.charAt(7) + RPT.charAt(6) + RPT.charAt(7) + RPT.charAt(6);
    }

    private static String xorBinaryStrings(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) ^ b.charAt(i));
        }
        return result.toString();
    }

    private static String sBoxSubstitution(String input) {
        // S-Box
        int[][] sBox = {
                { 10, 6, 9, 3, 7, 11, 8, 14, 12, 13, 14, 15, 5, 4, 2, 1 },
                { 0, 1, 15, 12, 13, 4, 2, 5, 10, 6, 9, 3, 7, 11, 8, 14 },
                { 7, 11, 8, 14, 12, 13, 14, 15, 10, 6, 9, 3, 0, 1, 15, 12 },
                { 13, 4, 2, 5, 10, 6, 9, 3, 0, 1, 15, 12, 7, 11, 8, 14 }
        };

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i += 4) {
            int row = Integer.parseInt(input.substring(i, i + 2), 2);
            int col = Integer.parseInt(input.substring(i + 2, i + 4), 2);
            output.append(String.format("%4s", Integer.toBinaryString(sBox[row][col])).replace(' ', '0'));
        }
        return output.toString();
    }

    private static String pBoxPermutation(String input) {
        // Swap consecutive bits
        StringBuilder permuted = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            permuted.append(input.charAt(i + 1)).append(input.charAt(i));
        }
        return permuted.toString();
    }

    private static String generateDigitalSignature(String data) {
        // Right shift by 2 (equivalent to dividing by 4)
        int decimalValue = Integer.parseInt(data, 2);
        int shiftedValue = decimalValue >> 2;
        return Integer.toBinaryString(shiftedValue); // Inserted missing semicolon here
    }
}

import java.util.*;

public class CeaserCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the text");
        String text = sc.nextLine();
        text = text.toLowerCase().trim();
        System.out.println("enter the key");
        int key = sc.nextInt();
        System.out.println("enter 0 for encryption and 1 for decryption");
        int choice = sc.nextInt();
        switch (choice) {
            case 0:
                String ciphertext = cipher(text, key);
                System.out.println(ciphertext);
                break;
            case 1:
                String plaintext = decipher(text, key);
                System.out.println(plaintext);
                break;
            default:
                System.out.println("choice does not match");
                break;

        }

    }

    public static String cipher(String text, int key) {

        ArrayList<Character> alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {

            alphabet.add((char) (97 + i));
        }
        String ans = "";
        for (int i = 0; i < text.length(); i++) {
            int index = (alphabet.indexOf(text.charAt(i)) + key) % 26;
            ans = ans + alphabet.get(index);
        }

        return ans;
    }

    public static String decipher(String text, int key) {
        ArrayList<Character> alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {

            alphabet.add((char) (97 + i));
        }
        String ans = "";
        for (int i = 0; i < text.length(); i++) {
            int index = (alphabet.indexOf(text.charAt(i)) - key) % 26;
            if (index < 0)
                index += 26;
            ans = ans + alphabet.get(index);
        }

        return ans;
    }
}

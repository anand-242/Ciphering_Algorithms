import java.util.*;

public class MonoAlphabetic {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("enter the text");
        String text = sc.nextLine();
        text = text.toLowerCase().trim();
        System.out.println("enter the key");
        String key = sc.nextLine();
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
                System.out.println("wrong choice");
                break;
        }

    }

    public static String cipher(String text, String key) {
        ArrayList<Character> alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {

            alphabet.add((char) (97 + i));
        }
        ArrayList<Character> keylist = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            if (!keylist.contains(key.charAt(i)))
                keylist.add(key.charAt(i));
        }
        for (int i = 0; i < alphabet.size(); i++) {
            if (!keylist.contains(alphabet.get(i)))
                keylist.add(alphabet.get(i));
        }
        String ans = "";
        for (int i = 0; i < text.length(); i++) {
            int index = alphabet.indexOf(text.charAt(i));
            ans = ans + keylist.get(index);
        }

        return ans;
    }

    public static String decipher(String text, String key) {
        ArrayList<Character> alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {

            alphabet.add((char) (97 + i));
        }
        ArrayList<Character> keylist = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            if (!keylist.contains(key.charAt(i)))
                keylist.add(key.charAt(i));
        }
        for (int i = 0; i < alphabet.size(); i++) {
            if (!keylist.contains(alphabet.get(i)))
                keylist.add(alphabet.get(i));
        }
        String ans = "";
        for (int i = 0; i < text.length(); i++) {
            int index = keylist.indexOf(text.charAt(i));
            ans = ans + alphabet.get(index);
        }

        return ans;
    }
}

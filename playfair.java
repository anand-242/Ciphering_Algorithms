import java.util.*;

public class playfair {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter the text");
        String text = sc.nextLine().toLowerCase().trim();
        System.out.println("enter the key");
        String key = sc.nextLine().toLowerCase().trim();
        System.out.println("enter 0 for encryption and 1 for decryption");
        int choice = sc.nextInt();
        switch (choice) {
            case 0:
                String ans = cipher(text, key);
                System.out.println(ans);
                break;
            case 1:
                String ans2 = decipher(text, key);
                System.out.println(ans2);
                break;
            default:
                System.out.println("wrong choice");
                break;

        }

    }

    public static String cipher(String text, String key) {
        ArrayList<Character> set = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            if (!set.contains(key.charAt(i)))
                set.add(key.charAt(i));
        }

        ArrayList<Character> alphabet = new ArrayList<>();
        for (Character ch : set) {

            alphabet.add(ch);
        }

        for (int i = 0; i < 26; i++) {
            if (!alphabet.contains((char) (97 + i)))
                alphabet.add((char) (97 + i));
        }
        if (set.contains('i'))
            alphabet.remove(alphabet.indexOf('j'));
        else if (set.contains('j'))
            alphabet.remove(alphabet.indexOf('i'));
        else if (set.contains('j') && set.contains('i'))
            alphabet.remove(alphabet.indexOf('j'));
        else if (!set.contains('j') && !set.contains('i'))
            alphabet.remove(alphabet.indexOf('j'));

        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();

        ArrayList<Character> tempalpha = new ArrayList<>();
        for (Character t : alphabet) {
            tempalpha.add(t);
        }
        for (int i = 0; i < 5; i++) {
            ArrayList<Character> temp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Character character = tempalpha.remove(0);
                temp.add(character);
            }
            matrix.add(temp);
        }
        String rep = "";
        for (int i = 0; i < text.length() - 1; i++) {
            rep = rep + text.charAt(i);
            if (text.charAt(i + 1) == text.charAt(i))
                rep = rep + 'x';
        }
        rep = rep + text.charAt(text.length() - 1);
        if (rep.length() % 2 != 0)
            rep = rep + 'x';
        System.out.println(matrix);
        String ans = "";
        for (int i = 0; i < rep.length(); i += 2) {
            int index1 = alphabet.indexOf(rep.charAt(i));
            int row1 = index1 / 5;
            int col1 = index1 % 5;

            int index2 = alphabet.indexOf(rep.charAt(i + 1));
            int row2 = index2 / 5;
            int col2 = index2 % 5;

            if (col1 == col2) {
                int r1 = (row1 + 1) % 5;
                int r2 = (row2 + 1) % 5;

                char ch1 = matrix.get(r1).get(col1);
                char ch2 = matrix.get(r2).get(col1);

                ans = ans + ch1 + ch2;

            } else if (row1 == row2) {
                int c1 = (col1 + 1) % 5;
                int c2 = (col2 + 1) % 5;
                char ch1 = matrix.get(row1).get(c1);
                char ch2 = matrix.get(row1).get(c2);
                ans = ans + ch1 + ch2;

            } else {
                char ch1 = matrix.get(row1).get(col2);
                char ch2 = matrix.get(row2).get(col1);
                ans = ans + ch1 + ch2;

            }
        }

        return ans;
    }

    public static String decipher(String text, String key) {
        ArrayList<Character> set = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            if (!set.contains(key.charAt(i)))
                set.add(key.charAt(i));
        }

        ArrayList<Character> alphabet = new ArrayList<>();
        for (Character ch : set) {

            alphabet.add(ch);
        }
        System.out.println(alphabet);
        for (int i = 0; i < 26; i++) {
            if (!alphabet.contains((char) (97 + i)))
                alphabet.add((char) (97 + i));
        }
        if (set.contains('i'))
            alphabet.remove(alphabet.indexOf('j'));
        else if (set.contains('j'))
            alphabet.remove(alphabet.indexOf('i'));
        else if (set.contains('j') && set.contains('i'))
            alphabet.remove(alphabet.indexOf('j'));
        else if (!set.contains('j') && !set.contains('i'))
            alphabet.remove(alphabet.indexOf('j'));
        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();

        ArrayList<Character> tempalpha = new ArrayList<>();
        for (Character t : alphabet) {
            tempalpha.add(t);
        }
        for (int i = 0; i < 5; i++) {
            ArrayList<Character> temp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Character character = tempalpha.remove(0);
                temp.add(character);
            }
            matrix.add(temp);
        }
        String rep = "";
        for (int i = 0; i < text.length() - 1; i++) {
            rep = rep + text.charAt(i);
            if (text.charAt(i + 1) == text.charAt(i))
                rep = rep + 'x';
        }
        rep = rep + text.charAt(text.length() - 1);
        if (rep.length() % 2 != 0)
            rep = rep + 'x';

        String ans = "";
        for (int i = 0; i < rep.length(); i += 2) {
            int index1 = alphabet.indexOf(rep.charAt(i));
            int row1 = index1 / 5;
            int col1 = index1 % 5;

            int index2 = alphabet.indexOf(rep.charAt(i + 1));
            int row2 = index2 / 5;
            int col2 = index2 % 5;

            if (col1 == col2) {
                int r1 = (row1 - 1) % 5;
                int r2 = (row2 - 1) % 5;
                if (r1 < 0)
                    r1 += 5;
                if (r2 < 0)
                    r2 += 5;
                char ch1 = matrix.get(r1).get(col1);
                char ch2 = matrix.get(r2).get(col1);

                ans = ans + ch1 + ch2;

            } else if (row1 == row2) {
                int c1 = (col1 - 1) % 5;
                int c2 = (col2 - 1) % 5;
                if (c1 < 0)
                    c1 += 5;
                if (c2 < 0)
                    c2 += 5;
                char ch1 = matrix.get(row1).get(c1);
                char ch2 = matrix.get(row1).get(c2);
                ans = ans + ch1 + ch2;

            } else {
                char ch1 = matrix.get(row1).get(col2);
                char ch2 = matrix.get(row2).get(col1);
                ans = ans + ch1 + ch2;

            }
        }

        return ans;
    }
}

public class VignereCipher {
    public static void main(String[] args) {
        String ans = cipher("javatpoint", "best");
        System.out.println(ans);
    }

    public static String cipher(String text, String key) {
        int len = text.length();
        String rep = key;
        while (rep.length() < len) {
            rep = rep + key;
        }
        return rep;
    }
}

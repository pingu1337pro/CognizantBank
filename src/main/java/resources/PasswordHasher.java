package resources;

import java.util.Arrays;

public class PasswordHasher {

    public static String hashPassword(String password) {
        String hashedPassword = "";
        for (int i = 0; i < password.length(); i++) {
            hashedPassword += (char) (password.charAt(i) + 1);
        }
        return hashedPassword;
    }

    public static int hashPassword2(char[] password) {
       return Arrays.hashCode(password);
    }
}

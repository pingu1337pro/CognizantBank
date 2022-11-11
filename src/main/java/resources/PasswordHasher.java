package resources;

public class PasswordHasher {

    public static String hashPassword(String password) {
        String hashedPassword = "";
        for (int i = 0; i < password.length(); i++) {
            hashedPassword += (char) (password.charAt(i) + 1);
        }
        return hashedPassword;
    }
}

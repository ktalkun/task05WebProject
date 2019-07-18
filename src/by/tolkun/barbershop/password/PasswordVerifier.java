package by.tolkun.barbershop.password;

import java.util.Optional;


public class PasswordVerifier {
    public static boolean verifyPassword (String password, String key, String salt) {
        Optional<String> optEncrypted = HashGenerator.hashPassword(password, salt);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);
    }
}

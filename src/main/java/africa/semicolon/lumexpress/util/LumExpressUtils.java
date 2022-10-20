package africa.semicolon.lumexpress.util;

import java.security.SecureRandom;

public class LumExpressUtils {

    public static String generateToken(){
        SecureRandom secureRandom = new SecureRandom();
        return String
                .valueOf(10000+secureRandom.nextInt(89999));
    }

    public static String getMockCloudinaryImageUrl(){
        return "https://www.cloudinary.com/abcd";
    }
}

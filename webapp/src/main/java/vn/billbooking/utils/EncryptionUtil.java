package vn.billbooking.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {

    public static String encoderStringToBase64(String text) {
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedString = encoder.encodeToString(text.getBytes(StandardCharsets.UTF_8) );
            return encodedString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String decoderBase64ToString(String text) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedByteArray = decoder.decode(text);
            return new String(decodedByteArray);
        } catch (Exception ex) {
            return null;
        }
    }

}

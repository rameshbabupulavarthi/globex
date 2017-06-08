package com.utils;

import com.globex.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

/**
 * Created by Sunil Golla on 1/11/2017.
 */

@SuppressWarnings("restriction")
public class EncryptionUtils {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtils.class);
    public static final String CHARACTER_SET = "UTF-8";
    private static long salt;
    private static Random rand = new Random((new Date()).getTime());

    public EncryptionUtils(long salt) {
        EncryptionUtils.salt = salt;
    }

    private static Long encrypt(long l){
        return ( (((0x0000FFFF & l)<<16)|((0xFFFF0000 &l)>>16) ) ^ salt );
    }

    private static Long decrypt(long l){
        long a=( l ^ salt );
        return (((0x0000FFFF & a)<<16)|((0xFFFF0000 &a)>>16) );
    }

    private static String encryptAndEncode(Long unencrypted){

        Long encVal = unencrypted;
        String encrypted = null;

        try {

            if(encVal != null){

                encVal = encrypt(encVal);
                byte[] plainText = encVal.toString().getBytes(CHARACTER_SET);
                BASE64Encoder base64encoder = new BASE64Encoder();
                encrypted = base64encoder.encode(plainText);
                encrypted = URLEncoder.encode(encrypted, CHARACTER_SET);

            }

        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error("Exception ", e);
        }

        return encrypted;

    }


    private static Long decodeAndDecrypt(String encryptedString){

        String decVal = encryptedString;
        Long decrypted = null;

        try {

            if(decVal != null && !decVal.equals("")){

                decVal = URLDecoder.decode(decVal, CHARACTER_SET);
                BASE64Decoder base64decoder = new BASE64Decoder();
                byte[] decryptedText = base64decoder.decodeBuffer(decVal);
                decVal = new String(decryptedText, CHARACTER_SET);
                decrypted = Long.parseLong(decVal);
                decrypted = decrypt(decrypted);

            }

        } catch (Exception e) {

            logger.error("Value passed for decryption *" + encryptedString + "*");

            if (logger.isErrorEnabled())
                logger.error("Exception ", e);

            throw new RuntimeException(AppConstants.CRYPTO_EXCEPTION);
        }

        return decrypted;

    }

    public static String encryptLong(Long valToEnc){

        return encryptAndEncode(valToEnc);

    }

    public static String encryptInteger(Integer valToEnc){

        String value = null;

        if (valToEnc != null) {
            value = encryptAndEncode(valToEnc.longValue());
        }

        return value;

    }



    public static String encrypt(String str) {

        BASE64Encoder encoder = new BASE64Encoder();

        byte[] salt = new byte[8];

        rand.nextBytes(salt);

        return encoder.encode(salt) + encoder.encode(str.getBytes());
    }

    public static String decrypt(String encstr) {

        if (encstr.length() > 12) {

            String cipher = encstr.substring(12);

            BASE64Decoder decoder = new BASE64Decoder();

            try {

                return new String(decoder.decodeBuffer(cipher));

            } catch (IOException e) {

                if (logger.isErrorEnabled())
                    logger.error("Exception ", e);
            }
        }
        return null;
    }

}

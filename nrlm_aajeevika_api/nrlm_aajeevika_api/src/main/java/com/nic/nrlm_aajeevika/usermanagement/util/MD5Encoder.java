package com.nic.nrlm_aajeevika.usermanagement.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class MD5Encoder {

    public static String getMD5Hash(String password) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            byte[] messageByte = messageDigest.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageByte);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e){
            e.getMessage();
            return null;
        }
    }
}

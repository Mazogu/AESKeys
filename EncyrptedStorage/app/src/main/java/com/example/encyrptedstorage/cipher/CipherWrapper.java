package com.example.encyrptedstorage.cipher;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public class CipherWrapper {
    private Cipher cipher;
    public static final String TRANSFORMATION = "AES/ECB/NoPadding";

    public void init() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(TRANSFORMATION);
    }

    String encrypt(String plainText, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encyptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.encodeToString(encyptedBytes, Base64.DEFAULT);
    }

    String decrypt(String encrpytedText, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] encryptedBytes = Base64.decode(encrpytedText.getBytes(), Base64.DEFAULT);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}

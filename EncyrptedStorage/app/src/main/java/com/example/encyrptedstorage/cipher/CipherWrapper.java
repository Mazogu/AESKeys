package com.example.encyrptedstorage.cipher;

import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.GCMParameterSpec;

public class CipherWrapper {
    private Cipher cipher;
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";

    public void init() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(TRANSFORMATION);
    }

    public String encrypt(String plainText, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.ENCRYPT_MODE,key,getGCMSpec());
        byte[] encyptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.encodeToString(encyptedBytes, Base64.DEFAULT);
    }

    public String decrypt(String encrpytedText, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, ShortBufferException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.DECRYPT_MODE,key,getGCMSpec());
        byte[] encryptedBytes = Base64.decode(encrpytedText.getBytes(), Base64.DEFAULT);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    private GCMParameterSpec getGCMSpec() throws NoSuchAlgorithmException {
        SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] iv = new byte[12];
        randomSecureRandom.nextBytes(iv);
        return new GCMParameterSpec(96, iv);
    }
}

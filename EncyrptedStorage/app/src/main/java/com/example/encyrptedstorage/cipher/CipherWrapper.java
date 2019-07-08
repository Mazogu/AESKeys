package com.example.encyrptedstorage.cipher;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;


/**
 * Class for creating cipher.
 */
public class CipherWrapper {
    private Cipher cipher;
    private final static int GCM_TAG = 16;
    private final static int GCM_IV = 12;
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";

    /**
     * Provides an instance of the cipher.
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public void init() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(TRANSFORMATION);
    }

    /**
     * Uses GCM based on a random initialization vector to create an encrypted string.
     * @param plainText plain text to encrypt
     * @param key Secret key provided by keystore.
     * @return encrypted string.
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws UnsupportedEncodingException
     */
    public String encrypt(String plainText, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        byte[] iv = new byte[GCM_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG * Byte.SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE,key,spec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
        byte[] encryptedBytes = new byte[cipherText.length + iv.length];
        System.arraycopy(iv,0,encryptedBytes,0,iv.length);
        System.arraycopy(cipherText,0,encryptedBytes,iv.length,cipherText.length);
        return Base64.encodeToString(encryptedBytes,Base64.DEFAULT);
    }

    /**
     * Decodes encoded string.
     * @param encryptedText Encrypted text stored in the database.
     * @param key Secret Key from keystore.
     * @return Plain text.
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws UnsupportedEncodingException
     */
    public String decrypt(String encryptedText, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        byte[] decoded = Base64.decode(encryptedText, Base64.DEFAULT);
        byte[] iv = Arrays.copyOfRange(decoded,0,GCM_IV);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG * Byte.SIZE,iv);
        cipher.init(Cipher.DECRYPT_MODE,key,spec);
        byte[] cipherText = cipher.doFinal(decoded,GCM_IV,decoded.length - GCM_IV);
        return new String(cipherText, "UTF8");
    }
}

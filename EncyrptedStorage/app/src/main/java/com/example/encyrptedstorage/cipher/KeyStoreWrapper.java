package com.example.encyrptedstorage.cipher;


import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;

/**
 * Class for handling key generation.
 */
public class KeyStoreWrapper {
    private static final String MY_ALGORITHM = "AES";
    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private KeyStore keyStore;
    private KeyGenerator generator;

    public KeyStoreWrapper() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER);
        keyStore.load(null);
    }

    /**
     * Creates a secret key and adds it to the keystore.
     * @param alias Alias string to generate key
     * @return
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     */
    public Key createSecretKey(String alias) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, UnrecoverableKeyException {
        if(keyStore.containsAlias(alias))
            return keyStore.getKey(alias, null);
        generator = KeyGenerator.getInstance(MY_ALGORITHM, KEYSTORE_PROVIDER);
        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT
                | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setRandomizedEncryptionRequired(false)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build();
        generator.init(spec);
        return generator.generateKey();
    }

    /**
     * Returns key based on the alias.
     * @param alias
     * @return Secret Key
     * @throws UnrecoverableEntryException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    public Key getKey(String alias) throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException {
        KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(alias,null);
        return secretKeyEntry.getSecretKey();
    }
}

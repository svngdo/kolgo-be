package com.dtu.kolgo.vnpay;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public enum HashAlgorithm {
    SHA256("HmacSHA256"),
    SHA512("HmacSHA512");

    private final String algorithm;

    HashAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] hash(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data);
    }

    public static byte[] HMAC_SHA256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        return SHA256.hash(data, key);
    }

    public static byte[] HMAC_SHA512(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        return SHA512.hash(data, key);
    }

}

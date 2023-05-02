package com.dtu.kolgo.vnpay;

//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class VnPayClient {

    private final String paymentUrl;
    private final String merchantId;
    private final String hashSecret;

    public VnPayClient(String paymentUrl, String merchantId, String hashSecret) {
        this.paymentUrl = paymentUrl;
        this.merchantId = merchantId;
        this.hashSecret = hashSecret;
    }

    public String createRequestUrl(Map<String, String> params) {
        Map<String, String> sortedParams = new TreeMap<>(params);

        StringBuilder data = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            data.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        data.deleteCharAt(data.length() - 1);

        String secureHash = createSecureHash(data.toString());

        sortedParams.put("vnp_SecureHashType", "SHA256");
        sortedParams.put("vnp_SecureHash", secureHash);

        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(paymentUrl);
        requestUrl.append("?");
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            requestUrl.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8)).append("&");
        }
        requestUrl.deleteCharAt(requestUrl.length() - 1);

        return requestUrl.toString();
    }

    public boolean verifyResponseUrl(Map<String, String> params) {
        String vnp_SecureHash = params.get("vnp_SecureHash");
        params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");

        Map<String, String> sortedParams = new TreeMap<>(params);

        StringBuilder data = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            data.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        data.deleteCharAt(data.length() - 1);

        String expectedSecureHash = createSecureHash(data.toString());

        return vnp_SecureHash.equals(expectedSecureHash);
    }

    private String createSecureHash(String data) {
//        try {
//            byte[] hashData = HashAlgorithm.HASH_SHA256(data.getBytes(StandardCharsets.UTF_8), hashSecret.getBytes(StandardCharsets.UTF_8));
//            return Hex.encodeHexString(hashData).toUpperCase();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    private enum HashAlgorithm {
        SHA256("HmacSHA256");

        private final String algorithm;

        HashAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public byte[] hash(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(data);
        }

        public static byte[] HASH_SHA256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
            return SHA256.hash(data, key);
        }
    }

}


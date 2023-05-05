package com.dtu.kolgo.vnpay;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

@Component
public class VnPayUtil {

    @Value("${vnpay.payment-url}")
    private String paymentUrl;

    @Value("${vnpay.hash-secret}")
    private String hashSecret;

    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getLocalAddr();
        }
        return ipAddress;
    }

    private String createQueryString(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                query
                        .append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                        .append("&");
            }
        }
        query.deleteCharAt(query.length() - 1);
        return query.toString();
    }

    public String createDataString(Map<String, String> params) {
        StringBuilder data = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                data
                        .append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), StandardCharsets.US_ASCII))
                        .append("&");

            }
        }
        data.deleteCharAt(data.length() - 1);
        return data.toString();
    }

    public String createSecureHash(String data) {
        try {
            byte[] hashData =
                    HashAlgorithm.HMAC_SHA512(data.getBytes(StandardCharsets.UTF_8), hashSecret.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hashData).toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String createRequestUrl(Map<String, String> params) {
        Map<String, String> sortedParams = new TreeMap<>(params);

        String dataString = createDataString(sortedParams);
        String vnp_SecureHash = createSecureHash(dataString);

        return paymentUrl
                + "?"
                + createQueryString(sortedParams)
                + "&vnp_SecureHash=" + vnp_SecureHash;
    }

}

package com.dtu.kolgo.vnpay;

import com.dtu.kolgo.exception.InvalidException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class VnPayService {

    @Value("${vnpay.version}")
    private String version;

    @Value("${vnpay.merchant-id}")
    private String merchantId;


    @Value("${vnpay.return-url}")
    private String returnUrl;

    @Value("${vnpay.cancel-url}")
    private String cancelUrl;

    private final VnPayUtil vnPayUtil;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            String locale = req.getParameter("language");
            String amountString = req.getParameter("amount");
            BigDecimal amount = new BigDecimal(amountString);
            String orderInfo = req.getParameter("orderInfo");
            String orderType = req.getParameter("orderType");

            if (amountString.length() == 0) {
                throw new InvalidException("Invalid amount");
            }

            Map<String, String> vnpParams = new HashMap<>();
            String vnp_Version = version;
            String vnp_Command = "pay";
            String vnp_TmnCode = merchantId;
            String vnp_Amount = amount.multiply(BigDecimal.valueOf(100)).toString();
            String vnp_BankCode = req.getParameter("bankCode");
            String vnp_CreateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String vnp_CurrCode = "VND";
            String vnp_IpAddr = vnPayUtil.getIpAddress(req);
            String vnp_Locale = (locale != null && !locale.isEmpty()) ? locale : "vn";
            String vnp_OrderInfo = (orderInfo != null && !orderInfo.isEmpty()) ? orderInfo : "Thanh toan dich vu";
            String vnp_OrderType = (orderType != null && !orderType.isEmpty()) ? orderType : "Other";
            String vnp_ReturnUrl = returnUrl;
            String vnp_ExpireDate = LocalDateTime.now().plusMinutes(15).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String vnp_TxnRef = req.getParameter("txnRef");

            if (vnp_TxnRef == null || vnp_TxnRef.length() == 0) {
                throw new InvalidException("Invalid txnRef");
            }

            vnpParams.put("vnp_Version", vnp_Version);
            vnpParams.put("vnp_Command", vnp_Command);
            vnpParams.put("vnp_TmnCode", vnp_TmnCode);
            vnpParams.put("vnp_Amount", vnp_Amount);
            vnpParams.put("vnp_BankCode", vnp_BankCode);
            vnpParams.put("vnp_CreateDate", vnp_CreateDate);
            vnpParams.put("vnp_CurrCode", vnp_CurrCode);
            vnpParams.put("vnp_IpAddr", vnp_IpAddr);
            vnpParams.put("vnp_Locale", vnp_Locale);
            vnpParams.put("vnp_OrderInfo", vnp_OrderInfo);
            vnpParams.put("vnp_OrderType", vnp_OrderType);
            vnpParams.put("vnp_ReturnUrl", vnp_ReturnUrl);
            vnpParams.put("vnp_ExpireDate", vnp_ExpireDate);
            vnpParams.put("vnp_TxnRef", vnp_TxnRef);

            String paymentUrl = vnPayUtil.createRequestUrl(vnpParams);
            log.info(paymentUrl);
            Map<String, Object> response = new HashMap<>() {{
                put("code", "00");
                put("message", "success");
                put("paymentUrl", paymentUrl);
            }};
            log.info(response.toString());
            final ObjectMapper mapper = new ObjectMapper();
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(res.getOutputStream(), response);
        } catch (Exception e) {
            log.error("VNPayService Exception", e);
        }
    }


}

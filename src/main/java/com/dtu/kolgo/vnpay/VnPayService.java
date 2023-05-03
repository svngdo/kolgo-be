package com.dtu.kolgo.vnpay;

import com.dtu.kolgo.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

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
            String amount = req.getParameter("amount");
            String orderInfo = req.getParameter("orderInfo");
            String orderType = req.getParameter("orderType");

            Map<String, String> vnpParams = new HashMap<>();
            String vnp_Version = version;
            String vnp_Command = "pay";
            String vnp_TmnCode = merchantId;
            String vnp_Amount = String.valueOf((amount != null && !amount.isEmpty()) ? Integer.parseInt(amount) * 100 : 10000000); // req.getParameter("amount")
            String vnp_BankCode = req.getParameter("bankCode");
            String vnp_CreateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String vnp_CurrCode = "VND";
            String vnp_IpAddr = vnPayUtil.getIpAddress(req);
            String vnp_Locale = (locale != null && !locale.isEmpty()) ? locale : "vn";
            String vnp_OrderInfo = (orderInfo != null && !orderInfo.isEmpty()) ? orderInfo : "Thanh toan dich vu";
            String vnp_OrderType = (orderType != null && !orderType.isEmpty()) ? orderType : "Other";
            String vnp_ReturnUrl = returnUrl;
            String vnp_ExpireDate = LocalDateTime.now().plusMinutes(15).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String vnp_TxnRef = String.valueOf(System.currentTimeMillis());

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
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setData(new HashMap<>() {{
                put("code", "00");
                put("message", "success");
                put("paymentUrl", paymentUrl);
            }});
            log.info(apiResponse.toString());
            final ObjectMapper mapper = new ObjectMapper();
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        res.setStatus(statusCode);
            mapper.writeValue(res.getOutputStream(), apiResponse);
        } catch (Exception e) {
            log.error("VNPayService Exception", e);
        }
    }


}

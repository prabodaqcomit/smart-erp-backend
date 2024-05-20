package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

@Data
public class GLPaymentFilterSearchRequest {

    private String dateFrom;

    private String dateTo;

    private String payeeName;

    private String voucherNumber;

    private String amount;

}
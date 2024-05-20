package lk.quantacom.smarterpbackend.dto.response;

import lk.quantacom.smarterpbackend.entity.Tblinvhed;
import lombok.Data;

@Data
public class ReceiptSettleInvoiceResponse {

    private Tblinvhed invoiceHeader;

    private Double pendingAmount;



}

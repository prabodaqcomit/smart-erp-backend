package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.response.ReceiptSettleInvoiceResponse;
import lk.quantacom.smarterpbackend.dto.response.TblinvhedResponse;
import lk.quantacom.smarterpbackend.entity.ReceiptSettleInvoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReceiptSettleInvoiceService {

    ReceiptSettleInvoice save(ReceiptSettleInvoice request);

    ReceiptSettleInvoice update(ReceiptSettleInvoice request);

    //List<TblinvhedResponse> getPendingCreditInvoicesByCustomerId(Long customerId);

    //ReceiptSettleInvoice getLastPaidReceiptByInvoiceLocationAndInvoiceNumber(String invoiceLocation, String invoiceNumber);

    List<ReceiptSettleInvoiceResponse> getPendingCreditInvoicesByCustomerId(Long customerId);

    ReceiptSettleInvoice getById(Long id);

    List<ReceiptSettleInvoice> getAll();

    Integer delete(Long id);
}
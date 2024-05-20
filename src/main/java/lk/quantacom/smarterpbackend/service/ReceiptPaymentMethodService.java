package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ReceiptDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptPaymentMethodRequest;
import lk.quantacom.smarterpbackend.dto.response.ReceiptDetailResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptPaymentMethodResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReceiptPaymentMethodService {

//    ReceiptPaymentMethodResponse save(ReceiptPaymentMethodRequest request);
//
//    ReceiptPaymentMethodResponse update(ReceiptPaymentMethodRequest request);

    ReceiptPaymentMethodResponse getById(Long id);

    List<ReceiptPaymentMethodResponse> getAll();

    List<ReceiptPaymentMethodResponse> getByReceiptHeaderId(Long id);

    Integer delete(Long id);
}
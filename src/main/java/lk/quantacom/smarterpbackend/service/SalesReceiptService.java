package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.SalesReceiptRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesReceiptUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SalesReceiptResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesReceiptService {

    SalesReceiptResponse save(SalesReceiptRequest request);

    SalesReceiptResponse update(SalesReceiptUpdateRequest request);

    SalesReceiptResponse getById(Long id);

    List<SalesReceiptResponse> getAll();


    Integer delete(Long id);
}
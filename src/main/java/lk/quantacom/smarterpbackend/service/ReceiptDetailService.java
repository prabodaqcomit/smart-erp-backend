package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ReceiptDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ReceiptDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReceiptDetailService {

    ReceiptDetailResponse save(ReceiptDetailRequest request);

    ReceiptDetailResponse update(ReceiptDetailUpdateRequest request);

    ReceiptDetailResponse getById(Long id);

    List<ReceiptDetailResponse> getAll();

    List<ReceiptDetailResponse> getByReceiptHeaderId(Long id);


    Integer delete(Long id);
}
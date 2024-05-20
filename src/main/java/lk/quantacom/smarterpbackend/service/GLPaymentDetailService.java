package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GLPaymentDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GLPaymentDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GLPaymentDetailService {

    GLPaymentDetailResponse save(GLPaymentDetailRequest request);

    GLPaymentDetailResponse update(GLPaymentDetailUpdateRequest request);

    GLPaymentDetailResponse getById(Long id);

    List<GLPaymentDetailResponse> getAll();

    Integer delete(Long id);

}
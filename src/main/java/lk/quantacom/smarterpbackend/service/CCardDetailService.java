package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.CCardDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.CCardDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CCardDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CCardDetailService {

    CCardDetailResponse save(CCardDetailRequest request);

    CCardDetailResponse update(CCardDetailUpdateRequest request);

    CCardDetailResponse getById(String id);

    List<CCardDetailResponse> getAll();

    Integer delete(String id);
}
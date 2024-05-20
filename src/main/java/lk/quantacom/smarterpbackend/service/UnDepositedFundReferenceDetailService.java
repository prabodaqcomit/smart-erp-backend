package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UnDepositedFundReferenceDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnDepositedFundReferenceDetailService {

//    UnDepositedFundReferenceDetailResponse save(UnDepositedFundReferenceDetailRequest request);
//
//    UnDepositedFundReferenceDetailResponse update(UnDepositedFundReferenceDetailUpdateRequest request);

    UnDepositedFundReferenceDetailResponse getById(Long id);

    List<UnDepositedFundReferenceDetailResponse> getAll();

//    Integer delete(Long id);
}
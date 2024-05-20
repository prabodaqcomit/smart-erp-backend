package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.response.BankDepositDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankDepositDetailService {

//    BankDepositDetailResponse save(BankDepositDetailRequest request);
//
//    BankDepositDetailResponse update(BankDepositDetailUpdateRequest request);

    BankDepositDetailResponse getById(Long id);

    List<BankDepositDetailResponse> getAll();


//    Integer delete(Long id);
}
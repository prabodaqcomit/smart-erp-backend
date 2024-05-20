package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BankCardTypeDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.BankCardTypeDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankCardTypeDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankCardTypeDetailService {

    BankCardTypeDetailResponse save(BankCardTypeDetailRequest request);

    BankCardTypeDetailResponse update(BankCardTypeDetailUpdateRequest request);

    BankCardTypeDetailResponse getById(Long id);

    List<BankCardTypeDetailResponse> getAll();


    Integer delete(Long id);
}
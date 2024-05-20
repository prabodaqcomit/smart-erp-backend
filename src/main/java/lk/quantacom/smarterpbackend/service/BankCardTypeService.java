package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BankCardTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.BankCardTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankCardTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankCardTypeService {

    BankCardTypeResponse save(BankCardTypeRequest request);

    BankCardTypeResponse update(BankCardTypeUpdateRequest request);

    BankCardTypeResponse getById(Long id);

    List<BankCardTypeResponse> getAll();


    Integer delete(Long id);
}
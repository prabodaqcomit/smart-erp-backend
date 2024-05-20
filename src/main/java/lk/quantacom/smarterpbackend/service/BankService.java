package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BankRequest;
import lk.quantacom.smarterpbackend.dto.request.BankUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {

    BankResponse save(BankRequest request);

    BankResponse update(BankUpdateRequest request);

    BankResponse getById(Long id);

    List<BankResponse> getAll();


    Integer delete(Long id);
}
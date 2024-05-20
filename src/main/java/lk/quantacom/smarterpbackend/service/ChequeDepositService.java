package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ChequeDepositAndReceivedRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeDepositRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeDepositUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ChequeDepoReq;
import lk.quantacom.smarterpbackend.dto.response.ChequeDepositResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChequeDepositService {

    ChequeDepositResponse save(ChequeDepositRequest request);

    ChequeDepositResponse update(ChequeDepositUpdateRequest request);

    ChequeDepositResponse getById(Long id);

    List<ChequeDepositResponse> getAll();

    Integer delete(Long id);

    List<ChequeDepositResponse> saveAll(List<ChequeDepositAndReceivedRequest> requests);

    List<ChequeDepoReq> getForChRtn();

}
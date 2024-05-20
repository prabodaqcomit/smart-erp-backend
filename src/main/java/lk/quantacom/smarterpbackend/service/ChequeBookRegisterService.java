package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterListRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ChequeBookRegisterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChequeBookRegisterService {

    ChequeBookRegisterResponse save(ChequeBookRegisterRequest request);

    List<ChequeBookRegisterResponse> saveList(List<ChequeBookRegisterListRequest> requests);

    ChequeBookRegisterResponse update(ChequeBookRegisterUpdateRequest request);

    ChequeBookRegisterResponse getById(Long id);

    List<ChequeBookRegisterResponse> getAll();

    List<ChequeBookRegisterResponse> getByBankAccountId(String bankAccountId);

    Integer delete(Long id);
}
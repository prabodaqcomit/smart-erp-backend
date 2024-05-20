package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.OwnChequeReturnsRequest;
import lk.quantacom.smarterpbackend.dto.request.OwnChequeReturnsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnChequeReturnsService {

    OwnChequeReturnsResponse save(OwnChequeReturnsRequest request);

    OwnChequeReturnsResponse update(OwnChequeReturnsUpdateRequest request);

    OwnChequeReturnsResponse getById(Long id);

    List<OwnChequeReturnsResponse> getAll();


    Integer delete(Long id);

    List<OwnChequeReturnsResponse> findByChequeNo(String chequeNo);

    List<OwnChequeReturnsResponse> findByChequeNoAndBranchId(String chequeNo,String branchId);

    List<getByPayeeNameResponse> getByPayeeName(String payeeName);

    List<getByChequeNoResponse> getByChequeNo(String chequeNo);

    List<getByBankCodeResponse> getByBankCode(String bankCode);

    List<getByBranchCodeResponse> getByBranchCode(String BranchCode);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ChequeIssueNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeIssueNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ChequeIssueNoteResponse;
import lk.quantacom.smarterpbackend.entity.ChequeIssueNote;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ChequeIssueNoteService {

    ChequeIssueNoteResponse save(ChequeIssueNoteRequest request);

    ChequeIssueNoteResponse update(ChequeIssueNoteUpdateRequest request);

    ChequeIssueNoteResponse getById(Long id);

    List<ChequeIssueNoteResponse> getAll();

    Integer delete(Long id);

    List<ChequeIssueNoteResponse> getByUpdateWindow(String updateWindow);

    List<ChequeIssueNoteResponse> getByPayeeName(String payeeName);

    List<ChequeIssueNoteResponse> getByAccName(String accName);

    List<ChequeIssueNoteResponse> getByBankCode(String bankCode);

    List<ChequeIssueNoteResponse> findByBranchCode(String branchCode);

    List<ChequeIssueNoteResponse> findByIsDeletedAndChequeNo(String chequeNo);

    List<String> getByAccNo(String chequeNo);



    List<ChequeIssueNoteResponse> findByPayeeNameAndBranchCodeAndIssueDateBetween(String payeeName, String Branch, String from, String to);

    List<ChequeIssueNoteResponse> findByUpdateWindowAndBranchCodeAndIssueDateBetween(String updateWindow,String Branch,String from,String to);

    List<ChequeIssueNoteResponse> findByBankCodeAndBranchCodeAndIssueDateBetween(String bankCode,String Branch,String from,String to);

    List<ChequeIssueNoteResponse> findByChequeNoAndBranchCodeAndIssueDateBetween(String branchCode,String Branch,String from,String to);

    List<ChequeIssueNoteResponse> findByAccNameAndBranchCodeAndIssueDateBetween(String accName,String Branch,String from,String to);

    List<ChequeIssueNoteResponse> findByAccNameAndBranchCodeAndIssueDateBetween(String Branch,String from,String to);

    List<ChequeIssueNoteResponse> findByAccNameAndBranchCodeAndChequeDateBetween(String Branch,String from,String to);
}
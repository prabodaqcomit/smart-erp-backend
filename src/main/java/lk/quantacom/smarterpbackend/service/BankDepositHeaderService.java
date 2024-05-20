package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BankDepositHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.BankDepositHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.BankDepositHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.response.BankDepositHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.BankDepositHeaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface BankDepositHeaderService {

    BankDepositHeaderDocumentNumberResponse getNextDocumentNumber();

    BankDepositHeaderResponse save(BankDepositHeaderRequest request);

    BankDepositHeaderResponse update(BankDepositHeaderUpdateRequest request);

    BankDepositHeaderResponse getById(Long id);

    List<BankDepositHeaderResponse> getAll();

    Page<BankDepositHeaderResponse> getPaginatedAll(int pageNumber, int countPerPage);

    Page<BankDepositHeaderResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage);

    Page<BankDepositHeaderResponse> getSearchPaginated(int pageNumber, int countPerPage, BankDepositHeaderSearchRequest searchRequest);

    Page<BankDepositHeaderResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, BankDepositHeaderSearchRequest searchRequest);

    File printSingle(Long id);

    //Integer delete(Long id);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.AccountTransferRequest;
import lk.quantacom.smarterpbackend.dto.request.AccountTransferSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.AccountTransferUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.response.AccountTransferDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.AccountTransferResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface AccountTransferService {

    AccountTransferDocumentNumberResponse getNextDocumentNumber();

    AccountTransferResponse save(AccountTransferRequest request);

    AccountTransferResponse update(AccountTransferUpdateRequest request);

    AccountTransferResponse getById(Long id);

    List<AccountTransferResponse> getAll();

    Page<AccountTransferResponse> getPaginatedAll(int pageNumber, int countPerPage);

    Page<AccountTransferResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage);

    Page<AccountTransferResponse> getSearchPaginated(int pageNumber, int countPerPage, AccountTransferSearchRequest searchRequest);

    Page<AccountTransferResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, AccountTransferSearchRequest searchRequest);

    File printSingle(Long id);

    Integer delete(Long id);

}
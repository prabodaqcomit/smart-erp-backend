package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.List;

@Service
public interface GeneralJournalHeaderService {

    GeneralJournalHeaderDocumentNumberResponse getNextDocumentNumber();

    GeneralJournalHeaderResponse save(GeneralJournalHeaderRequest request);

    GeneralJournalHeaderResponse update(GeneralJournalHeaderUpdateRequest request);

    GeneralJournalHeaderResponse getById(Long id);

    List<GeneralJournalHeaderResponse> getAll();

    Page<GeneralJournalHeaderResponse> getPaginatedAll(int pageNumber, int countPerPage);

    Page<GeneralJournalHeaderResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage);

    Page<GeneralJournalHeaderResponse> getSearchPaginated(int pageNumber, int countPerPage, GeneralJournalHeaderSearchRequest searchRequest);

    Page<GeneralJournalHeaderResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, GeneralJournalHeaderSearchRequest searchRequest);

    File printSingle(Long id);

    Integer delete(Long id);
}
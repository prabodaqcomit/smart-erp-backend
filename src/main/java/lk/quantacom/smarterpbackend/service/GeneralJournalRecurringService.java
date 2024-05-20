package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalRecurringResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeneralJournalRecurringService {

    GeneralJournalRecurringResponse save(GeneralJournalRecurringRequest request);

    GeneralJournalRecurringResponse update(GeneralJournalRecurringUpdateRequest request);

    GeneralJournalRecurringResponse getById(Long id);

    List<GeneralJournalRecurringResponse> getAll();

    Page<GeneralJournalRecurringResponse> getPaginatedAll(int pageNumber, int countPerPage);

    Page<GeneralJournalRecurringResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage);

    Page<GeneralJournalRecurringResponse> getSearchPaginated(int pageNumber, int countPerPage, GeneralJournalRecurringSearchRequest searchRequest);

    Page<GeneralJournalRecurringResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, GeneralJournalRecurringSearchRequest searchRequest);

    Integer delete(Long id);
}
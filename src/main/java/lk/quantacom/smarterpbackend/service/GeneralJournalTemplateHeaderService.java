package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalTemplateHeaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeneralJournalTemplateHeaderService {

    GeneralJournalTemplateHeaderResponse save(GeneralJournalTemplateHeaderRequest request);

    GeneralJournalTemplateHeaderResponse update(GeneralJournalTemplateHeaderUpdateRequest request);

    GeneralJournalTemplateHeaderResponse getById(Long id);

    List<GeneralJournalTemplateHeaderResponse> getAll();

    Page<GeneralJournalTemplateHeaderResponse> getPaginatedAll(int pageNumber, int countPerPage);

    Page<GeneralJournalTemplateHeaderResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage);

    Page<GeneralJournalTemplateHeaderResponse> getSearchPaginated(int pageNumber, int countPerPage, GeneralJournalTemplateHeaderSearchRequest searchRequest);

    Page<GeneralJournalTemplateHeaderResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, GeneralJournalTemplateHeaderSearchRequest searchRequest);


    Integer delete(Long id);
}
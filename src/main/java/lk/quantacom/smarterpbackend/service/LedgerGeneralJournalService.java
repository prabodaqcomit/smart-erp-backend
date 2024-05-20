package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerGeneralJournalRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerGeneralJournalUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerGeneralJournalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerGeneralJournalService {

    LedgerGeneralJournalResponse save(LedgerGeneralJournalRequest request);

    LedgerGeneralJournalResponse update(LedgerGeneralJournalUpdateRequest request);

    LedgerGeneralJournalResponse getById(Long id);

    List<LedgerGeneralJournalResponse> getAll();


    Integer delete(Long id);
}
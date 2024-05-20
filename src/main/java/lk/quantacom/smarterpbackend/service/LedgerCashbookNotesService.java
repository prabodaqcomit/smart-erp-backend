package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookNotesRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookNotesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerCashbookNotesResponse;
import lk.quantacom.smarterpbackend.entity.LedgerCashbookNotes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerCashbookNotesService {

    LedgerCashbookNotesResponse save(LedgerCashbookNotesRequest request);

    LedgerCashbookNotesResponse update(LedgerCashbookNotesUpdateRequest request);

    LedgerCashbookNotesResponse getById(Integer id);

    List<LedgerCashbookNotesResponse> getAll();

    Integer delete(Integer id);

    List<LedgerCashbookNotesResponse> getByPayeeName(String payeeName);


}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookSaveListRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerPettyCashBookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerPettyCashBookService {

    LedgerPettyCashBookResponse save(LedgerPettyCashBookRequest request);

    LedgerPettyCashBookResponse update(LedgerPettyCashBookUpdateRequest request);

    LedgerPettyCashBookResponse getById(Long id);

    List<LedgerPettyCashBookResponse> getAll();

    Integer delete(Long id);

    List<LedgerPettyCashBookResponse> saveAll1(List<LedgerPettyCashBookSaveListRequest> saveListRequests);

    List<LedgerPettyCashBookResponse> getByDates(String fromDate,String toDate,Long branchId);
}

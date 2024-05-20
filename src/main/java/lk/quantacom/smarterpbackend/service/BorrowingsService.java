package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BorrowingsRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsSaveAllRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsSaveReturnRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BorrowingsResponse;
import lk.quantacom.smarterpbackend.entity.Borrowings;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface BorrowingsService {

    BorrowingsResponse save(BorrowingsRequest request);

    BorrowingsResponse update(BorrowingsUpdateRequest request);

    BorrowingsResponse getById(Long id);

    List<BorrowingsResponse> getAll();

    Integer delete(Long id);

    List<BorrowingsResponse> getByBorrowerName(String borrowerName);

    BorrowingsResponse saveAll(BorrowingsSaveAllRequest request);

    List<BorrowingsResponse> getByReason(String reason);

    List<BorrowingsResponse> getByBorrowerNameAndStatusNwBr(String borrowerName);

    List<BorrowingsResponse> getByDateRangeOfBorrowDate(Date from, Date to);

    BorrowingsResponse saveReturn(BorrowingsSaveReturnRequest request);

    List<String> getBorrowerNames();

}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.TblinvhedResponse;
import lk.quantacom.smarterpbackend.entity.Tblinvhed;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface ReceiptHeaderService {

    ReceiptHeaderDocumentNumberResponse getNextDocumentNumber();

    List<String> getAvailableVoucherNumbers(String searchNumber);

    ReceiptHeaderResponse save(ReceiptHeaderRequest request);

    ReceiptHeaderResponse update(ReceiptHeaderUpdateRequest request);

    ReceiptHeaderResponse getById(Long id);

    List<ReceiptHeaderResponse> getAll();

//    Page<ReceiptHeaderResponse> getPaginatedAll(int pageNumber, int countPerPage);
//
//    Page<ReceiptHeaderResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage);
//
//    Page<ReceiptHeaderResponse> getSearchPaginated(int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest);
//
//    Page<ReceiptHeaderResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest);

    Page<ReceiptHeaderResponse> getPaginatedGeneralReceipts(int pageNumber, int countPerPage);

    Page<ReceiptHeaderResponse> getPaginatedGeneralReceipts(Long branchId, int pageNumber, int countPerPage);

    Page<ReceiptHeaderResponse> getPaginatedCustomerReceipts(int pageNumber, int countPerPage);

    Page<ReceiptHeaderResponse> getPaginatedCustomerReceipts(Long branchId, int pageNumber, int countPerPage);

    Page<ReceiptHeaderResponse> getSearchPaginatedGeneralReceipts(int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest);

    Page<ReceiptHeaderResponse> getSearchPaginatedGeneralReceipts(Long branchId, int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest);

    Page<ReceiptHeaderResponse> getSearchPaginatedCustomerReceipts(int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest);

    Page<ReceiptHeaderResponse> getSearchPaginatedCustomerReceipts(Long branchId, int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest);

    File printSingle(Long id);

    Integer delete(Long id);

}
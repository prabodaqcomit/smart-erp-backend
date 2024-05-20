package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UnDepositedFundReferenceHeaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnDepositedFundReferenceHeaderService {

    //UnDepositedFundReferenceHeaderResponse save(UnDepositedFundReferenceHeaderRequest request);

    //UnDepositedFundReferenceHeaderResponse update(UnDepositedFundReferenceHeaderUpdateRequest request);

    UnDepositedFundReferenceHeaderResponse getById(Long id);

    List<UnDepositedFundReferenceHeaderResponse> getAll();

    List<UnDepositedFundReferenceHeaderResponse> getPendingFunds();

    List<UnDepositedFundReferenceHeaderResponse> getPendingFundsByBranch(Long branchId);

    Page<UnDepositedFundReferenceHeaderResponse> getPagedPendingFunds(int pageNumber, int countPerPage);

    Page<UnDepositedFundReferenceHeaderResponse> getPagedPendingFundsByBranch(int pageNumber, int countPerPage, Long branchId);

    //Integer delete(Long id);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsSaveListRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChequeReturnsService {

    ChequeReturnsResponse save(ChequeReturnsRequest request);

    ChequeReturnsResponse update(ChequeReturnsUpdateRequest request);

    ChequeReturnsResponse getById(Long id);

    List<ChequeReturnsResponse> getAll();

    Integer delete(Long id);

    List<ChequeReturnsResponse> getByCustomerIdAndBranchId(Integer customerId,String branchId);

    List<ChequeReturnsResponse> getByBankCode(String bankCode);

    List<ChequeReturnsResponse> getByBranchCode(String branchCode);

    List<ChequeReturnsResponse> saveCustomerChequeReturn(ChequeReturnsSaveListRequest request);


    List<getByOwnChequeReturnResponse> getByPayeeNameAndDateAndBranchId(String payeeName, String startDate, String endDate, Integer branchId);

    List<getByOwnChequeReturnResponse> getByChequeNoAndDateAndBranchId(String ChequeNO, String startDate, String endDate, Integer branchId);

    List<getByOwnChequeReturnResponse> getByBankCodeAndDateAndBranchId(String bankCode, String startDate, String endDate, Integer branchId);

    List<getByOwnChequeReturnResponse> getByBranchCodeAndDateAndBranchId(String branchCode, String startDate, String endDate, Integer branchId);

    List<CustomerChequeReturnSearch> getForCusRtn();

    List<GetReceivedCheckWiithCustomer> getFromReceivedCheques(String chequeNo);

    List<GetInvOfCheque> getInvoicesOfCheque(String cheque_no);

    LadgerAccountResponse getCreditAcc(String chequeNo);

}
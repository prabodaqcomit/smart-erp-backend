package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.SalesDepositRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesDepositUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SalesDepositResponse;
import lk.quantacom.smarterpbackend.dto.response.getBySalesReceiptAndSalesDepositResponse;
import lk.quantacom.smarterpbackend.entity.SalesDeposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesDepositService {

    SalesDepositResponse save(SalesDepositRequest request);

    SalesDepositResponse update(SalesDepositUpdateRequest request);

    SalesDepositResponse getById(Long id);

    List<SalesDepositResponse> getAll();

    Integer delete(Long id);

    List<SalesDepositResponse> getByCustomerIdAndBranchId(Integer customerId, String branchId);

    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByCustomerName(String customerName, String startDate, String endDate, Integer branchId);

    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByBankName(String bankName,String startDate, String endDate, Integer branchId);

    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByBranchName(String branchName,String startDate, String endDate, Integer branchId);

    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByAccNo(String accNo,String startDate, String endDate, Integer branchId);

    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByAccName(String accName,String startDate, String endDate, Integer branchId);

    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByDateRange(String startDate, String endDate, Integer branchId);


}
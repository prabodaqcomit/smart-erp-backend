package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.SalesDepositRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesDepositUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SalesDepositResponse;
import lk.quantacom.smarterpbackend.dto.response.getBySalesReceiptAndSalesDepositResponse;
import lk.quantacom.smarterpbackend.entity.SalesDeposit;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.SalesDepositRepository;
import lk.quantacom.smarterpbackend.service.SalesDepositService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesDepositServiceImpl implements SalesDepositService {

    @Autowired
    private SalesDepositRepository salesDepositRepository;

    private static SalesDepositResponse convert(SalesDeposit salesDeposit) {

        SalesDepositResponse typeResponse = new SalesDepositResponse();
        typeResponse.setBalance(salesDeposit.getBalance());
        typeResponse.setBankAccountId(salesDeposit.getBankAccountId());
        typeResponse.setBranchId(salesDeposit.getBranchId());
        typeResponse.setCustomerId(salesDeposit.getCustomerId());
        typeResponse.setDate(salesDeposit.getDate());
        typeResponse.setDepositAmount(salesDeposit.getDepositAmount());
        typeResponse.setGrandTotalDeposit(salesDeposit.getGrandTotalDeposit());
        typeResponse.setIdsalesDeposit(salesDeposit.getIdsalesDeposit());
        typeResponse.setLineNo(salesDeposit.getLineNo());
        typeResponse.setSalesReceiptId(salesDeposit.getSalesReceiptId());
        typeResponse.setId(salesDeposit.getId());
        typeResponse.setCreatedBy(salesDeposit.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(salesDeposit.getCreatedDateTime()));
        typeResponse.setModifiedBy(salesDeposit.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(salesDeposit.getModifiedDateTime()));
        typeResponse.setIsDeleted(salesDeposit.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public SalesDepositResponse save(SalesDepositRequest request) {

        SalesDeposit salesDeposit = new SalesDeposit();
        salesDeposit.setBalance(request.getBalance());
        salesDeposit.setBankAccountId(request.getBankAccountId());
        salesDeposit.setBranchId(request.getBranchId());
        salesDeposit.setCustomerId(request.getCustomerId());
        salesDeposit.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        salesDeposit.setDepositAmount(request.getDepositAmount());
        salesDeposit.setGrandTotalDeposit(request.getGrandTotalDeposit());
        salesDeposit.setIdsalesDeposit(request.getIdsalesDeposit());
        salesDeposit.setLineNo(request.getLineNo());
        salesDeposit.setSalesReceiptId(request.getSalesReceiptId());
        salesDeposit.setIsDeleted(Deleted.NO);
        SalesDeposit save = salesDepositRepository.save(salesDeposit);

        return convert(save);
    }

    @Override
    @Transactional
    public SalesDepositResponse update(SalesDepositUpdateRequest request) {

        SalesDeposit salesDeposit = salesDepositRepository.findById(request.getId()).orElse(null);
        if (salesDeposit == null) {
            return null;
        }

        salesDeposit.setId(request.getId());
        salesDeposit.setBalance(request.getBalance());
        salesDeposit.setBankAccountId(request.getBankAccountId());
        salesDeposit.setBranchId(request.getBranchId());
        salesDeposit.setCustomerId(request.getCustomerId());
        salesDeposit.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        salesDeposit.setDepositAmount(request.getDepositAmount());
        salesDeposit.setGrandTotalDeposit(request.getGrandTotalDeposit());
        salesDeposit.setIdsalesDeposit(request.getIdsalesDeposit());
        salesDeposit.setLineNo(request.getLineNo());
        salesDeposit.setSalesReceiptId(request.getSalesReceiptId());
        SalesDeposit updated = salesDepositRepository.save(salesDeposit);

        return (convert(updated));
    }

    @Override
    public SalesDepositResponse getById(Long id) {

        return salesDepositRepository.findById(id).map(SalesDepositServiceImpl::convert).orElse(null);
    }

    @Override
    public List<SalesDepositResponse> getAll() {

        return salesDepositRepository.findByIsDeleted(Deleted.NO)
                .stream().map(SalesDepositServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        SalesDeposit got = salesDepositRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        salesDepositRepository.save(got);

        return 1;
    }

    @Override
    public List<SalesDepositResponse> getByCustomerIdAndBranchId(Integer customerId, String branchId) {
        return salesDepositRepository.findByCustomerIdAndBranchId(customerId,branchId)
                .stream().map(SalesDepositServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByCustomerName(String customerName, String startDate, String endDate, Integer branchId) {
        List<getBySalesReceiptAndSalesDepositResponse> responseList = salesDepositRepository.getSalesDepositAndReceiptByCustomerName(customerName,startDate,endDate,branchId);
        return responseList;
    }

    @Override
    public List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByBankName(String bankName, String startDate, String endDate, Integer branchId) {
        List<getBySalesReceiptAndSalesDepositResponse> responseList = salesDepositRepository.getSalesDepositAndReceiptByBankName(bankName,startDate,endDate,branchId);
        return responseList;
    }

    @Override
    public List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByBranchName(String branchName, String startDate, String endDate, Integer branchId) {
        List<getBySalesReceiptAndSalesDepositResponse> responseList = salesDepositRepository.getSalesDepositAndReceiptByBranchName(branchName,startDate,endDate,branchId);
        return responseList;
    }

    @Override
    public List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByAccNo(String accNo, String startDate, String endDate, Integer branchId) {
        List<getBySalesReceiptAndSalesDepositResponse> responseList = salesDepositRepository.getSalesDepositAndReceiptByAccNo(accNo,startDate,endDate,branchId);
        return responseList;
    }

    @Override
    public List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByAccName(String accName, String startDate, String endDate, Integer branchId) {
        List<getBySalesReceiptAndSalesDepositResponse> responseList = salesDepositRepository.getSalesDepositAndReceiptByAccName(accName,startDate,endDate,branchId);
        return responseList;
    }

    @Override
    public List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByDateRange(String startDate, String endDate, Integer branchId) {
        List<getBySalesReceiptAndSalesDepositResponse> responseList = salesDepositRepository.getSalesDepositAndReceiptByDateRange( startDate,endDate,branchId);
        return responseList;
    }
}
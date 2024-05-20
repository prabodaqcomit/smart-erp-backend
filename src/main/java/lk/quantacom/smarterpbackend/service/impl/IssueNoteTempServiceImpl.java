package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.IssueNoteTempRequest;
import lk.quantacom.smarterpbackend.dto.request.IssueNoteTempUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.IssueNoteTempResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.BinCardRepository;
import lk.quantacom.smarterpbackend.repository.IssueNoteTempRepository;
import lk.quantacom.smarterpbackend.repository.StockRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.IssueNoteTempService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueNoteTempServiceImpl implements IssueNoteTempService {

    @Autowired
    private IssueNoteTempRepository issueNoteTempRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<IssueNoteTempResponse> save(List<IssueNoteTempRequest> requestList) {

        Long max=issueNoteTempRepository.getMaxId();
        if(max==null){
            max=1L;
        }else{
            max=max+1;
        }

        List<IssueNoteTempResponse> responses=new ArrayList<>();

        for(IssueNoteTempRequest request:requestList){
            IssueNoteTemp issueNoteTemp = new IssueNoteTemp();
            ItemMaster item=new ItemMaster();
            item.setId(request.getItemId());
            issueNoteTemp.setItem(item);
            issueNoteTemp.setBatchNo(request.getBatchNo());
            BranchNetwork branch=new BranchNetwork();
            branch.setId(request.getBranchId());

            issueNoteTemp.setBranch(branch);
            issueNoteTemp.setIssueDate(request.getIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getIssueDate()));
            issueNoteTemp.setTransferMethod(request.getTransferMethod());
            issueNoteTemp.setTrFrom(request.getTrFrom());
            issueNoteTemp.setTrTo(request.getTrTo());
            issueNoteTemp.setUnitPrice(request.getUnitPrice());
            issueNoteTemp.setItemValue(request.getItemValue());
            issueNoteTemp.setIssueQty(request.getIssueQty());
            issueNoteTemp.setInHandQty(request.getInHandQty());
            issueNoteTemp.setCurrentBalance(request.getCurrentBalance());
            issueNoteTemp.setGrandTotal(request.getGrandTotal());
            issueNoteTemp.setIssueTotalQty(request.getIssueTotalQty());
            issueNoteTemp.setItemCreditValue(request.getItemCreditValue());
            issueNoteTemp.setItemCashValue(request.getItemCashValue());
            issueNoteTemp.setTotalCreditAmount(request.getTotalCreditAmount());
            issueNoteTemp.setTotalCashAmount(request.getTotalCashAmount());
            issueNoteTemp.setCashPrice(request.getCashPrice());
            issueNoteTemp.setCreditPrice(request.getCreditPrice());
            issueNoteTemp.setStoresInhand(request.getStoresInhand());
            issueNoteTemp.setStoresNew(request.getStoresNew());
            issueNoteTemp.setTransferBatchNo(request.getTransferBatchNo());
            issueNoteTemp.setTrFromBranchId(request.getTrFromBranchId());
            issueNoteTemp.setTrToBranchId(request.getTrToBranchId());
            issueNoteTemp.setColor(new Color(request.getColorId()));
            issueNoteTemp.setSize(new Size(request.getSizeId()));
            issueNoteTemp.setFit(new Fit(request.getFitId()));
            issueNoteTemp.setIsDeleted(Deleted.NO);
            issueNoteTemp.setIssueId(max);
            IssueNoteTemp save = issueNoteTempRepository.save(issueNoteTemp);
            responses.add(convert(save));



        }




        saveLog("IssueNote", "Data Saved - Bulk" );

        return responses;
    }

    @Override
    @Transactional
    public IssueNoteTempResponse update(IssueNoteTempUpdateRequest request) {

        IssueNoteTemp issueNoteTemp = issueNoteTempRepository.findById(request.getId()).orElse(null);
        if (issueNoteTemp == null) {
            return null;
        }

        issueNoteTemp.setId(request.getId());
        ItemMaster item=new ItemMaster();
        item.setId(request.getItemId());
        issueNoteTemp.setItem(item);
        issueNoteTemp.setBatchNo(request.getBatchNo());
        BranchNetwork branch=new BranchNetwork();
        branch.setId(request.getBranchId());
        issueNoteTemp.setBranch(branch);
        issueNoteTemp.setIssueDate(request.getIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getIssueDate()));
        issueNoteTemp.setTransferMethod(request.getTransferMethod());
        issueNoteTemp.setTrFrom(request.getTrFrom());
        issueNoteTemp.setTrTo(request.getTrTo());
        issueNoteTemp.setUnitPrice(request.getUnitPrice());
        issueNoteTemp.setItemValue(request.getItemValue());
        issueNoteTemp.setIssueQty(request.getIssueQty());
        issueNoteTemp.setInHandQty(request.getInHandQty());
        issueNoteTemp.setCurrentBalance(request.getCurrentBalance());
        issueNoteTemp.setGrandTotal(request.getGrandTotal());
        issueNoteTemp.setIssueTotalQty(request.getIssueTotalQty());
        issueNoteTemp.setItemCreditValue(request.getItemCreditValue());
        issueNoteTemp.setItemCashValue(request.getItemCashValue());
        issueNoteTemp.setTotalCreditAmount(request.getTotalCreditAmount());
        issueNoteTemp.setTotalCashAmount(request.getTotalCashAmount());
        issueNoteTemp.setCashPrice(request.getCashPrice());
        issueNoteTemp.setCreditPrice(request.getCreditPrice());
        issueNoteTemp.setStoresInhand(request.getStoresInhand());
        issueNoteTemp.setStoresNew(request.getStoresNew());
        issueNoteTemp.setTransferBatchNo(request.getTransferBatchNo());
        issueNoteTemp.setTrFromBranchId(request.getTrFromBranchId());
        issueNoteTemp.setTrToBranchId(request.getTrToBranchId());
        issueNoteTemp.setColor(new Color(request.getColorId()));
        issueNoteTemp.setSize(new Size(request.getSizeId()));
        issueNoteTemp.setFit(new Fit(request.getFitId()));
        IssueNoteTemp updated = issueNoteTempRepository.save(issueNoteTemp);

        saveLog("IssueNote", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public IssueNoteTempResponse getById(Long id) {

        return issueNoteTempRepository.findById(id).map(IssueNoteTempServiceImpl::convert).orElse(null);
    }

    @Override
    public List<IssueNoteTempResponse> getAll() {

        return issueNoteTempRepository.findAll()
                .stream().map(IssueNoteTempServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        IssueNoteTemp got = issueNoteTempRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        issueNoteTempRepository.save(got);

        saveLog("IssueNote", "Data Deleted - " + id);

        return 1;
    }

    private static IssueNoteTempResponse convert(IssueNoteTemp issueNoteTemp) {

        IssueNoteTempResponse typeResponse = new IssueNoteTempResponse();
        typeResponse.setItemCode(issueNoteTemp.getItem().getItemCode());
        typeResponse.setItemId(issueNoteTemp.getItem().getId());
        typeResponse.setBatchNo(issueNoteTemp.getBatchNo());
        typeResponse.setBranchId(issueNoteTemp.getBranch().getId());
        typeResponse.setIssueDate(ConvertUtils.convertDateToStr(issueNoteTemp.getIssueDate()));
        typeResponse.setTransferMethod(issueNoteTemp.getTransferMethod());
        typeResponse.setTrFrom(issueNoteTemp.getTrFrom());
        typeResponse.setTrTo(issueNoteTemp.getTrTo());
        typeResponse.setUnitPrice(issueNoteTemp.getUnitPrice());
        typeResponse.setItemValue(issueNoteTemp.getItemValue());
        typeResponse.setIssueQty(issueNoteTemp.getIssueQty());
        typeResponse.setInHandQty(issueNoteTemp.getInHandQty());
        typeResponse.setCurrentBalance(issueNoteTemp.getCurrentBalance());
        typeResponse.setGrandTotal(issueNoteTemp.getGrandTotal());
        typeResponse.setIssueTotalQty(issueNoteTemp.getIssueTotalQty());
        typeResponse.setItemCreditValue(issueNoteTemp.getItemCreditValue());
        typeResponse.setItemCashValue(issueNoteTemp.getItemCashValue());
        typeResponse.setTotalCreditAmount(issueNoteTemp.getTotalCreditAmount());
        typeResponse.setTotalCashAmount(issueNoteTemp.getTotalCashAmount());
        typeResponse.setCashPrice(issueNoteTemp.getCashPrice());
        typeResponse.setCreditPrice(issueNoteTemp.getCreditPrice());
        typeResponse.setStoresInhand(issueNoteTemp.getStoresInhand());
        typeResponse.setStoresNew(issueNoteTemp.getStoresNew());
        typeResponse.setTransferBatchNo(issueNoteTemp.getTransferBatchNo());
        typeResponse.setTrFromBranchId(issueNoteTemp.getTrFromBranchId());
        typeResponse.setTrToBranchId(issueNoteTemp.getTrToBranchId());
        typeResponse.setColorId(issueNoteTemp.getColor().getId());
        typeResponse.setSizeId(issueNoteTemp.getSize().getId());
        typeResponse.setFitId(issueNoteTemp.getFit().getId());
        typeResponse.setId(issueNoteTemp.getId());
        typeResponse.setCreatedBy(issueNoteTemp.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(issueNoteTemp.getCreatedDateTime()));
        typeResponse.setModifiedBy(issueNoteTemp.getModifiedBy());
        typeResponse.setIsDeleted(issueNoteTemp.getIsDeleted());
        typeResponse.setIssueId(issueNoteTemp.getIssueId());
        typeResponse.setItemName(issueNoteTemp.getItemName());
        typeResponse.setStatus(issueNoteTemp.getStatus());
        typeResponse.setIssueNoteSavedId(issueNoteTemp.getIssueNoteSavedId());

        return typeResponse;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}
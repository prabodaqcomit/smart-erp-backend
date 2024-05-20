package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerCommonSelectionsRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCommonSelectionsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CommonDebitAccountWithInvestmentResponse;
import lk.quantacom.smarterpbackend.dto.response.LedgerCommonSelectionsResponse;
import lk.quantacom.smarterpbackend.entity.LedgerCommonSelections;
import lk.quantacom.smarterpbackend.repository.LedgerCommonSelectionsRepository;
import lk.quantacom.smarterpbackend.service.LedgerCommonSelectionsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerCommonSelectionsServiceImpl implements LedgerCommonSelectionsService {

    @Autowired
    private LedgerCommonSelectionsRepository ledgerCommonSelectionsRepository;

    @Override
    public LedgerCommonSelectionsResponse save( LedgerCommonSelectionsRequest request) {

        LedgerCommonSelections ledgerCommonSelections = new LedgerCommonSelections();
        ledgerCommonSelections.setCreditAccId(request.getCreditAccId());

        ledgerCommonSelections.setCreditAccNum(request.getCreditAccNum());

        ledgerCommonSelections.setDebitAccId(request.getDebitAccId());

        ledgerCommonSelections.setDebitAccNum(request.getDebitAccNum());

        ledgerCommonSelections.setDescription(request.getDescription());

        ledgerCommonSelections.setFramName(request.getFramName());

        ledgerCommonSelections.setFrameId(request.getFrameId());

        //ledgerCommonSelections.setLedgerCommonSelectionsId(request.());

        ledgerCommonSelections.setPayMode(request.getPayMode());

        ledgerCommonSelections.setRecordRight(request.getRecordRight());

        LedgerCommonSelections save = ledgerCommonSelectionsRepository.save(ledgerCommonSelections);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerCommonSelectionsResponse update(LedgerCommonSelectionsUpdateRequest request) {

//        int update = ledgerCommonSelectionsRepository.update(request.getCreditAccId(), request.getCreditAccNum(), request.getDebitAccId(), request.getDebitAccNum(), request.getDescription(), request.getFramName(), request.getFrameId(), request.getLedgerCommonSelectionsId(), request.getPayMode(), request.getRecordRight(), request.getId());
//
//        if (update == 0) {
//            return null;
//        }

 //       return (getById(request.getId()));
        return null;
    }

    @Override
    public LedgerCommonSelectionsResponse getById(Integer id) {

        return ledgerCommonSelectionsRepository.findById(id).map(LedgerCommonSelectionsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerCommonSelectionsResponse> getAll() {

        return ledgerCommonSelectionsRepository.findAll()
                .stream().map(LedgerCommonSelectionsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<LedgerCommonSelectionsResponse> getAllByFrameIdAndPayModeAndDescription() {
        return ledgerCommonSelectionsRepository.findByFrameIdAndPayModeAndDescription("M164","1","paying a petty cash expence")
                .stream().map(LedgerCommonSelectionsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LedgerCommonSelectionsResponse> getByDebitAccNum(String debitAccNum) {
        return ledgerCommonSelectionsRepository.findByFrameIdAndPayModeAndDescriptionAndDebitAccNum("M230","1","new opening balance of a customer",debitAccNum)
                .stream().map(LedgerCommonSelectionsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<CommonDebitAccountWithInvestmentResponse> getByCommonDebitAccountWithInvestment(Integer debitAccId) {

        List<CommonDebitAccountWithInvestmentResponse> responseList = ledgerCommonSelectionsRepository.getCommonDebitAccountWithInvestment(debitAccId);
        return responseList;
    }

    @Override
    public List<LedgerCommonSelectionsResponse> getBySearchChequeDepositCommons() {
        return ledgerCommonSelectionsRepository.findByFrameIdAndPayModeAndDescription("M147","3","deposit cheques in another day")
                .stream().map(LedgerCommonSelectionsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LedgerCommonSelectionsResponse> getAllByIssueMoneyForBorrower() {
        return ledgerCommonSelectionsRepository.findByFrameIdAndPayModeAndDescription("M167","1","Issue a money for borrorwer")
                .stream().map(LedgerCommonSelectionsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LedgerCommonSelectionsResponse> getAllByReceivedMoneyFromBorrower() {
        return ledgerCommonSelectionsRepository.findByFrameIdAndPayModeAndDescription("M167","1","received money from borrorwer")
                .stream().map(LedgerCommonSelectionsServiceImpl::convert).collect(Collectors.toList());
    }

//    @Override
//    public List<LedgerCommonSelectionsResponse> getAllActive() {
//
//        return ledgerCommonSelectionsRepository.findByActiveAndDeleted(true, false)
//                .stream().map(LedgerCommonSelectionsServiceImpl::convert).collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public Integer delete(Long id) {
//
//        return ledgerCommonSelectionsRepository.delete(id);
//
//    }

    private static LedgerCommonSelectionsResponse convert(LedgerCommonSelections ledgerCommonSelections) {

        LedgerCommonSelectionsResponse typeResponse = new LedgerCommonSelectionsResponse();
        typeResponse.setCreditAccId(ledgerCommonSelections.getCreditAccId());

        typeResponse.setCreditAccNum(ledgerCommonSelections.getCreditAccNum());

        typeResponse.setDebitAccId(ledgerCommonSelections.getDebitAccId());

        typeResponse.setDebitAccNum(ledgerCommonSelections.getDebitAccNum());

        typeResponse.setDescription(ledgerCommonSelections.getDescription());

        typeResponse.setFramName(ledgerCommonSelections.getFramName());

        typeResponse.setFrameId(ledgerCommonSelections.getFrameId());

        typeResponse.setLedgerCommonSelectionsId(ledgerCommonSelections.getLedgerCommonSelectionsId());

        typeResponse.setPayMode(ledgerCommonSelections.getPayMode());

        typeResponse.setRecordRight(ledgerCommonSelections.getRecordRight());

        return typeResponse;
    }
}
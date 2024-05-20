package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ReceivedChequesRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceivedChequesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.Customer;
import lk.quantacom.smarterpbackend.entity.ReceivedCheques;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.CustomerRepository;
import lk.quantacom.smarterpbackend.repository.ReceivedChequesRepository;
import lk.quantacom.smarterpbackend.service.ReceivedChequesService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceivedChequesServiceImpl implements ReceivedChequesService {

    @Autowired
    private ReceivedChequesRepository receivedChequesRepository;

    @Autowired
    private CustomerRepository customerRepository;


    private static ReceivedChequesResponse convert(ReceivedCheques receivedCheques) {

        ReceivedChequesResponse typeResponse = new ReceivedChequesResponse();
        typeResponse.setAvailbleAmount(receivedCheques.getAvailbleAmount());
        typeResponse.setBankCode(receivedCheques.getBankCode());
        typeResponse.setBranchCode(receivedCheques.getBranchCode());
        typeResponse.setBranchId(receivedCheques.getBranchId());
        typeResponse.setChequeAccName(receivedCheques.getChequeAccName());
        typeResponse.setChequeAmount(receivedCheques.getChequeAmount());
        typeResponse.setChequeDate(receivedCheques.getChequeDate());
        typeResponse.setChequeNo(receivedCheques.getChequeNo());
        typeResponse.setCurrencyType(receivedCheques.getCurrencyType());
        typeResponse.setCustomerId(receivedCheques.getCustomerId());
        typeResponse.setDepoBank(receivedCheques.getDepoBank());
        typeResponse.setDepoDate(receivedCheques.getDepoDate());
        typeResponse.setNewChequeNo(receivedCheques.getNewChequeNo());
        typeResponse.setPdOwner(receivedCheques.getPdOwner());
        typeResponse.setReceivedDate(receivedCheques.getReceivedDate());
        typeResponse.setRemarks(receivedCheques.getRemarks());
        typeResponse.setStatus(receivedCheques.getStatus());
        typeResponse.setStatusDate(receivedCheques.getStatusDate());
        typeResponse.setThisIsPd(receivedCheques.getThisIsPd());
        typeResponse.setId(receivedCheques.getId());
        typeResponse.setCreatedBy(receivedCheques.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(receivedCheques.getCreatedDateTime()));
        typeResponse.setModifiedBy(receivedCheques.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(receivedCheques.getModifiedDateTime()));
        typeResponse.setIsDeleted(receivedCheques.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public ReceivedChequesResponse save(ReceivedChequesRequest request) {

        ReceivedCheques receivedCheques = new ReceivedCheques();
        receivedCheques.setAvailbleAmount(request.getAvailbleAmount());
        receivedCheques.setBankCode(request.getBankCode());
        receivedCheques.setBranchCode(request.getBranchCode());
        receivedCheques.setBranchId(request.getBranchId());
        receivedCheques.setChequeAccName(request.getChequeAccName());
        receivedCheques.setChequeAmount(request.getChequeAmount());
        receivedCheques.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        receivedCheques.setChequeNo(request.getChequeNo());
        receivedCheques.setCurrencyType(request.getCurrencyType());
        receivedCheques.setCustomerId(request.getCustomerId());
        receivedCheques.setDepoBank(request.getDepoBank());
        receivedCheques.setDepoDate(request.getDepoDate() == null ? null : ConvertUtils.convertStrToDate(request.getDepoDate()));
        receivedCheques.setNewChequeNo(request.getNewChequeNo());
        receivedCheques.setPdOwner(request.getPdOwner());
        receivedCheques.setReceivedDate(request.getReceivedDate() == null ? null : ConvertUtils.convertStrToDate(request.getReceivedDate()));
        receivedCheques.setRemarks(request.getRemarks());
        receivedCheques.setStatus(request.getStatus());
        receivedCheques.setStatusDate(request.getStatusDate() == null ? null : ConvertUtils.convertStrToDate(request.getStatusDate()));
        receivedCheques.setThisIsPd(request.getThisIsPd());
        receivedCheques.setIsDeleted(Deleted.NO);
        ReceivedCheques save = receivedChequesRepository.save(receivedCheques);

        return convert(save);
    }

    @Override
    @Transactional
    public ReceivedChequesResponse update(ReceivedChequesUpdateRequest request) {

        ReceivedCheques receivedCheques = receivedChequesRepository.findById(request.getId()).orElse(null);
        if (receivedCheques == null) {
            return null;
        }

        receivedCheques.setId(request.getId());
        receivedCheques.setAvailbleAmount(request.getAvailbleAmount());
        receivedCheques.setBankCode(request.getBankCode());
        receivedCheques.setBranchCode(request.getBranchCode());
        receivedCheques.setBranchId(request.getBranchId());
        receivedCheques.setChequeAccName(request.getChequeAccName());
        receivedCheques.setChequeAmount(request.getChequeAmount());
        receivedCheques.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        receivedCheques.setChequeNo(request.getChequeNo());
        receivedCheques.setCurrencyType(request.getCurrencyType());
        receivedCheques.setCustomerId(request.getCustomerId());
        receivedCheques.setDepoBank(request.getDepoBank());
        receivedCheques.setDepoDate(request.getDepoDate() == null ? null : ConvertUtils.convertStrToDate(request.getDepoDate()));
        receivedCheques.setNewChequeNo(request.getNewChequeNo());
        receivedCheques.setPdOwner(request.getPdOwner());
        receivedCheques.setReceivedDate(request.getReceivedDate() == null ? null : ConvertUtils.convertStrToDate(request.getReceivedDate()));
        receivedCheques.setRemarks(request.getRemarks());
        receivedCheques.setStatus(request.getStatus());
        receivedCheques.setStatusDate(request.getStatusDate() == null ? null : ConvertUtils.convertStrToDate(request.getStatusDate()));
        receivedCheques.setThisIsPd(request.getThisIsPd());
        ReceivedCheques updated = receivedChequesRepository.save(receivedCheques);

        return (convert(updated));
    }

    @Override
    public ReceivedChequesResponse getById(Long id) {

        return receivedChequesRepository.findById(id).map(ReceivedChequesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ReceivedChequesResponse> getAll() {

        return receivedChequesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ReceivedChequesServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ReceivedCheques got = receivedChequesRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        receivedChequesRepository.save(got);

        return 1;
    }

    @Override
    public List<ReceivedChequesResponse> getAllByCustomerId(Integer customerId) {
        return receivedChequesRepository.findByCustomerId(customerId)
                .stream().map(ReceivedChequesServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ReceivedChequesResponse> getAllByChequeNoGroupByChequeNo(String chequeNo) {
        return receivedChequesRepository.findByChequeNo(chequeNo)
                .stream().map(ReceivedChequesServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ReceivedChequesResponse> getAllByBankCodeGroupByBankCode(String bankCode) {
        return receivedChequesRepository.findByBankCode(bankCode)
                .stream().map(ReceivedChequesServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ReceivedChequesResponse> getAllByBranchCodeGroupByBranchCode(String branchCode) {
        return receivedChequesRepository.findByBranchCode(branchCode)
                .stream().map(ReceivedChequesServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ReceivedChequesInterfaceResponse> getByCustomerIdAndDateAndBranchId(Integer customerId, String startDate, String endDate, Integer branchId) {
        List<ReceivedChequesInterfaceResponse> responseList = new ArrayList<>();
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<ReceivedChequesInterfaceResponse> responses = receivedChequesRepository.getByCustomerIdAndDateAndBranchId(customerId,startDate,endDate,branchId);

        for(ReceivedChequesInterfaceResponse response:responses){
            Customer customer = customerRepository.getById(Long.parseLong(response.getCUSTOMER_ID().toString()));
            responseList.add(response);

        }
        return responseList;
    }

    @Override
    public List<ReceivedChequesInterfaceResponse> getByChequeNoAndDateAndBranchId(String chequeNo, String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<ReceivedChequesInterfaceResponse> responses = receivedChequesRepository.getByChequeNoAndDateAndBranchId(chequeNo,startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<ReceivedChequesInterfaceResponse> getByBankCodeAndDateAndBranchId(String bankCode, String startDate, String endDate, Integer branchId) {

        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<ReceivedChequesInterfaceResponse> responses = receivedChequesRepository.getByBankCodeAndDateAndBranchId(bankCode,startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<ReceivedChequesInterfaceResponse> getByBranchCodeAndDateAndBranchId(String branchCode, String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<ReceivedChequesInterfaceResponse> responses = receivedChequesRepository.getByBranchCodeAndDateAndBranchId(branchCode,startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<ReceivedChequesAndCustomerInterfaceResponse> getByChequesAndDateAndBranchId(String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<ReceivedChequesAndCustomerInterfaceResponse> responses = receivedChequesRepository.getByChequesAndDateAndBranchId(startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<ReceivedChequesAndCustomerInterfaceResponse> getByChequeDateAndDateAndBranchId(String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<ReceivedChequesAndCustomerInterfaceResponse> responses = receivedChequesRepository.getByChequeDateAndDateAndBranchId(startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<ReceivedChequesAndCustomerInterfaceResponse> getByDepoDateAndDateAndBranchId(String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<ReceivedChequesAndCustomerInterfaceResponse> responses = receivedChequesRepository.getByDepoDateAndDateAndBranchId(startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<ReceivedChequesAndCustomerInterfaceResponse> getByDepositDateAndDateAndBranchId(String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<ReceivedChequesAndCustomerInterfaceResponse> responses = receivedChequesRepository.getByDepositDateAndDateAndBranchId(startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<getChequeDepositAndBankAccountByChqueNoResponse> getByChequeDepositAndBankAccountByChequeNo(String chequeNo) {
        List<getChequeDepositAndBankAccountByChqueNoResponse> responses=receivedChequesRepository.getByChequeDepositAndBankAccountByChequeNo(chequeNo);
        return responses;
    }

    @Override
    public List<getReceivedChequesAndCustomerByChequeNoResponse> getByReceivedChequesAndCustomerByChequeNo(String chequeNo) {
        List<getReceivedChequesAndCustomerByChequeNoResponse> responses=receivedChequesRepository.getByReceivedChequesAndCustomerByChequeNo(chequeNo);
        return responses;
    }

    @Override
    public List<getBankIdAndLadgerIdResponse> getBygetBankIdAndLadgerIdResponseByChequeNo(String chequeNo) {
        List<getBankIdAndLadgerIdResponse> responses=receivedChequesRepository.getBygetBankIdAndLadgerIdByChequeNo(chequeNo);
        return responses;
    }
}
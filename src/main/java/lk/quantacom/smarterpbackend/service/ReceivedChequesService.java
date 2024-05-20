package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ReceivedChequesRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceivedChequesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.ReceivedCheques;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReceivedChequesService {

    ReceivedChequesResponse save(ReceivedChequesRequest request);

    ReceivedChequesResponse update(ReceivedChequesUpdateRequest request);

    ReceivedChequesResponse getById(Long id);

    List<ReceivedChequesResponse> getAll();

    Integer delete(Long id);

    List<ReceivedChequesResponse> getAllByCustomerId(Integer customerId);

    List<ReceivedChequesResponse> getAllByChequeNoGroupByChequeNo(String chequeNo);

    List<ReceivedChequesResponse> getAllByBankCodeGroupByBankCode(String bankCode);

    List<ReceivedChequesResponse> getAllByBranchCodeGroupByBranchCode(String branchCode);

    List<ReceivedChequesInterfaceResponse> getByCustomerIdAndDateAndBranchId(Integer customerId, String startDate, String endDate, Integer branchId);

    List<ReceivedChequesInterfaceResponse> getByChequeNoAndDateAndBranchId(String chequeNo, String startDate, String endDate, Integer branchId);

    List<ReceivedChequesInterfaceResponse> getByBankCodeAndDateAndBranchId(String bankCode, String startDate, String endDate, Integer branchId);

    List<ReceivedChequesInterfaceResponse> getByBranchCodeAndDateAndBranchId(String branchCode, String startDate, String endDate, Integer branchId);

    List<ReceivedChequesAndCustomerInterfaceResponse> getByChequesAndDateAndBranchId(String startDate, String endDate, Integer branchId);

    List<ReceivedChequesAndCustomerInterfaceResponse> getByChequeDateAndDateAndBranchId(String startDate, String endDate, Integer branchId);

    List<ReceivedChequesAndCustomerInterfaceResponse> getByDepoDateAndDateAndBranchId(String startDate, String endDate, Integer branchId);

    List<ReceivedChequesAndCustomerInterfaceResponse> getByDepositDateAndDateAndBranchId(String startDate, String endDate, Integer branchId);

    List<getChequeDepositAndBankAccountByChqueNoResponse> getByChequeDepositAndBankAccountByChequeNo(String chequeNo);

    List<getReceivedChequesAndCustomerByChequeNoResponse> getByReceivedChequesAndCustomerByChequeNo(String chequeNo);

    List<getBankIdAndLadgerIdResponse> getBygetBankIdAndLadgerIdResponseByChequeNo(String chequeNo);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceLedgerRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CustomerAndCusOpeningBalResponse;
import lk.quantacom.smarterpbackend.dto.response.CustomerOpeningBalanceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerOpeningBalanceService {

    CustomerOpeningBalanceResponse save(CustomerOpeningBalanceRequest request);

    CustomerOpeningBalanceResponse update(CustomerOpeningBalanceUpdateRequest request);

    CustomerOpeningBalanceResponse getById(Integer id);

    List<CustomerOpeningBalanceResponse> getAll();

    List<CustomerOpeningBalanceResponse> saveCustomerOpeningBalance(List<CustomerOpeningBalanceLedgerRequest> customerOpeningBalanceLedgerRequests);

    Integer delete(Integer id);

    String getFldCustomerId(String fldCustomerId);

    List<CustomerAndCusOpeningBalResponse> getCustomerAndCusOpeningBal();
}
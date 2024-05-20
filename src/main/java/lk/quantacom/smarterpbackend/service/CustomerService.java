package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.CustomerRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CustomerResponse;
import lk.quantacom.smarterpbackend.entity.Customer;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface CustomerService {

    CustomerResponse save(CustomerRequest request);

    CustomerResponse update(CustomerUpdateRequest request);

    CustomerResponse getById(Long id);

    List<CustomerResponse> getAll();

    String getMaxCreditAccNo();

    Integer delete(Long id);

    File printProfile(Long id);

    List<CustomerResponse> getByName(String name);
}
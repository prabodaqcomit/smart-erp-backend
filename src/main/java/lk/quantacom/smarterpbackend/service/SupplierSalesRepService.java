package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.SupplierSalesRepRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierSalesRepUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierSalesRepResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierSalesRepService {

    SupplierSalesRepResponse save(SupplierSalesRepRequest request);

    SupplierSalesRepResponse update(SupplierSalesRepUpdateRequest request);

    SupplierSalesRepResponse getById(Long id);

    List<SupplierSalesRepResponse> getAll();


    Integer delete(Long id);
}
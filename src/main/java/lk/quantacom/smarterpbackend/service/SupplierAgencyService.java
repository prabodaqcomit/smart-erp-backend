package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.SupplierAgencyRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierAgencyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierAgencyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierAgencyService {

    SupplierAgencyResponse save(SupplierAgencyRequest request);

    SupplierAgencyResponse update(SupplierAgencyUpdateRequest request);

    SupplierAgencyResponse getById(Long id);

    List<SupplierAgencyResponse> getAll();


    Integer delete(Long id);
}
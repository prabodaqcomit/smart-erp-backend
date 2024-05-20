package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.SupplierRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface SupplierService {

    SupplierResponse save(SupplierRequest request);

    SupplierResponse update(SupplierUpdateRequest request);

    SupplierResponse getById(Long id);

    List<SupplierResponse> getAll();

    String getMaxCreditAccNo();

    Integer delete(Long id);

    File printProfile(Long id);
}
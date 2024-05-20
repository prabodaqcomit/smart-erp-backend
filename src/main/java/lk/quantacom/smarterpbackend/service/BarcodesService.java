package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BarcodesRequest;
import lk.quantacom.smarterpbackend.dto.request.BarcodesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BarcodesResponse;
import lk.quantacom.smarterpbackend.dto.response.ItemsByBarcodesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BarcodesService {

    BarcodesResponse save(BarcodesRequest request);

    BarcodesResponse update(BarcodesUpdateRequest request);

    BarcodesResponse getById(Long id);

    List<BarcodesResponse> getAll();

    Integer delete(Long id);

    ItemsByBarcodesResponse getItemByBarcode(String barcode);

}
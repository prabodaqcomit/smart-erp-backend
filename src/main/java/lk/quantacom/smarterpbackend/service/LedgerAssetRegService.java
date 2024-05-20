package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerAssetRegRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerAssetRegUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerAssetRegResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerAssetRegService {

    LedgerAssetRegResponse save(LedgerAssetRegRequest request);

    LedgerAssetRegResponse update(LedgerAssetRegUpdateRequest request);

    LedgerAssetRegResponse getById(Long id);

    List<LedgerAssetRegResponse> getAll();


    Integer delete(Long id);
}
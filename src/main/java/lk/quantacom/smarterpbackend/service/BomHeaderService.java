package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BomHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.BomHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BomHeaderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BomHeaderService {

    BomHeaderResponse save(BomHeaderRequest request);

    BomHeaderResponse update(BomHeaderUpdateRequest request);

    BomHeaderResponse getById(Long id);

    List<BomHeaderResponse> getAll();


    Integer delete(Long id);
}
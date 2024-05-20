package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.UnitRefRequest;
import lk.quantacom.smarterpbackend.dto.request.UnitRefUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UnitRefResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnitRefService {

    UnitRefResponse save(UnitRefRequest request);

    UnitRefResponse update(UnitRefUpdateRequest request);

    UnitRefResponse getById(Long id);

    List<UnitRefResponse> getAll();


    Integer delete(Long id);
}
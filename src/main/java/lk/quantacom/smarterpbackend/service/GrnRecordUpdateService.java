package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GrnRecordUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnRecordUpdateUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnRecordUpdateResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrnRecordUpdateService {

    GrnRecordUpdateResponse save(GrnRecordUpdateRequest request);

    GrnRecordUpdateResponse update(GrnRecordUpdateUpdateRequest request);

    GrnRecordUpdateResponse getById(Integer id);

    List<GrnRecordUpdateResponse> getAll();


    Integer delete(Integer id);
}
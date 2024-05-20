package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PrnTemparyRequest;
import lk.quantacom.smarterpbackend.dto.request.PrnTemparyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PrnTemparyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrnTemparyService {

    List<PrnTemparyResponse> save(List<PrnTemparyRequest> requestList);

    PrnTemparyResponse update(PrnTemparyUpdateRequest request);

    PrnTemparyResponse getById(Integer id);

    List<PrnTemparyResponse> getAll();


    Integer delete(Integer id);
}
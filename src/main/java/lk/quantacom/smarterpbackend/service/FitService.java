package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.FitRequest;
import lk.quantacom.smarterpbackend.dto.request.FitUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.FitResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FitService {

    FitResponse save(FitRequest request);

    FitResponse update(FitUpdateRequest request);

    FitResponse getById(Long id);

    List<FitResponse> getAll();


    Integer delete(Long id);
}
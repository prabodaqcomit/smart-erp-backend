package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.SalesRefRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesRefUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SalesRefResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesRefService {

    SalesRefResponse save(SalesRefRequest request);

    SalesRefResponse update(SalesRefUpdateRequest request);

    SalesRefResponse getById(Long id);

    List<SalesRefResponse> getAll();


    Integer delete(Long id);
}
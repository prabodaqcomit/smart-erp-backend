package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.DiningTableRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiningTableService {

    DiningTableResponse save(DiningTableRequest request);

    DiningTableResponse update(DiningTableUpdateRequest request);

    DiningTableResponse getById(Long id);

    List<DiningTableResponse> getAll();


    Integer delete(Long id);
}
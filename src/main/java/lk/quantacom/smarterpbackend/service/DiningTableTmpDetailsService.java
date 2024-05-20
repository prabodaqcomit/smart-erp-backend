package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableTmpDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiningTableTmpDetailsService {

    DiningTableTmpDetailsResponse save(DiningTableTmpDetailsRequest request);

    DiningTableTmpDetailsResponse update(DiningTableTmpDetailsUpdateRequest request);

    DiningTableTmpDetailsResponse getById(Long id);

    List<DiningTableTmpDetailsResponse> getAll();


    Integer delete(Long id);
}
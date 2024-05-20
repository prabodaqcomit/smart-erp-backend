package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.OpeningBalanceRequest;
import lk.quantacom.smarterpbackend.dto.request.OpeningBalanceUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.OpeningBalanceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OpeningBalanceService {

    List<OpeningBalanceResponse> saveBulk(List<OpeningBalanceRequest> request);

    OpeningBalanceResponse save(OpeningBalanceRequest request);

    OpeningBalanceResponse update(OpeningBalanceUpdateRequest request);

    OpeningBalanceResponse getById(Long id);

    List<OpeningBalanceResponse> getAll();


    Integer delete(Long id);
}
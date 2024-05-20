package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.MainNavButtonsRequest;
import lk.quantacom.smarterpbackend.dto.request.MainNavButtonsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MainNavButtonsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MainNavButtonsService {

    MainNavButtonsResponse save(MainNavButtonsRequest request);

    MainNavButtonsResponse update(MainNavButtonsUpdateRequest request);

    MainNavButtonsResponse getById(Long id);

    List<MainNavButtonsResponse> getAll();


    Integer delete(Long id);
}
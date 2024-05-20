package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.TabFormRequest;
import lk.quantacom.smarterpbackend.dto.request.TabFormUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TabFormResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TabFormService {

    TabFormResponse save(TabFormRequest request);

    TabFormResponse update(TabFormUpdateRequest request);

    TabFormResponse getById(Long id);

    List<TabFormResponse> getAll();


    Integer delete(Long id);
}
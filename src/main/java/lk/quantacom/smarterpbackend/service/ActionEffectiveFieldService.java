package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ActionEffectiveFieldRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionEffectiveFieldUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionEffectiveFieldResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActionEffectiveFieldService {

    ActionEffectiveFieldResponse save(ActionEffectiveFieldRequest request);

    ActionEffectiveFieldResponse update(ActionEffectiveFieldUpdateRequest request);

    ActionEffectiveFieldResponse getById(Long id);

    List<ActionEffectiveFieldResponse> getAll();


    Integer delete(Long id);
}
package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ActionTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActionTypeService {

    ActionTypeResponse save(ActionTypeRequest request);

    ActionTypeResponse update(ActionTypeUpdateRequest request);

    ActionTypeResponse getById(Long id);

    List<ActionTypeResponse> getAll();


    Integer delete(Long id);
}
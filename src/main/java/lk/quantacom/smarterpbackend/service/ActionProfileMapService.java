package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ActionProfileMapRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionProfileMapUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionProfileMapResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActionProfileMapService {

    ActionProfileMapResponse save(ActionProfileMapRequest request);

    ActionProfileMapResponse update(ActionProfileMapUpdateRequest request);

    ActionProfileMapResponse getById(Long id);

    List<ActionProfileMapResponse> getAll();

    Integer delete(Long id);

//    List<getActionFieldByReferenceAliasAndProfileIdResponse> getAllActionField(Long actionFieldId,Long profileId);
}
package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ProfileFieldMappingRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFieldMappingUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionFieldResponse;
import lk.quantacom.smarterpbackend.dto.response.ProfileFieldMappingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileFieldMappingService {

    List<ProfileFieldMappingResponse> save(List<ProfileFieldMappingRequest> request);

    ProfileFieldMappingResponse update(ProfileFieldMappingUpdateRequest request);

    ProfileFieldMappingResponse getById(Long id);

    List<ProfileFieldMappingResponse> getAll();

    List<ActionFieldResponse> getFields(Integer profileId);

    Integer delete(Integer profileId,Long fieldId);
}
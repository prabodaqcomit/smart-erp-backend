package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ProfileMenuMappingRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileMenuMappingUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileMenuMappingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileMenuMappingService {

    List<ProfileMenuMappingResponse> save(List<ProfileMenuMappingRequest> request);

    ProfileMenuMappingResponse update(ProfileMenuMappingUpdateRequest request);

    ProfileMenuMappingResponse getById(Long id);

    List<ProfileMenuMappingResponse> getAll();

    List<ProfileMenuMappingResponse> getByProfile(Integer profileId);

    Integer delete(Long id);
}
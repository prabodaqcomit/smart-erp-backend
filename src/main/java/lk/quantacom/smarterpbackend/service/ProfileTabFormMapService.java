package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ProfileTabFormMapRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTabFormMapUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTabFormMapResponse;
import lk.quantacom.smarterpbackend.dto.response.TabFormResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileTabFormMapService {

    List<ProfileTabFormMapResponse> save(List<ProfileTabFormMapRequest> request);

    ProfileTabFormMapResponse update(ProfileTabFormMapUpdateRequest request);

    ProfileTabFormMapResponse getById(Long id);

    List<ProfileTabFormMapResponse> getAll();

    List<TabFormResponse> getByProfile(Integer profileId);

    Integer delete(Integer profileId,Long formId);
}
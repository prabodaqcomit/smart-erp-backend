package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ProfileTypesRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTypesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTypesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileTypesService {

    ProfileTypesResponse save(ProfileTypesRequest request);

    ProfileTypesResponse update(ProfileTypesUpdateRequest request);

    ProfileTypesResponse getById(Integer id);

    List<ProfileTypesResponse> getAll();


    Integer delete(Integer id);
}
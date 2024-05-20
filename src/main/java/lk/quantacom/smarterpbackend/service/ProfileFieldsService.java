package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ProfileFieldsRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFieldsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileFieldsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileFieldsService {

    ProfileFieldsResponse save(ProfileFieldsRequest request);

    ProfileFieldsResponse update(ProfileFieldsUpdateRequest request);

    ProfileFieldsResponse getById(String id);

    List<ProfileFieldsResponse> getAll();


    Integer delete(String id);
}
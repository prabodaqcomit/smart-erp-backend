package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ProfileValuesRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileValuesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileValuesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileValuesService {

    ProfileValuesResponse save(ProfileValuesRequest request);

    ProfileValuesResponse update(ProfileValuesUpdateRequest request);

    ProfileValuesResponse getById(Integer id);

    List<ProfileValuesResponse> getAll();


    Integer delete(Integer id);
}
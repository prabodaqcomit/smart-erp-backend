package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ProfileRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {

    ProfileResponse save(ProfileRequest request);

    ProfileResponse update(ProfileUpdateRequest request);

    ProfileResponse getById(Integer id);

    List<ProfileResponse> getAll();

    Integer delete(Integer id);


}
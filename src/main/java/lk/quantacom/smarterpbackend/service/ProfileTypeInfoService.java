package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ProfileTypeInfoRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTypeInfoUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTypeInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileTypeInfoService {

    ProfileTypeInfoResponse save(ProfileTypeInfoRequest request);

    ProfileTypeInfoResponse update(ProfileTypeInfoUpdateRequest request);

    ProfileTypeInfoResponse getById(Integer id);

    List<ProfileTypeInfoResponse> getAll();


    Integer delete(Integer id);
}
package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ProfileFormsRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFormsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileFormsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileFormsService {

    ProfileFormsResponse save(ProfileFormsRequest request);

    ProfileFormsResponse update(ProfileFormsUpdateRequest request);

    ProfileFormsResponse getById(Integer id);

    List<ProfileFormsResponse> getAll();


    Integer delete(Integer id);
}
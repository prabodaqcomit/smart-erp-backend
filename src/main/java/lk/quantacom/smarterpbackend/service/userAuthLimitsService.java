package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.userAuthLimitsRequest;
import lk.quantacom.smarterpbackend.dto.request.userAuthLimitsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.userAuthLimitsResponse;
import lk.quantacom.smarterpbackend.entity.userAuthLimits;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userAuthLimitsService {

    userAuthLimitsResponse save(userAuthLimitsRequest request);

    userAuthLimitsResponse update(userAuthLimitsUpdateRequest request);

    userAuthLimitsResponse getById(Long id);

    List<userAuthLimitsResponse> getAll();

    Integer delete(Long id);

    List<userAuthLimitsResponse> getByUsername(String username);

}
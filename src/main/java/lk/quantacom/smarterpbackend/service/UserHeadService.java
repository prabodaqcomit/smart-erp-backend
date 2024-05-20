package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.UserHeadRequest;
import lk.quantacom.smarterpbackend.dto.request.UserHeadUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UserHeadResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserHeadService {

    UserHeadResponse save(UserHeadRequest request);

    UserHeadResponse update(UserHeadUpdateRequest request);

    UserHeadResponse getById(Long id);

    UserHeadResponse getByUn(String user);

    List<UserHeadResponse> getAll();

    Integer delete(Long id);
}
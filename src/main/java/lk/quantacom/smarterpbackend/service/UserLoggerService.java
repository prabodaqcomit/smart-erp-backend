package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.UserLoggerRequest;
import lk.quantacom.smarterpbackend.dto.request.UserLoggerUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UserLoggerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLoggerService {

    UserLoggerResponse save(UserLoggerRequest request);

    UserLoggerResponse update(UserLoggerUpdateRequest request);

    UserLoggerResponse getById(Long id);

    List<UserLoggerResponse> getAll();


    Integer delete(Long id);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GLSupPayDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GLSupPayDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLSupPayDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GLSupPayDetailTemplateService {

    GLSupPayDetailResponse save(GLSupPayDetailRequest request);

    GLSupPayDetailResponse update(GLSupPayDetailUpdateRequest request);

    GLSupPayDetailResponse getById(Long id);

    List<GLSupPayDetailResponse> getAll();


    Integer delete(Long id);
}
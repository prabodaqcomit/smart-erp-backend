package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.SizeRequest;
import lk.quantacom.smarterpbackend.dto.request.SizeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SizeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SizeService {

    SizeResponse save(SizeRequest request);

    SizeResponse update(SizeUpdateRequest request);

    SizeResponse getById(Long id);

    List<SizeResponse> getAll();


    Integer delete(Long id);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GrnDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrnDetailsService {

    GrnDetailsResponse save(GrnDetailsRequest request);

    GrnDetailsResponse update(GrnDetailsUpdateRequest request);

    GrnDetailsResponse getById(Long id);

    List<GrnDetailsResponse> getAll();


    Integer delete(Long id);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BomDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.BomDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BomDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BomDetailsService {

    BomDetailsResponse save(BomDetailsRequest request);

    BomDetailsResponse update(BomDetailsUpdateRequest request);

    BomDetailsResponse getById(Long id);

    List<BomDetailsResponse> getAll();


    Integer delete(Long id);
}
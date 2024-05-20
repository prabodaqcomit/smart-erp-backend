package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.MasterDataTypesRequest;
import lk.quantacom.smarterpbackend.dto.request.MasterDataTypesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MasterDataTypesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MasterDataTypesService {

    MasterDataTypesResponse save(MasterDataTypesRequest request);

    MasterDataTypesResponse update(MasterDataTypesUpdateRequest request);

    MasterDataTypesResponse getById(Long id);

    List<MasterDataTypesResponse> getAll();


    Integer delete(Long id);
}
package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ModuleNamesRequest;
import lk.quantacom.smarterpbackend.dto.request.ModuleNamesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ModuleNamesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleNamesService {

    ModuleNamesResponse save(ModuleNamesRequest request);

    ModuleNamesResponse update(ModuleNamesUpdateRequest request);

    ModuleNamesResponse getById(Long id);

    List<ModuleNamesResponse> getAll();


    Integer delete(Long id);
}
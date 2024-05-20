package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.DepartmentRegRequest;
import lk.quantacom.smarterpbackend.dto.request.DepartmentRegUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DepartmentRegResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentRegService {

    DepartmentRegResponse save(DepartmentRegRequest request);

    DepartmentRegResponse update(DepartmentRegUpdateRequest request);

    DepartmentRegResponse getById(Long id);

    List<DepartmentRegResponse> getAll();


    Integer delete(Long id);
}
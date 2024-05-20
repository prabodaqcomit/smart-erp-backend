package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BranchTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.BranchTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BranchTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BranchTypeService {

    BranchTypeResponse save(BranchTypeRequest request);

    BranchTypeResponse update(BranchTypeUpdateRequest request);

    BranchTypeResponse getById(Long id);

    List<BranchTypeResponse> getAll();


    Integer delete(Long id);
}
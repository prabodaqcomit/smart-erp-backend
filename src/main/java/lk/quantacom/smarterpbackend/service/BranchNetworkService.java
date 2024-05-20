package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BranchNetworkRequest;
import lk.quantacom.smarterpbackend.dto.request.BranchNetworkUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BranchNetworkResponse;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BranchNetworkService {

    BranchNetworkResponse save(BranchNetworkRequest request);

    BranchNetworkResponse update(BranchNetworkUpdateRequest request);

    BranchNetworkResponse getById(Long id);

    List<BranchNetworkResponse> getAll();

    List<BranchNetworkResponse> getAllPublic();

    Integer delete(Long id);

    List<BranchNetworkResponse> getByBranchName(String branchName);

    List<BranchNetworkResponse> getByBranchType(Long branchType);
}
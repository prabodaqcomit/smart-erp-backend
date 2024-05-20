package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PettyCashRequest;
import lk.quantacom.smarterpbackend.dto.request.PettyCashUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PettyCashResponse;
import lk.quantacom.smarterpbackend.entity.PettyCash;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PettyCashService {

    PettyCashResponse save(PettyCashRequest request);

    PettyCashResponse update(PettyCashUpdateRequest request);

    PettyCashResponse getById(Long id);

    List<PettyCashResponse> getAll();

    Integer delete(Long id);

    List<PettyCashResponse> findByPayeeName(String payeeName);

    List<PettyCashResponse> findByDesctription(String desctription);

    List<PettyCashResponse> getByDateAndBranchId(String Date,Integer branchId);

    List<PettyCashResponse> getByPayeeNameAndDateAndBranchId(String payeeName,String startDate,String endDate,Integer branchId);

    List<PettyCashResponse> getAllByDateAndBranchId(String startDate,String endDate,Integer branchId);

    List<PettyCashResponse> getByDateRangeAndBranchId(String startDate,String endDate,Integer branchId);




}
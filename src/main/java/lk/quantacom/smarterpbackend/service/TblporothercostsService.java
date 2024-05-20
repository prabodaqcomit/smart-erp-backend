package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblporheaderRequestApprovalRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporothercostsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporothercostsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporfitsResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporothercostsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblporothercostsService {

    TblporothercostsResponse save(TblporothercostsRequest request);

    TblporothercostsResponse update(TblporothercostsUpdateRequest request);

    TblporothercostsResponse getById(Integer id);

    List<TblporothercostsResponse> getAll();


    Integer delete(Integer id);

    TblporheaderResponse requestApprovalForApproval(TblporheaderRequestApprovalRequest requestApprovalRequest);

    List<TblporothercostsResponse> getByPorId(String porId);
}
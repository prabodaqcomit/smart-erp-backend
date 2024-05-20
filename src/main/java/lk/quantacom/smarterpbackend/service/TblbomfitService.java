package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblbomfitRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomfitUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomfitResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblbomfitService {

    TblbomfitResponse save(TblbomfitRequest request);

    TblbomfitResponse update(TblbomfitUpdateRequest request);

    TblbomfitResponse getById(Long id);

    List<TblbomfitResponse> getAll();


    Integer delete(Integer bfId);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblbomsizeRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomsizeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomsizeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblbomsizeService {

    TblbomsizeResponse save(TblbomsizeRequest request);

    TblbomsizeResponse update(TblbomsizeUpdateRequest request);

    TblbomsizeResponse getById(Long id);

    List<TblbomsizeResponse> getAll();


    Integer delete(Integer bsId);
}
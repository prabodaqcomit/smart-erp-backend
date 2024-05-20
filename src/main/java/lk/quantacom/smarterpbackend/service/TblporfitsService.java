package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblporfitsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporfitsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporfitsResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporsizesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblporfitsService {

    TblporfitsResponse save(TblporfitsRequest request);

    TblporfitsResponse update(TblporfitsUpdateRequest request);

    TblporfitsResponse getById(Long id);

    List<TblporfitsResponse> getAll();


    Integer delete(Long id);

    List<TblporfitsResponse> getByPorId(String porId);



}
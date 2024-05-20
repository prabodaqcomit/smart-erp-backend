package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblporsizesRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporsizesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporsizesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblporsizesService {

    TblporsizesResponse save(TblporsizesRequest request);

    TblporsizesResponse update(TblporsizesUpdateRequest request);

    TblporsizesResponse getById(Long id);

    List<TblporsizesResponse> getAll();

    Integer delete(Long id);

    List<TblporsizesResponse> getByPorId(String porId);



}
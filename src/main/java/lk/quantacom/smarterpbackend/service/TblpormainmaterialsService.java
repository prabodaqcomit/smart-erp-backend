package lk.quantacom.smarterpbackend.service;

import feign.Param;
import lk.quantacom.smarterpbackend.dto.request.TblpormainmaterialsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpormainmaterialsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpormainmaterialsResponse;
import lk.quantacom.smarterpbackend.dto.response.getPorByPorIdResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblpormainmaterialsService {

    TblpormainmaterialsResponse save(TblpormainmaterialsRequest request);

    TblpormainmaterialsResponse update(TblpormainmaterialsUpdateRequest request);

    TblpormainmaterialsResponse getById(Long id);

    List<TblpormainmaterialsResponse> getAll();


    Integer delete(Long id);

    List<getPorByPorIdResponse> getPorByPorId(String porId);
}
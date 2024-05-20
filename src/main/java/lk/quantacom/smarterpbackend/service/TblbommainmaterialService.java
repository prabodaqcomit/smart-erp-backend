package lk.quantacom.smarterpbackend.service;

import feign.Param;
import lk.quantacom.smarterpbackend.dto.request.TblbommainmaterialRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbommainmaterialUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GetBomMaterialByStockMaterialDescResponse;
import lk.quantacom.smarterpbackend.dto.response.TblbommainmaterialResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblbommainmaterialService {

    TblbommainmaterialResponse save(TblbommainmaterialRequest request);

    TblbommainmaterialResponse update(TblbommainmaterialUpdateRequest request);

    TblbommainmaterialResponse getById(Long id);

    List<TblbommainmaterialResponse> getAll();


    Integer delete(Integer bomId);

    List<GetBomMaterialByStockMaterialDescResponse> getBomMaterialsByBomId(Integer bomId);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblbomheaderRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomheaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BOMAllResponse;
import lk.quantacom.smarterpbackend.dto.response.BomMainMaterialForPOR;
import lk.quantacom.smarterpbackend.dto.response.GetForBomAllItemCodeByDescResponse;
import lk.quantacom.smarterpbackend.dto.response.TblbomheaderResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface TblbomheaderService {

    TblbomheaderResponse save(TblbomheaderRequest request);

    TblbomheaderResponse update(TblbomheaderUpdateRequest request);

    TblbomheaderResponse getById(Integer id);

    List<TblbomheaderResponse> getAll();

    Integer delete(Integer bomId);

    List<GetForBomAllItemCodeByDescResponse> getAllItemCodeByDesc(String itemName);

    List<GetForBomAllItemCodeByDescResponse> getAllItemCodeByDesc2(String itemName);

    BOMAllResponse getFromAllTables(Integer bomId);

    List<BomMainMaterialForPOR> getMaterialForPOR();

    File printBomHeader(Integer bomId);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import lk.quantacom.smarterpbackend.dto.response.GetAllItemCodesByDescResponse;
import lk.quantacom.smarterpbackend.dto.response.getPorAccessoryResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface TblporheaderService {

    TblporheaderResponse save(TblporheaderRequest request);

    TblporheaderResponse update(TblporheaderUpdateRequest request);

    TblporheaderResponse getById(Long id);

    List<TblporheaderResponse> getAll();

    List<TblporheaderResponse> getAllAvailable();

    List<TblporheaderResponse> getAllApproved();

    Integer delete(Long id);

    List<TblporheaderResponse> getByPorId(String porId,Boolean approved);

    TblporheaderResponse saveToPorTables(TblporheaderAllListRequest request);

    Integer reduceFromStock(TblporheaderReduceFromStockRequest updateRequest);

    TblporheaderResponse requestApprovalForApproved(TblporheaderRequestApprovalRequest requestApprovalRequest);

    Integer confirmApproval(String porId);

    List<TblporheaderResponse> getByPorIdAndPorApprovedAndPorApproveRequest(String porId);

    List<getPorAccessoryResponse> getByStockAndItemPorAccessory(String batchNo, String itemCode, Long branchId,
        Long sizeId, Long fitId, Long colorId
    );

    Integer updateStatus(String porId);

    Integer reduceFromStockByConvertToGrn(TblporheaderReduceFromStockRequest updateRequest);

    File printPOR(String porID);
}
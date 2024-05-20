package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PurchaseOrderRequest;
import lk.quantacom.smarterpbackend.dto.request.PurchaseOrderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PurchaseOrderResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface PurchaseOrderService {

    File print(String po,Long branchId);

    List<PurchaseOrderResponse> save(List<PurchaseOrderRequest> requestList);

    PurchaseOrderResponse update(PurchaseOrderUpdateRequest request);

    PurchaseOrderResponse getById(Long id);

    List<PurchaseOrderResponse> getByPOId(Long id);

    List<PurchaseOrderResponse> getAll();

    List<PurchaseOrderResponse> getHeadList(String type);

    List<PurchaseOrderResponse> getHeadListForGrn();

    Integer delete(Long id);

    String bulkApprove(List<Integer> poNo);
}
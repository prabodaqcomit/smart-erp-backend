package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ItemMasterRequest;
import lk.quantacom.smarterpbackend.dto.request.ItemMasterUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ItemMasterResponse;
import lk.quantacom.smarterpbackend.dto.response.StockResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemMasterService {

    ItemMasterResponse save(ItemMasterRequest request);

    ItemMasterResponse update(ItemMasterUpdateRequest request);

    ItemMasterResponse getById(String id);

    ItemMasterResponse getByItemCode(String code);

    List<ItemMasterResponse> getAll();

    List<ItemMasterResponse> getAllAll();

    Integer delete(String id);

}
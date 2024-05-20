package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblporaccessoriesRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporaccessoriesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporaccessoriesResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporfitsResponse;
import lk.quantacom.smarterpbackend.dto.response.getByStockAndTblPorAccessoriesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblporaccessoriesService {

    TblporaccessoriesResponse save(TblporaccessoriesRequest request);

    TblporaccessoriesResponse update(TblporaccessoriesUpdateRequest request);

    TblporaccessoriesResponse getById(Long id);

    List<TblporaccessoriesResponse> getAll();

    Integer delete(Long id);

    List<getByStockAndTblPorAccessoriesResponse> getByStockAndTblPorAccessory(String porId);


}
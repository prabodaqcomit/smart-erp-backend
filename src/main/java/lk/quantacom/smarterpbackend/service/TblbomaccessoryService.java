package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblbomaccessoryRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomaccessoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomaccessoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblbomaccessoryService {

    TblbomaccessoryResponse save(TblbomaccessoryRequest request);

    TblbomaccessoryResponse update(TblbomaccessoryUpdateRequest request);

    TblbomaccessoryResponse getById(Long id);

    List<TblbomaccessoryResponse> getAll();


    Integer delete(Integer bdId);
}
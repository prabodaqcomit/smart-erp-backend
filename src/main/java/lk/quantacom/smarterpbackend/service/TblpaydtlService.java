package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblpaydtlRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpaydtlUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpaydtlResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblpaydtlService {

    TblpaydtlResponse save(TblpaydtlRequest request);

    TblpaydtlResponse update(TblpaydtlUpdateRequest request);

    TblpaydtlResponse getById(String id);

    List<TblpaydtlResponse> getAll();


    Integer delete(String id);
}
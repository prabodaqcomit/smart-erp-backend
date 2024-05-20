package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblpaymodeRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpaymodeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpaymodeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblpaymodeService {

    TblpaymodeResponse save(TblpaymodeRequest request);

    TblpaymodeResponse update(TblpaymodeUpdateRequest request);

    TblpaymodeResponse getById(Long id);

    List<TblpaymodeResponse> getAll();


    Integer delete(Long id);
}
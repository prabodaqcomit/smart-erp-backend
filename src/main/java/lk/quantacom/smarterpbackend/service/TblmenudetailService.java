package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblmenudetailRequest;
import lk.quantacom.smarterpbackend.dto.request.TblmenudetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblmenudetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblmenudetailService {

    TblmenudetailResponse save(TblmenudetailRequest request);

    TblmenudetailResponse update(TblmenudetailUpdateRequest request);

    TblmenudetailResponse getById(String id);

    List<TblmenudetailResponse> getAll();


    Integer delete(String id);
}
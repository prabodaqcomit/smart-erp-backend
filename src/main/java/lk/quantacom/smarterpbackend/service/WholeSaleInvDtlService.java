package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvDtlRequest;
import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvDtlUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.WholeSaleInvDtlResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WholeSaleInvDtlService {

    WholeSaleInvDtlResponse save(WholeSaleInvDtlRequest request);

    WholeSaleInvDtlResponse update(WholeSaleInvDtlUpdateRequest request);

    WholeSaleInvDtlResponse getById(Long id);

    List<WholeSaleInvDtlResponse> getAll();

    Integer delete(Long id);
}
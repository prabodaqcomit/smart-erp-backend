package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvHedRequest;
import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvHedUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvRequest;
import lk.quantacom.smarterpbackend.dto.response.WholeSaleInvHedResponse;
import lk.quantacom.smarterpbackend.dto.response.getWholeSaleInvDtlResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WholeSaleInvHedService {

    WholeSaleInvHedResponse save(WholeSaleInvHedRequest request);

    WholeSaleInvHedResponse update(WholeSaleInvHedUpdateRequest request);

    WholeSaleInvHedResponse getById(Integer id);

    List<WholeSaleInvHedResponse> getAll();

    Integer delete(Integer id);

    WholeSaleInvHedResponse saveInvoice(WholeSaleInvRequest request);

    Integer getWhInvNo();

    List<getWholeSaleInvDtlResponse> getWholeSaleInvDtl();

    Integer updateCancel(String invNo);

    WholeSaleInvHedResponse returnWSInvoice(WholeSaleInvRequest request);

    String getWhTXInvNo();

    String getWhRInvNo();
}


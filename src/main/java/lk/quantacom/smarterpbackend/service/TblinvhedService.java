package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TblinvhedRequest;
import lk.quantacom.smarterpbackend.dto.request.TblinvhedUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblinvListDtlsResponse;
import lk.quantacom.smarterpbackend.dto.response.TblinvhedResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblinvhedService {

    TblinvhedResponse save(TblinvhedRequest request);

    TblinvhedResponse update(TblinvhedUpdateRequest request);

    TblinvhedResponse getById(String id);

    List<TblinvhedResponse> getAll();

    Integer delete(String id);

    TblinvListDtlsResponse TblinvListDtls(String fldInvno);

}
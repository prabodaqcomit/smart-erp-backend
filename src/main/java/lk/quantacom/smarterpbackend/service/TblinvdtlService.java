package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.Tblinvdtl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TblinvdtlService {

    TblinvdtlResponse save(TblinvdtlRequest request);

    TblinvdtlResponse update(TblinvdtlUpdateRequest request);

    TblinvdtlResponse getById(Long id);

    List<TblinvdtlResponse> getAll();

    Integer delete(Long id);

    List<TblinvdtlResponse> getAllByInvno(String fldInvno);

//    List<getMonthlyInvDtlRequestResponse> getMonthlyInv(getMontlyInvRequest request);

    TblinvdtlResponse saveWholeSaleInv(WholeSaleTblInvdtlRequest request);

//    List<getMonthlyInvDtlsResponse> getMonthlyInvDtl(getMontlyInvRequest request);

//    List<getMonthlyInvDtlRequestResponse> getMonthlyInvReturn(getMontlyInvReturnRequest request);

//    List<getMonthlyInvDtlsResponse> getMonthlyInvDtlReturn(getMontlyInvReturnRequest request);

    List<getMonthlyDtlsByAllResponse> getMonthlyInvDtls(getMontlyInvRequest request);

    List<getMonthlyDtlsByAllResponse> getMonthlyInvDtlsReturn(getMontlyInvReturnRequest request);
}

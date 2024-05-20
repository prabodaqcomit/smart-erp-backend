package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblpaydtlRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpaydtlUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpaydtlResponse;
import lk.quantacom.smarterpbackend.entity.Tblpaydtl;
import lk.quantacom.smarterpbackend.entity.TblpaydtlPK;
import lk.quantacom.smarterpbackend.repository.TblpaydtlRepository;
import lk.quantacom.smarterpbackend.service.TblpaydtlService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblpaydtlServiceImpl implements TblpaydtlService {

    @Autowired
    private TblpaydtlRepository tblpaydtlRepository;

    private static TblpaydtlResponse convert(Tblpaydtl tblpaydtl) {

        TblpaydtlResponse typeResponse = new TblpaydtlResponse();
        typeResponse.setFldInvpaydtlkey(tblpaydtl.getTblpaydtlPK().getFldInvpaydtlkey());
        typeResponse.setFldDate(tblpaydtl.getTblpaydtlPK().getFldDate());
        typeResponse.setFldLocation(tblpaydtl.getTblpaydtlPK().getFldLocation());
        typeResponse.setFldInvno(tblpaydtl.getTblpaydtlPK().getFldInvno());
        typeResponse.setFldPaymode(tblpaydtl.getFldPaymode());
        typeResponse.setFldPaytype(tblpaydtl.getFldPaytype());
        typeResponse.setFldPaytypecode(tblpaydtl.getFldPaytypecode());
        typeResponse.setFldCrdcardno(tblpaydtl.getFldCrdcardno());
        typeResponse.setFldFcuramt(tblpaydtl.getFldFcuramt());
        typeResponse.setFldPaytypeamt(tblpaydtl.getFldPaytypeamt());
        typeResponse.setFldExchngrate(tblpaydtl.getFldExchngrate());
        typeResponse.setFldDatatransfer(tblpaydtl.getFldDatatransfer());
        typeResponse.setFldGvdisno(tblpaydtl.getFldGvdisno());
        typeResponse.setFldCreditno(tblpaydtl.getFldCreditno());
        typeResponse.setFldComno(tblpaydtl.getFldComno());
        typeResponse.setFldCashierid(tblpaydtl.getFldCashierid());
        typeResponse.setFldEncrkey(tblpaydtl.getFldEncrkey());
        typeResponse.setBlnconfirmed(tblpaydtl.getBlnconfirmed());
        typeResponse.setBlnmodechange(tblpaydtl.getBlnmodechange());
        typeResponse.setFldCancel(tblpaydtl.getFldCancel());
        typeResponse.setFldAccessupdate(tblpaydtl.getFldAccessupdate());
        typeResponse.setFldMiddlewareuuid(tblpaydtl.getFldMiddlewareuuid());
        typeResponse.setFldMiddlewarestatus(tblpaydtl.getFldMiddlewarestatus());
        typeResponse.setFldMiddlewareuuidCreditcust(tblpaydtl.getFldMiddlewareuuidCreditcust());
        typeResponse.setFldMiddlewarestatusCreditcust(tblpaydtl.getFldMiddlewarestatusCreditcust());
        typeResponse.setFldMiddlewareuuidBw(tblpaydtl.getFldMiddlewareuuidBw());
        typeResponse.setFldMiddlewarestatusBw(tblpaydtl.getFldMiddlewarestatusBw());
        typeResponse.setCreatedon(tblpaydtl.getCreatedon());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblpaydtlResponse save(TblpaydtlRequest request) {

        Tblpaydtl tblpaydtl = new Tblpaydtl();
        TblpaydtlPK tblpaydtlPK = new TblpaydtlPK(ConvertUtils.convertStrToDate(request.getFldDate()),request.getFldLocation(),request.getFldInvno());
        tblpaydtl.setTblpaydtlPK(tblpaydtlPK);
        tblpaydtl.setFldPaymode(request.getFldPaymode());
        tblpaydtl.setFldPaytype(request.getFldPaytype());
        tblpaydtl.setFldPaytypecode(request.getFldPaytypecode());
        tblpaydtl.setFldCrdcardno(request.getFldCrdcardno());
        tblpaydtl.setFldFcuramt(request.getFldFcuramt());
        tblpaydtl.setFldPaytypeamt(request.getFldPaytypeamt());
        tblpaydtl.setFldExchngrate(request.getFldExchngrate());
        tblpaydtl.setFldDatatransfer(request.getFldDatatransfer());
        tblpaydtl.setFldGvdisno(request.getFldGvdisno());
        tblpaydtl.setFldCreditno(request.getFldCreditno());
        tblpaydtl.setFldComno(request.getFldComno());
        tblpaydtl.setFldCashierid(request.getFldCashierid());
        tblpaydtl.setFldEncrkey(request.getFldEncrkey());
        tblpaydtl.setBlnconfirmed(request.getBlnconfirmed());
        tblpaydtl.setBlnmodechange(request.getBlnmodechange());
        tblpaydtl.setFldCancel(request.getFldCancel());
        tblpaydtl.setFldAccessupdate(request.getFldAccessupdate());
        tblpaydtl.setFldMiddlewareuuid(request.getFldMiddlewareuuid());
        tblpaydtl.setFldMiddlewarestatus(request.getFldMiddlewarestatus());
        tblpaydtl.setFldMiddlewareuuidCreditcust(request.getFldMiddlewareuuidCreditcust());
        tblpaydtl.setFldMiddlewarestatusCreditcust(request.getFldMiddlewarestatusCreditcust());
        tblpaydtl.setFldMiddlewareuuidBw(request.getFldMiddlewareuuidBw());
        tblpaydtl.setFldMiddlewarestatusBw(request.getFldMiddlewarestatusBw());
        tblpaydtl.setCreatedon(request.getCreatedon() == null ? null : ConvertUtils.convertStrToDate(request.getCreatedon()));
        Tblpaydtl save = tblpaydtlRepository.save(tblpaydtl);

        return convert(save);
    }

    @Override
    @Transactional
    public TblpaydtlResponse update(TblpaydtlUpdateRequest request) {

        Tblpaydtl tblpaydtl = tblpaydtlRepository.findById(request.getFldInvno()).orElse(null);
        if (tblpaydtl == null) {
            return null;
        }


        TblpaydtlPK tblpaydtlPK = new TblpaydtlPK(ConvertUtils.convertStrToDate(request.getFldDate()),request.getFldLocation(),request.getFldInvno());
        tblpaydtl.setTblpaydtlPK(tblpaydtlPK);
        tblpaydtl.setFldPaymode(request.getFldPaymode());
        tblpaydtl.setFldPaytype(request.getFldPaytype());
        tblpaydtl.setFldPaytypecode(request.getFldPaytypecode());
        tblpaydtl.setFldCrdcardno(request.getFldCrdcardno());
        tblpaydtl.setFldFcuramt(request.getFldFcuramt());
        tblpaydtl.setFldPaytypeamt(request.getFldPaytypeamt());
        tblpaydtl.setFldExchngrate(request.getFldExchngrate());
        tblpaydtl.setFldDatatransfer(request.getFldDatatransfer());
        tblpaydtl.setFldGvdisno(request.getFldGvdisno());
        tblpaydtl.setFldCreditno(request.getFldCreditno());
        tblpaydtl.setFldComno(request.getFldComno());
        tblpaydtl.setFldCashierid(request.getFldCashierid());
        tblpaydtl.setFldEncrkey(request.getFldEncrkey());
        tblpaydtl.setBlnconfirmed(request.getBlnconfirmed());
        tblpaydtl.setBlnmodechange(request.getBlnmodechange());
        tblpaydtl.setFldCancel(request.getFldCancel());
        tblpaydtl.setFldAccessupdate(request.getFldAccessupdate());
        tblpaydtl.setFldMiddlewareuuid(request.getFldMiddlewareuuid());
        tblpaydtl.setFldMiddlewarestatus(request.getFldMiddlewarestatus());
        tblpaydtl.setFldMiddlewareuuidCreditcust(request.getFldMiddlewareuuidCreditcust());
        tblpaydtl.setFldMiddlewarestatusCreditcust(request.getFldMiddlewarestatusCreditcust());
        tblpaydtl.setFldMiddlewareuuidBw(request.getFldMiddlewareuuidBw());
        tblpaydtl.setFldMiddlewarestatusBw(request.getFldMiddlewarestatusBw());
        tblpaydtl.setCreatedon(request.getCreatedon() == null ? null : ConvertUtils.convertStrToDate(request.getCreatedon()));
        Tblpaydtl updated = tblpaydtlRepository.save(tblpaydtl);

        return (convert(updated));
    }

    @Override
    public TblpaydtlResponse getById(String id) {

        return tblpaydtlRepository.findById(id).map(TblpaydtlServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblpaydtlResponse> getAll() {
        return tblpaydtlRepository.findAll().stream().map(TblpaydtlServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer delete(String fldInvno) {
        Tblpaydtl got = tblpaydtlRepository.findById(fldInvno).orElse(null);
        tblpaydtlRepository.delete(got);
        return 1;
    }
}
package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblpaymodeRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpaymodeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpaymodeResponse;
import lk.quantacom.smarterpbackend.entity.Tblpaymode;
import lk.quantacom.smarterpbackend.repository.TblpaymodeRepository;
import lk.quantacom.smarterpbackend.service.TblpaymodeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblpaymodeServiceImpl implements TblpaymodeService {

    @Autowired
    private TblpaymodeRepository tblpaymodeRepository;

    public static TblpaymodeResponse convert(Tblpaymode tblpaymode) {

        TblpaymodeResponse typeResponse = new TblpaymodeResponse();
        typeResponse.setPaymodeCode(tblpaymode.getPaymodeCode());
        typeResponse.setPaymodeDesc(tblpaymode.getPaymodeDesc());
        typeResponse.setPaymodeFormat(tblpaymode.getPaymodeFormat());
        typeResponse.setPaymodeCardformat(tblpaymode.getPaymodeCardformat());
        typeResponse.setPaymodeExchangerate(tblpaymode.getPaymodeExchangerate());
        typeResponse.setPaymodeCommission(tblpaymode.getPaymodeCommission());
        typeResponse.setPaymodeActive(tblpaymode.getPaymodeActive());
        typeResponse.setPaymodeOrder(tblpaymode.getPaymodeOrder());
        typeResponse.setPaymodeIsrCode(tblpaymode.getPaymodeIsrCode());
        typeResponse.setPaymodePath(tblpaymode.getPaymodePath());
        typeResponse.setPaymodeFOrder(tblpaymode.getPaymodeFOrder());
        typeResponse.setPaymodeGvEnable(tblpaymode.getPaymodeGvEnable());
        typeResponse.setPaymodeIsDetail(tblpaymode.getPaymodeIsDetail());
        typeResponse.setPaymodeIsCreditcard(tblpaymode.getPaymodeIsCreditcard());
        typeResponse.setPaymodeIsForeigncurrency(tblpaymode.getPaymodeIsForeigncurrency());
        typeResponse.setPaymodeIsNexus(tblpaymode.getPaymodeIsNexus());
        typeResponse.setPaymodeDcEnable(tblpaymode.getPaymodeDcEnable());
        typeResponse.setId(tblpaymode.getId());
//        typeResponse.setCreatedBy(tblpaymode.getCreatedBy());
//        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblpaymode.getCreatedDateTime()));
//        typeResponse.setModifiedBy(tblpaymode.getModifiedBy());
//        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblpaymode.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblpaymode.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblpaymodeResponse save(TblpaymodeRequest request) {

        Tblpaymode tblpaymode = new Tblpaymode();
        tblpaymode.setPaymodeCode(request.getPaymodeCode());
        tblpaymode.setPaymodeDesc(request.getPaymodeDesc());
        tblpaymode.setPaymodeFormat(request.getPaymodeFormat());
        tblpaymode.setPaymodeCardformat(request.getPaymodeCardformat());
        tblpaymode.setPaymodeExchangerate(request.getPaymodeExchangerate());
        tblpaymode.setPaymodeCommission(request.getPaymodeCommission());
        tblpaymode.setPaymodeActive(request.getPaymodeActive());
        tblpaymode.setPaymodeOrder(request.getPaymodeOrder());
        tblpaymode.setPaymodeIsrCode(request.getPaymodeIsrCode());
        tblpaymode.setPaymodePath(request.getPaymodePath());
        tblpaymode.setPaymodeFOrder(request.getPaymodeFOrder());
        tblpaymode.setPaymodeGvEnable(request.getPaymodeGvEnable());
        tblpaymode.setPaymodeIsDetail(request.getPaymodeIsDetail());
        tblpaymode.setPaymodeIsCreditcard(request.getPaymodeIsCreditcard());
        tblpaymode.setPaymodeIsForeigncurrency(request.getPaymodeIsForeigncurrency());
        tblpaymode.setPaymodeIsNexus(request.getPaymodeIsNexus());
        tblpaymode.setPaymodeDcEnable(request.getPaymodeDcEnable());
        tblpaymode.setIsDeleted(Deleted.NO);
        Tblpaymode save = tblpaymodeRepository.save(tblpaymode);

        return convert(save);
    }

    @Override
    @Transactional
    public TblpaymodeResponse update(TblpaymodeUpdateRequest request) {

        Tblpaymode tblpaymode = tblpaymodeRepository.findById(request.getId()).orElse(null);
        if (tblpaymode == null) {
            return null;
        }

//tblpaymode.setId(request.getId());
        tblpaymode.setPaymodeCode(request.getPaymodeCode());
        tblpaymode.setPaymodeDesc(request.getPaymodeDesc());
        tblpaymode.setPaymodeFormat(request.getPaymodeFormat());
        tblpaymode.setPaymodeCardformat(request.getPaymodeCardformat());
        tblpaymode.setPaymodeExchangerate(request.getPaymodeExchangerate());
        tblpaymode.setPaymodeCommission(request.getPaymodeCommission());
        tblpaymode.setPaymodeActive(request.getPaymodeActive());
        tblpaymode.setPaymodeOrder(request.getPaymodeOrder());
        tblpaymode.setPaymodeIsrCode(request.getPaymodeIsrCode());
        tblpaymode.setPaymodePath(request.getPaymodePath());
        tblpaymode.setPaymodeFOrder(request.getPaymodeFOrder());
        tblpaymode.setPaymodeGvEnable(request.getPaymodeGvEnable());
        tblpaymode.setPaymodeIsDetail(request.getPaymodeIsDetail());
        tblpaymode.setPaymodeIsCreditcard(request.getPaymodeIsCreditcard());
        tblpaymode.setPaymodeIsForeigncurrency(request.getPaymodeIsForeigncurrency());
        tblpaymode.setPaymodeIsNexus(request.getPaymodeIsNexus());
        tblpaymode.setPaymodeDcEnable(request.getPaymodeDcEnable());
        Tblpaymode updated = tblpaymodeRepository.save(tblpaymode);

        return (convert(updated));
    }

    @Override
    @Transactional
    public TblpaymodeResponse getById(Long id) {
        tblpaymodeRepository.updateId();
        return tblpaymodeRepository.findById(id).map(TblpaymodeServiceImpl::convert).orElse(null);
    }

    @Override
    @Transactional
    public List<TblpaymodeResponse> getAll() {
        tblpaymodeRepository.updateId();
        return tblpaymodeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblpaymodeServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Tblpaymode got = tblpaymodeRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblpaymodeRepository.save(got);

        return 1;
    }
}
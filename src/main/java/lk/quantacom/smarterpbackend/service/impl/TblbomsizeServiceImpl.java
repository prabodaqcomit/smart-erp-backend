package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblbomsizeRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomsizeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomsizeResponse;
import lk.quantacom.smarterpbackend.entity.Tblbomsize;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblbomsizeRepository;
import lk.quantacom.smarterpbackend.service.TblbomsizeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblbomsizeServiceImpl implements TblbomsizeService {

    @Autowired
    private TblbomsizeRepository tblbomsizeRepository;

    private static TblbomsizeResponse convert(Tblbomsize tblbomsize) {

        TblbomsizeResponse typeResponse = new TblbomsizeResponse();
        typeResponse.setBsDesc(tblbomsize.getBsDesc());
        typeResponse.setBsId(tblbomsize.getBsId());
        typeResponse.setBsSizeId(tblbomsize.getBsSizeId());
        typeResponse.setId(tblbomsize.getId());
        typeResponse.setCreatedBy(tblbomsize.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblbomsize.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblbomsize.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblbomsize.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblbomsize.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblbomsizeResponse save(TblbomsizeRequest request) {

        Tblbomsize tblbomsize = new Tblbomsize();
        tblbomsize.setBsDesc(request.getBsDesc());
        tblbomsize.setBsId(request.getBsId());
        tblbomsize.setBsSizeId(request.getBsSizeId());
        tblbomsize.setIsDeleted(Deleted.NO);
        Tblbomsize save = tblbomsizeRepository.save(tblbomsize);

        return convert(save);
    }

    @Override
    @Transactional
    public TblbomsizeResponse update(TblbomsizeUpdateRequest request) {

        Tblbomsize tblbomsize = tblbomsizeRepository.findById(request.getId()).orElse(null);
        if (tblbomsize == null) {
            return null;
        }

        tblbomsize.setId(request.getId());
        tblbomsize.setBsDesc(request.getBsDesc());
        tblbomsize.setBsId(request.getBsId());
        tblbomsize.setBsSizeId(request.getBsSizeId());
        Tblbomsize updated = tblbomsizeRepository.save(tblbomsize);

        return (convert(updated));
    }

    @Override
    public TblbomsizeResponse getById(Long id) {

        return tblbomsizeRepository.findById(id).map(TblbomsizeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblbomsizeResponse> getAll() {

        return tblbomsizeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblbomsizeServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer bsId) {

//        Tblbomsize got = tblbomsizeRepository.findByBsId(bsId);
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        tblbomsizeRepository.save(got);

        return 1;
    }
}
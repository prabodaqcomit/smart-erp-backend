package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblbomfitRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomfitUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomfitResponse;
import lk.quantacom.smarterpbackend.entity.Tblbomfit;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblbomfitRepository;
import lk.quantacom.smarterpbackend.service.TblbomfitService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblbomfitServiceImpl implements TblbomfitService {

    @Autowired
    private TblbomfitRepository tblbomfitRepository;

    private static TblbomfitResponse convert(Tblbomfit tblbomfit) {

        TblbomfitResponse typeResponse = new TblbomfitResponse();
        typeResponse.setBfDesc(tblbomfit.getBfDesc());
        typeResponse.setBfFitId(tblbomfit.getBfFitId());
        typeResponse.setBfId(tblbomfit.getBfId());
        typeResponse.setId(tblbomfit.getId());
        typeResponse.setCreatedBy(tblbomfit.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblbomfit.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblbomfit.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblbomfit.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblbomfit.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblbomfitResponse save(TblbomfitRequest request) {

        Tblbomfit tblbomfit = new Tblbomfit();
        tblbomfit.setBfDesc(request.getBfDesc());
        tblbomfit.setBfFitId(request.getBfFitId());
        tblbomfit.setBfId(request.getBfId());
        tblbomfit.setIsDeleted(Deleted.NO);
        Tblbomfit save = tblbomfitRepository.save(tblbomfit);

        return convert(save);
    }

    @Override
    @Transactional
    public TblbomfitResponse update(TblbomfitUpdateRequest request) {

        Tblbomfit tblbomfit = tblbomfitRepository.findById(request.getId()).orElse(null);
        if (tblbomfit == null) {
            return null;
        }

        tblbomfit.setId(request.getId());
        tblbomfit.setBfDesc(request.getBfDesc());
        tblbomfit.setBfFitId(request.getBfFitId());
        tblbomfit.setBfId(request.getBfId());
        Tblbomfit updated = tblbomfitRepository.save(tblbomfit);

        return (convert(updated));
    }

    @Override
    public TblbomfitResponse getById(Long id) {

        return tblbomfitRepository.findById(id).map(TblbomfitServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblbomfitResponse> getAll() {

        return tblbomfitRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblbomfitServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer bfId) {

//        Tblbomfit got = tblbomfitRepository.findByBfId(bfId);
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        tblbomfitRepository.save(got);

        return 1;
    }
}
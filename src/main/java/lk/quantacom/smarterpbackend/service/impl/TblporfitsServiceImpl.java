package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblporfitsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporfitsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporfitsResponse;
import lk.quantacom.smarterpbackend.entity.Tblporfits;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblporfitsRepository;
import lk.quantacom.smarterpbackend.service.TblporfitsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblporfitsServiceImpl implements TblporfitsService {

    @Autowired
    private TblporfitsRepository tblporfitsRepository;

    private static TblporfitsResponse convert(Tblporfits tblporfits) {

        TblporfitsResponse typeResponse = new TblporfitsResponse();
        typeResponse.setPorFitDesc(tblporfits.getPorFitDesc());
        typeResponse.setPorFitId(tblporfits.getPorFitId());
        typeResponse.setPorId(tblporfits.getPorId());
        typeResponse.setId(tblporfits.getId());
        typeResponse.setCreatedBy(tblporfits.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblporfits.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblporfits.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblporfits.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblporfits.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblporfitsResponse save(TblporfitsRequest request) {

        Tblporfits tblporfits = new Tblporfits();
        tblporfits.setPorFitDesc(request.getPorFitDesc());
        tblporfits.setPorFitId(request.getPorFitId());
        tblporfits.setPorId(request.getPorId());
        tblporfits.setIsDeleted(Deleted.NO);
        Tblporfits save = tblporfitsRepository.save(tblporfits);

        return convert(save);
    }

    @Override
    @Transactional
    public TblporfitsResponse update(TblporfitsUpdateRequest request) {

        Tblporfits tblporfits = tblporfitsRepository.findById(request.getId()).orElse(null);
        if (tblporfits == null) {
            return null;
        }

        tblporfits.setId(request.getId());
        tblporfits.setPorFitDesc(request.getPorFitDesc());
        tblporfits.setPorFitId(request.getPorFitId());
        tblporfits.setPorId(request.getPorId());
        Tblporfits updated = tblporfitsRepository.save(tblporfits);

        return (convert(updated));
    }

    @Override
    public TblporfitsResponse getById(Long id) {

        return tblporfitsRepository.findById(id).map(TblporfitsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblporfitsResponse> getAll() {

        return tblporfitsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblporfitsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Tblporfits got = tblporfitsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblporfitsRepository.save(got);

        return 1;
    }

    @Override
    public List<TblporfitsResponse> getByPorId(String porId) {
        return tblporfitsRepository.findByPorId(porId)
                .stream().map(TblporfitsServiceImpl::convert).collect(Collectors.toList());
    }
}
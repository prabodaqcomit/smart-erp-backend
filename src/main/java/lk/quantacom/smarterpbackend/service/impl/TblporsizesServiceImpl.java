package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblporsizesRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporsizesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporsizesResponse;
import lk.quantacom.smarterpbackend.entity.Tblporsizes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblporsizesRepository;
import lk.quantacom.smarterpbackend.service.TblporsizesService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblporsizesServiceImpl implements TblporsizesService {

    @Autowired
    private TblporsizesRepository tblporsizesRepository;

    private static TblporsizesResponse convert(Tblporsizes tblporsizes) {

        TblporsizesResponse typeResponse = new TblporsizesResponse();
        typeResponse.setPorId(tblporsizes.getPorId());
        typeResponse.setPorQty(tblporsizes.getPorQty());
        typeResponse.setPorRatio(tblporsizes.getPorRatio());
        typeResponse.setPorSizeDesc(tblporsizes.getPorSizeDesc());
        typeResponse.setPorSizeId(tblporsizes.getPorSizeId());
        typeResponse.setId(tblporsizes.getId());
        typeResponse.setCreatedBy(tblporsizes.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblporsizes.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblporsizes.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblporsizes.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblporsizes.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblporsizesResponse save(TblporsizesRequest request) {

        Tblporsizes tblporsizes = new Tblporsizes();
        tblporsizes.setPorId(request.getPorId());
        tblporsizes.setPorQty(request.getPorQty());
        tblporsizes.setPorRatio(request.getPorRatio());
        tblporsizes.setPorSizeDesc(request.getPorSizeDesc());
        tblporsizes.setPorSizeId(request.getPorSizeId());
        tblporsizes.setIsDeleted(Deleted.NO);
        Tblporsizes save = tblporsizesRepository.save(tblporsizes);

        return convert(save);
    }

    @Override
    @Transactional
    public TblporsizesResponse update(TblporsizesUpdateRequest request) {

        Tblporsizes tblporsizes = tblporsizesRepository.findById(request.getId()).orElse(null);
        if (tblporsizes == null) {
            return null;
        }

        tblporsizes.setId(request.getId());
        tblporsizes.setPorId(request.getPorId());
        tblporsizes.setPorQty(request.getPorQty());
        tblporsizes.setPorRatio(request.getPorRatio());
        tblporsizes.setPorSizeDesc(request.getPorSizeDesc());
        tblporsizes.setPorSizeId(request.getPorSizeId());
        Tblporsizes updated = tblporsizesRepository.save(tblporsizes);

        return (convert(updated));
    }

    @Override
    public TblporsizesResponse getById(Long id) {

        return tblporsizesRepository.findById(id).map(TblporsizesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblporsizesResponse> getAll() {

        return tblporsizesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblporsizesServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Tblporsizes got = tblporsizesRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblporsizesRepository.save(got);

        return 1;
    }

    @Override
    public List<TblporsizesResponse> getByPorId(String porId) {
        return tblporsizesRepository.findByPorId(porId)
                .stream().map(TblporsizesServiceImpl::convert).collect(Collectors.toList());
    }
}
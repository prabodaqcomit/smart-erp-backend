package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblbommainmaterialRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbommainmaterialUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GetBomMaterialByStockMaterialDescResponse;
import lk.quantacom.smarterpbackend.dto.response.TblbommainmaterialResponse;
import lk.quantacom.smarterpbackend.entity.Tblbommainmaterial;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblbommainmaterialRepository;
import lk.quantacom.smarterpbackend.service.TblbommainmaterialService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblbommainmaterialServiceImpl implements TblbommainmaterialService {

    @Autowired
    private TblbommainmaterialRepository tblbommainmaterialRepository;

    private static TblbommainmaterialResponse convert(Tblbommainmaterial tblbommainmaterial) {

        TblbommainmaterialResponse typeResponse = new TblbommainmaterialResponse();
        typeResponse.setBomId(tblbommainmaterial.getBomId());
        typeResponse.setBomMainMaterialBatchId(tblbommainmaterial.getBomMainMaterialBatchId());
        typeResponse.setBomMainMaterialBranchId(tblbommainmaterial.getBomMainMaterialBranchId());
        typeResponse.setBomMainMaterialColorId(tblbommainmaterial.getBomMainMaterialColorId());
        typeResponse.setBomMainMaterialDesc(tblbommainmaterial.getBomMainMaterialDesc());
        typeResponse.setBomMainMaterialId(tblbommainmaterial.getBomMainMaterialId());
        typeResponse.setId(tblbommainmaterial.getId());
        typeResponse.setCreatedBy(tblbommainmaterial.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblbommainmaterial.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblbommainmaterial.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblbommainmaterial.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblbommainmaterial.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblbommainmaterialResponse save(TblbommainmaterialRequest request) {

        Tblbommainmaterial tblbommainmaterial = new Tblbommainmaterial();
        tblbommainmaterial.setBomId(request.getBomId());
        tblbommainmaterial.setBomMainMaterialBatchId(request.getBomMainMaterialBatchId());
        tblbommainmaterial.setBomMainMaterialBranchId(request.getBomMainMaterialBranchId());
        tblbommainmaterial.setBomMainMaterialColorId(request.getBomMainMaterialColorId());
        tblbommainmaterial.setBomMainMaterialDesc(request.getBomMainMaterialDesc());
        tblbommainmaterial.setBomMainMaterialId(request.getBomMainMaterialId());
        tblbommainmaterial.setIsDeleted(Deleted.NO);
        Tblbommainmaterial save = tblbommainmaterialRepository.save(tblbommainmaterial);

        return convert(save);
    }

    @Override
    @Transactional
    public TblbommainmaterialResponse update(TblbommainmaterialUpdateRequest request) {

        Tblbommainmaterial tblbommainmaterial = tblbommainmaterialRepository.findById(request.getId()).orElse(null);
        if (tblbommainmaterial == null) {
            return null;
        }

        tblbommainmaterial.setId(request.getId());
        tblbommainmaterial.setBomId(request.getBomId());
        tblbommainmaterial.setBomMainMaterialBatchId(request.getBomMainMaterialBatchId());
        tblbommainmaterial.setBomMainMaterialBranchId(request.getBomMainMaterialBranchId());
        tblbommainmaterial.setBomMainMaterialColorId(request.getBomMainMaterialColorId());
        tblbommainmaterial.setBomMainMaterialDesc(request.getBomMainMaterialDesc());
        tblbommainmaterial.setBomMainMaterialId(request.getBomMainMaterialId());
        Tblbommainmaterial updated = tblbommainmaterialRepository.save(tblbommainmaterial);

        return (convert(updated));
    }

    @Override
    public TblbommainmaterialResponse getById(Long id) {

        return tblbommainmaterialRepository.findById(id).map(TblbommainmaterialServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblbommainmaterialResponse> getAll() {

        return tblbommainmaterialRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblbommainmaterialServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer bomId) {

        Tblbommainmaterial got = tblbommainmaterialRepository.findByBomId(bomId);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblbommainmaterialRepository.save(got);

        return 1;
    }

    @Override
    public List<GetBomMaterialByStockMaterialDescResponse> getBomMaterialsByBomId(Integer bomId) {

        List<GetBomMaterialByStockMaterialDescResponse> responses = tblbommainmaterialRepository.getBomMaterialsByBomId(bomId);
        return responses;
    }
}
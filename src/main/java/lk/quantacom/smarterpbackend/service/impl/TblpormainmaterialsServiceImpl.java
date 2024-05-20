package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblpormainmaterialsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpormainmaterialsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpormainmaterialsResponse;
import lk.quantacom.smarterpbackend.dto.response.getPorByPorIdResponse;
import lk.quantacom.smarterpbackend.entity.Tblpormainmaterials;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblpormainmaterialsRepository;
import lk.quantacom.smarterpbackend.service.TblpormainmaterialsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblpormainmaterialsServiceImpl implements TblpormainmaterialsService {

    @Autowired
    private TblpormainmaterialsRepository tblpormainmaterialsRepository;

    private static TblpormainmaterialsResponse convert(Tblpormainmaterials tblpormainmaterials) {

        TblpormainmaterialsResponse typeResponse = new TblpormainmaterialsResponse();
        typeResponse.setPorId(tblpormainmaterials.getPorId());
        typeResponse.setPorMainColorId(tblpormainmaterials.getPorMainColorId());
        typeResponse.setPorMainItemBatchId(tblpormainmaterials.getPorMainItemBatchId());
        typeResponse.setPorMainItemBranchId(tblpormainmaterials.getPorMainItemBranchId());
        typeResponse.setPorMainItemCode(tblpormainmaterials.getPorMainItemCode());
        typeResponse.setPorMainItemDesc(tblpormainmaterials.getPorMainItemDesc());
        typeResponse.setPorMainItemQty(tblpormainmaterials.getPorMainItemQty());
        typeResponse.setId(tblpormainmaterials.getId());
        typeResponse.setCreatedBy(tblpormainmaterials.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblpormainmaterials.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblpormainmaterials.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblpormainmaterials.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblpormainmaterials.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblpormainmaterialsResponse save(TblpormainmaterialsRequest request) {

        Tblpormainmaterials tblpormainmaterials = new Tblpormainmaterials();
        tblpormainmaterials.setPorId(request.getPorId());
        tblpormainmaterials.setPorMainColorId(request.getPorMainColorId());
        tblpormainmaterials.setPorMainItemBatchId(request.getPorMainItemBatchId());
        tblpormainmaterials.setPorMainItemBranchId(request.getPorMainItemBranchId());
        tblpormainmaterials.setPorMainItemCode(request.getPorMainItemCode());
        tblpormainmaterials.setPorMainItemDesc(request.getPorMainItemDesc());
        tblpormainmaterials.setPorMainItemQty(request.getPorMainItemQty());
        tblpormainmaterials.setIsDeleted(Deleted.NO);
        Tblpormainmaterials save = tblpormainmaterialsRepository.save(tblpormainmaterials);

        return convert(save);
    }

    @Override
    @Transactional
    public TblpormainmaterialsResponse update(TblpormainmaterialsUpdateRequest request) {

        Tblpormainmaterials tblpormainmaterials = tblpormainmaterialsRepository.findById(request.getId()).orElse(null);
        if (tblpormainmaterials == null) {
            return null;
        }

        tblpormainmaterials.setId(request.getId());
        tblpormainmaterials.setPorId(request.getPorId());
        tblpormainmaterials.setPorMainColorId(request.getPorMainColorId());
        tblpormainmaterials.setPorMainItemBatchId(request.getPorMainItemBatchId());
        tblpormainmaterials.setPorMainItemBranchId(request.getPorMainItemBranchId());
        tblpormainmaterials.setPorMainItemCode(request.getPorMainItemCode());
        tblpormainmaterials.setPorMainItemDesc(request.getPorMainItemDesc());
        tblpormainmaterials.setPorMainItemQty(request.getPorMainItemQty());
        Tblpormainmaterials updated = tblpormainmaterialsRepository.save(tblpormainmaterials);

        return (convert(updated));
    }

    @Override
    public TblpormainmaterialsResponse getById(Long id) {

        return tblpormainmaterialsRepository.findById(id).map(TblpormainmaterialsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblpormainmaterialsResponse> getAll() {

        return tblpormainmaterialsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblpormainmaterialsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Tblpormainmaterials got = tblpormainmaterialsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblpormainmaterialsRepository.save(got);

        return 1;
    }

    @Override
    public List<getPorByPorIdResponse> getPorByPorId(String porId) {
        List<getPorByPorIdResponse> responseList=tblpormainmaterialsRepository.getPorByPorId(porId);
        return responseList;
    }
}
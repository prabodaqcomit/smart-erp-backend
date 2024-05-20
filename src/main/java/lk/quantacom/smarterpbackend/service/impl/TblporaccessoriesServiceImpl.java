package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblporaccessoriesRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporaccessoriesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporaccessoriesResponse;
import lk.quantacom.smarterpbackend.dto.response.getByStockAndTblPorAccessoriesResponse;
import lk.quantacom.smarterpbackend.entity.Tblporaccessories;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblporaccessoriesRepository;
import lk.quantacom.smarterpbackend.service.TblporaccessoriesService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblporaccessoriesServiceImpl implements TblporaccessoriesService {

    @Autowired
    private TblporaccessoriesRepository tblporaccessoriesRepository;

    private static TblporaccessoriesResponse convert(Tblporaccessories tblporaccessories) {

        TblporaccessoriesResponse typeResponse = new TblporaccessoriesResponse();
        typeResponse.setPorAccItemBatchId(tblporaccessories.getPorAccItemBatchId());
        typeResponse.setPorAccItemBranchId(tblporaccessories.getPorAccItemBranchId());
        typeResponse.setPorAccItemCode(tblporaccessories.getPorAccItemCode());
        typeResponse.setPorAccItemConsumptionQty(tblporaccessories.getPorAccItemConsumptionQty());
        typeResponse.setPorAccItemDesc(tblporaccessories.getPorAccItemDesc());
        typeResponse.setPorAccItemQty(tblporaccessories.getPorAccItemQty());
        typeResponse.setPorId(tblporaccessories.getPorId());
        typeResponse.setId(tblporaccessories.getId());
        typeResponse.setCreatedBy(tblporaccessories.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblporaccessories.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblporaccessories.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblporaccessories.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblporaccessories.getIsDeleted());

        return typeResponse;

    }

    @Override
    public List<getByStockAndTblPorAccessoriesResponse> getByStockAndTblPorAccessory(String porId) {
        List<getByStockAndTblPorAccessoriesResponse> responses =tblporaccessoriesRepository.getByStockAndTblPorAccessory(porId);
        return responses;
    }


    @Override
    @Transactional
    public TblporaccessoriesResponse save(TblporaccessoriesRequest request) {

        Tblporaccessories tblporaccessories = new Tblporaccessories();
        tblporaccessories.setPorAccItemBatchId(request.getPorAccItemBatchId());
        tblporaccessories.setPorAccItemBranchId(request.getPorAccItemBranchId());
        tblporaccessories.setPorAccItemCode(request.getPorAccItemCode());
        tblporaccessories.setPorAccItemConsumptionQty(request.getPorAccItemConsumptionQty());
        tblporaccessories.setPorAccItemDesc(request.getPorAccItemDesc());
        tblporaccessories.setPorAccItemQty(request.getPorAccItemQty());
        tblporaccessories.setPorId(request.getPorId());
        tblporaccessories.setIsDeleted(Deleted.NO);
        Tblporaccessories save = tblporaccessoriesRepository.save(tblporaccessories);

        return convert(save);
    }

    @Override
    @Transactional
    public TblporaccessoriesResponse update(TblporaccessoriesUpdateRequest request) {

        Tblporaccessories tblporaccessories = tblporaccessoriesRepository.findById(request.getId()).orElse(null);
        if (tblporaccessories == null) {
            return null;
        }

        tblporaccessories.setId(request.getId());
        tblporaccessories.setPorAccItemBatchId(request.getPorAccItemBatchId());
        tblporaccessories.setPorAccItemBranchId(request.getPorAccItemBranchId());
        tblporaccessories.setPorAccItemCode(request.getPorAccItemCode());
        tblporaccessories.setPorAccItemConsumptionQty(request.getPorAccItemConsumptionQty());
        tblporaccessories.setPorAccItemDesc(request.getPorAccItemDesc());
        tblporaccessories.setPorAccItemQty(request.getPorAccItemQty());
        tblporaccessories.setPorId(request.getPorId());
        Tblporaccessories updated = tblporaccessoriesRepository.save(tblporaccessories);

        return (convert(updated));
    }

    @Override
    public TblporaccessoriesResponse getById(Long id) {

        return tblporaccessoriesRepository.findById(id).map(TblporaccessoriesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblporaccessoriesResponse> getAll() {

        return tblporaccessoriesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblporaccessoriesServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Tblporaccessories got = tblporaccessoriesRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblporaccessoriesRepository.save(got);

        return 1;
    }

}
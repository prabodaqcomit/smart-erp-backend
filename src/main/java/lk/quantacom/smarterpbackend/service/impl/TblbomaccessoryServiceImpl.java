package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblbomaccessoryRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomaccessoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomaccessoryResponse;
import lk.quantacom.smarterpbackend.entity.Tblbomaccessory;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblbomaccessoryRepository;
import lk.quantacom.smarterpbackend.service.TblbomaccessoryService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblbomaccessoryServiceImpl implements TblbomaccessoryService {

    @Autowired
    private TblbomaccessoryRepository tblbomaccessoryRepository;

    private static TblbomaccessoryResponse convert(Tblbomaccessory tblbomaccessory) {

        TblbomaccessoryResponse typeResponse = new TblbomaccessoryResponse();
        typeResponse.setBdAccessoryBatchId(tblbomaccessory.getBdAccessoryBatchId());
        typeResponse.setBdAccessoryBranchId(tblbomaccessory.getBdAccessoryBranchId());
        typeResponse.setBdAccessoryId(tblbomaccessory.getBdAccessoryId());
        typeResponse.setBdDesc(tblbomaccessory.getBdDesc());
        typeResponse.setBdId(tblbomaccessory.getBdId());
        typeResponse.setBdQty(tblbomaccessory.getBdQty());
        typeResponse.setId(tblbomaccessory.getId());
        typeResponse.setCreatedBy(tblbomaccessory.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblbomaccessory.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblbomaccessory.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblbomaccessory.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblbomaccessory.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblbomaccessoryResponse save(TblbomaccessoryRequest request) {

        Tblbomaccessory tblbomaccessory = new Tblbomaccessory();
        tblbomaccessory.setBdAccessoryBatchId(request.getBdAccessoryBatchId());
        tblbomaccessory.setBdAccessoryBranchId(request.getBdAccessoryBranchId());
        tblbomaccessory.setBdAccessoryId(request.getBdAccessoryId());
        tblbomaccessory.setBdDesc(request.getBdDesc());
        tblbomaccessory.setBdId(request.getBdId());
        tblbomaccessory.setBdQty(request.getBdQty());
        tblbomaccessory.setIsDeleted(Deleted.NO);
        Tblbomaccessory save = tblbomaccessoryRepository.save(tblbomaccessory);

        return convert(save);
    }

    @Override
    @Transactional
    public TblbomaccessoryResponse update(TblbomaccessoryUpdateRequest request) {

        Tblbomaccessory tblbomaccessory = tblbomaccessoryRepository.findById(request.getId()).orElse(null);
        if (tblbomaccessory == null) {
            return null;
        }

        tblbomaccessory.setId(request.getId());
        tblbomaccessory.setBdAccessoryBatchId(request.getBdAccessoryBatchId());
        tblbomaccessory.setBdAccessoryBranchId(request.getBdAccessoryBranchId());
        tblbomaccessory.setBdAccessoryId(request.getBdAccessoryId());
        tblbomaccessory.setBdDesc(request.getBdDesc());
        tblbomaccessory.setBdId(request.getBdId());
        tblbomaccessory.setBdQty(request.getBdQty());
        Tblbomaccessory updated = tblbomaccessoryRepository.save(tblbomaccessory);

        return (convert(updated));
    }

    @Override
    public TblbomaccessoryResponse getById(Long id) {

        return tblbomaccessoryRepository.findById(id).map(TblbomaccessoryServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblbomaccessoryResponse> getAll() {

        return tblbomaccessoryRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblbomaccessoryServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer bdId) {

//        List<Tblbomaccessory> got = tblbomaccessoryRepository.findByBdId(bdId) ;
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        tblbomaccessoryRepository.save(got);

        return 1;
    }
}
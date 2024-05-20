package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.DiningTableRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableResponse;
import lk.quantacom.smarterpbackend.entity.DiningTable;
import lk.quantacom.smarterpbackend.entity.DiningTableTmpHead;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.DiningTableRepository;
import lk.quantacom.smarterpbackend.repository.DiningTableTmpHeadRepository;
import lk.quantacom.smarterpbackend.repository.ItemMasterRepository;
import lk.quantacom.smarterpbackend.service.DiningTableService;

import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiningTableServiceImpl implements DiningTableService {

    @Autowired
    private DiningTableRepository diningTableRepository;

    @Autowired
    private DiningTableTmpHeadRepository diningTableTmpHeadRepository;

    @Override
    @Transactional
    public DiningTableResponse save(DiningTableRequest request) {

        DiningTable diningTable = new DiningTable();
        diningTable.setDnTableName(request.getDnTableName());
        diningTable.setIsActive(request.getIsActive());
        diningTable.setIsDeleted(Deleted.NO);
        DiningTable save = diningTableRepository.save(diningTable);

        return convert(save);
    }

    @Override
    @Transactional
    public DiningTableResponse update(DiningTableUpdateRequest request) {

        DiningTable diningTable = diningTableRepository.findById(request.getId()).orElse(null);
        if (diningTable == null) {
            return null;
        }

        diningTable.setId(request.getId());
        diningTable.setDnTableName(request.getDnTableName());
        diningTable.setIsActive(request.getIsActive());
        DiningTable updated = diningTableRepository.save(diningTable);

        return (convert(updated));
    }

    @Override
    public DiningTableResponse getById(Long id) {

        return diningTableRepository.findById(id).map(DiningTableServiceImpl::convert).orElse(null);
    }

    @Override
    public List<DiningTableResponse> getAll() {

        List<DiningTableResponse> res=new ArrayList<>();

        List<DiningTable> tables=diningTableRepository.findByIsDeleted(Deleted.NO);
        for(DiningTable tb:tables){
            res.add(convert2(tb));
        }

        return res;

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        DiningTable got = diningTableRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        diningTableRepository.save(got);

        return 1;
    }

    private static DiningTableResponse convert(DiningTable diningTable) {

        DiningTableResponse typeResponse = new DiningTableResponse();
        typeResponse.setDnTableName(diningTable.getDnTableName());
        typeResponse.setIsActive(diningTable.getIsActive());
        typeResponse.setId(diningTable.getId());
        typeResponse.setCreatedBy(diningTable.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(diningTable.getCreatedDateTime()));
        typeResponse.setModifiedBy(diningTable.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(diningTable.getModifiedDateTime()));
        typeResponse.setIsDeleted(diningTable.getIsDeleted());

        return typeResponse;
    }

    private DiningTableResponse convert2(DiningTable diningTable) {

        DiningTableResponse typeResponse = new DiningTableResponse();
        typeResponse.setDnTableName(diningTable.getDnTableName());
        typeResponse.setIsActive(diningTable.getIsActive());
        typeResponse.setId(diningTable.getId());
        typeResponse.setCreatedBy(diningTable.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(diningTable.getCreatedDateTime()));
        typeResponse.setModifiedBy(diningTable.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(diningTable.getModifiedDateTime()));
        typeResponse.setIsDeleted(diningTable.getIsDeleted());

        DiningTableTmpHead tmpHead= diningTableTmpHeadRepository.findByTableIdAndInvStatusAndIsDeleted(diningTable.getId(),0,Deleted.NO);
        if(tmpHead!=null){
            typeResponse.setHasOrder(true);
        }else{
            typeResponse.setHasOrder(false);
        }

        return typeResponse;
    }
}
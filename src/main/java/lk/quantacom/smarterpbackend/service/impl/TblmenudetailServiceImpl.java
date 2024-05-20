package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblmenudetailRequest;
import lk.quantacom.smarterpbackend.dto.request.TblmenudetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblmenudetailResponse;
import lk.quantacom.smarterpbackend.entity.Tblmenudetail;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblmenudetailRepository;
import lk.quantacom.smarterpbackend.service.TblmenudetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblmenudetailServiceImpl implements TblmenudetailService {

    @Autowired
    private TblmenudetailRepository tblmenudetailRepository;

    private static TblmenudetailResponse convert(Tblmenudetail tblmenudetail) {

        TblmenudetailResponse typeResponse = new TblmenudetailResponse();
        typeResponse.setMenuId(tblmenudetail.getMenuId());
        typeResponse.setMenuDesc(tblmenudetail.getMenuDesc());
        typeResponse.setMenuRight(tblmenudetail.getMenuRight());
        typeResponse.setCreatedBy(tblmenudetail.getCreatedBy());
        typeResponse.setCreatedDateTime(tblmenudetail.getCreatedDateTime());
        typeResponse.setModifiedBy(tblmenudetail.getModifiedBy());
        typeResponse.setModifiedDateTime(tblmenudetail.getModifiedDateTime());
        typeResponse.setMenuId(tblmenudetail.getMenuId());
        typeResponse.setCreatedBy(tblmenudetail.getCreatedBy());
        typeResponse.setModifiedBy(tblmenudetail.getModifiedBy());
        typeResponse.setIsDeleted(tblmenudetail.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblmenudetailResponse save(TblmenudetailRequest request) {

        Tblmenudetail tblmenudetail = new Tblmenudetail();
        tblmenudetail.setMenuId(request.getMenuId());
        tblmenudetail.setMenuDesc(request.getMenuDesc());
        tblmenudetail.setMenuRight(request.getMenuRight());
        tblmenudetail.setCreatedBy(request.getCreatedBy());
        tblmenudetail.setCreatedDateTime(new Date());
        tblmenudetail.setIsDeleted(request.getIsDeleted());
        tblmenudetail.setModifiedBy(request.getCreatedBy());
        tblmenudetail.setModifiedDateTime(new Date());
        tblmenudetail.setIsDeleted(Deleted.NO);
        Tblmenudetail save = tblmenudetailRepository.save(tblmenudetail);

        return convert(save);
    }

    @Override
    @Transactional
    public TblmenudetailResponse update(TblmenudetailUpdateRequest request) {

        Tblmenudetail tblmenudetail = tblmenudetailRepository.findById(request.getMenuId()).orElse(null);
        if (tblmenudetail == null) {
            return null;
        }

        tblmenudetail.setMenuId(request.getMenuId());
        tblmenudetail.setMenuId(request.getMenuId());
        tblmenudetail.setMenuDesc(request.getMenuDesc());
        tblmenudetail.setMenuRight(request.getMenuRight());
        tblmenudetail.setCreatedDateTime(new Date());
        tblmenudetail.setIsDeleted(request.getIsDeleted());
        tblmenudetail.setModifiedDateTime(new Date());
        Tblmenudetail updated = tblmenudetailRepository.save(tblmenudetail);

        return (convert(updated));
    }

    @Override
    public TblmenudetailResponse getById(String id) {

        return tblmenudetailRepository.findById(id).map(TblmenudetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblmenudetailResponse> getAll() {

        return tblmenudetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblmenudetailServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(String id) {

        Tblmenudetail got = tblmenudetailRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblmenudetailRepository.save(got);

        return 1;
    }
}
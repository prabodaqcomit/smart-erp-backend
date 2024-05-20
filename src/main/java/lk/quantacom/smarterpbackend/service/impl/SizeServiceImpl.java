package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.SizeRequest;
import lk.quantacom.smarterpbackend.dto.request.SizeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SizeResponse;
import lk.quantacom.smarterpbackend.entity.Size;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.SizeRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.SizeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    private void saveLog(String form,String action){
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional
    public SizeResponse save(SizeRequest request) {

        Size size = new Size();
        size.setSizeDesc(request.getSizeDesc());
        size.setIsDeleted(Deleted.NO);
        Size save = sizeRepository.save(size);

        saveLog("Size","Data Saved - "+save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public SizeResponse update(SizeUpdateRequest request) {

        Size size = sizeRepository.findById(request.getId()).orElse(null);
        if (size == null) {
            return null;
        }

        size.setId(request.getId());
        size.setSizeDesc(request.getSizeDesc());
        Size updated = sizeRepository.save(size);

        saveLog("Size","Data Updated - "+updated.getId());

        return (convert(updated));
    }

    @Override
    public SizeResponse getById(Long id) {

        return sizeRepository.findById(id).map(SizeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<SizeResponse> getAll() {

        return sizeRepository.findAll()
                .stream().map(SizeServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Size got = sizeRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        sizeRepository.save(got);

        saveLog("Size","Data Deleted - "+id);

        return 1;
    }

    private static SizeResponse convert(Size size) {

        SizeResponse typeResponse = new SizeResponse();
        typeResponse.setSizeDesc(size.getSizeDesc());

        typeResponse.setId(size.getId());
        typeResponse.setCreatedBy(size.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(size.getCreatedDateTime()));
        typeResponse.setModifiedBy(size.getModifiedBy());
        typeResponse.setIsDeleted(size.getIsDeleted());

        return typeResponse;
    }
}
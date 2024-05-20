package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ColorRequest;
import lk.quantacom.smarterpbackend.dto.request.ColorUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ColorResponse;
import lk.quantacom.smarterpbackend.entity.Color;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.ColorRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.ColorService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    private void saveLog(String form,String action){
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        UserLogger save = userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional
    public ColorResponse save(ColorRequest request) {

        Color color = new Color();
        color.setColorDesc(request.getColorDesc());
        color.setIsDeleted(Deleted.NO);
        Color save = colorRepository.save(color);

        saveLog("Color","Data Saved - "+save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public ColorResponse update(ColorUpdateRequest request) {

        Color color = colorRepository.findById(request.getId()).orElse(null);
        if (color == null) {
            return null;
        }

        color.setId(request.getId());
        color.setColorDesc(request.getColorDesc());
        Color updated = colorRepository.save(color);

        saveLog("Color","Data Updated - "+updated.getId());

        return (convert(updated));
    }

    @Override
    public ColorResponse getById(Long id) {

        return colorRepository.findById(id).map(ColorServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ColorResponse> getAll() {

        return colorRepository.findAll()
                .stream().map(ColorServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Color got = colorRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        colorRepository.save(got);

        saveLog("Color","Data Deleted - "+id);

        return 1;
    }

    private static ColorResponse convert(Color color) {

        ColorResponse typeResponse = new ColorResponse();
        typeResponse.setColorDesc(color.getColorDesc());

        typeResponse.setId(color.getId());
        typeResponse.setCreatedBy(color.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(color.getCreatedDateTime()));
        typeResponse.setModifiedBy(color.getModifiedBy());
        typeResponse.setIsDeleted(color.getIsDeleted());

        return typeResponse;
    }
}
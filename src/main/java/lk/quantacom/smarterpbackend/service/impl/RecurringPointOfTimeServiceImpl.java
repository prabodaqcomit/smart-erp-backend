package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.RecurringPointOfTimeRequest;
import lk.quantacom.smarterpbackend.dto.request.RecurringPointOfTimeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.RecurringPointOfTimeResponse;
import lk.quantacom.smarterpbackend.entity.RecurringPointOfTime;
import lk.quantacom.smarterpbackend.repository.RecurringPointOfTimeRepository;
import lk.quantacom.smarterpbackend.service.RecurringPointOfTimeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecurringPointOfTimeServiceImpl implements RecurringPointOfTimeService {

    @Autowired
    private RecurringPointOfTimeRepository recurringPointOfTimeRepository;


    @Override
    @Transactional
    public RecurringPointOfTimeResponse save(RecurringPointOfTimeRequest request) {

        RecurringPointOfTime recurringPointOfTime = new RecurringPointOfTime();
        recurringPointOfTime.setDescription(request.getDescription());
        recurringPointOfTime.setPointOfTimeStart(request.getPointOfTimeStart());
        recurringPointOfTime.setPointOfTimeEnd(request.getPointOfTimeEnd());
        recurringPointOfTime.setIsVariableEndPointOfTime(request.getIsVariableEndPointOfTime());
        recurringPointOfTime.setIsDeleted(Deleted.NO);
        RecurringPointOfTime save = recurringPointOfTimeRepository.save(recurringPointOfTime);

        return convert(save);
    }

    @Override
    @Transactional
    public RecurringPointOfTimeResponse update(RecurringPointOfTimeUpdateRequest request) {

        RecurringPointOfTime recurringPointOfTime = recurringPointOfTimeRepository.findById(request.getId()).orElse(null);
        if (recurringPointOfTime == null) {
            return null;
        }

        recurringPointOfTime.setId(request.getId());
        recurringPointOfTime.setDescription(request.getDescription());
        recurringPointOfTime.setPointOfTimeStart(request.getPointOfTimeStart());
        recurringPointOfTime.setPointOfTimeEnd(request.getPointOfTimeEnd());
        recurringPointOfTime.setIsVariableEndPointOfTime(request.getIsVariableEndPointOfTime());
        RecurringPointOfTime updated = recurringPointOfTimeRepository.save(recurringPointOfTime);

        return (convert(updated));
    }

    @Override
    public RecurringPointOfTimeResponse getById(Long id) {

        return recurringPointOfTimeRepository.findById(id).map(RecurringPointOfTimeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<RecurringPointOfTimeResponse> getAll() {

        return recurringPointOfTimeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(RecurringPointOfTimeServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        RecurringPointOfTime got = recurringPointOfTimeRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        recurringPointOfTimeRepository.save(got);

        return 1;
    }

    private static RecurringPointOfTimeResponse convert(RecurringPointOfTime recurringPointOfTime) {

        RecurringPointOfTimeResponse typeResponse = new RecurringPointOfTimeResponse();
        typeResponse.setDescription(recurringPointOfTime.getDescription());
        typeResponse.setPointOfTimeStart(recurringPointOfTime.getPointOfTimeStart());
        typeResponse.setPointOfTimeEnd(recurringPointOfTime.getPointOfTimeEnd());
        typeResponse.setIsVariableEndPointOfTime(recurringPointOfTime.getIsVariableEndPointOfTime());
        typeResponse.setId(recurringPointOfTime.getId());
        typeResponse.setCreatedBy(recurringPointOfTime.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(recurringPointOfTime.getCreatedDateTime()));
        typeResponse.setModifiedBy(recurringPointOfTime.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(recurringPointOfTime.getModifiedDateTime()));
        typeResponse.setIsDeleted(recurringPointOfTime.getIsDeleted());

        return typeResponse;
    }
}
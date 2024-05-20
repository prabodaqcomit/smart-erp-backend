package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ActionTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionTypeResponse;
import lk.quantacom.smarterpbackend.entity.ActionType;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ActionTypeRepository;
import lk.quantacom.smarterpbackend.service.ActionTypeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionTypeServiceImpl implements ActionTypeService {

    @Autowired
    private ActionTypeRepository actionTypeRepository;

    private static ActionTypeResponse convert(ActionType actionType) {

        ActionTypeResponse typeResponse = new ActionTypeResponse();
        typeResponse.setDescription(actionType.getDescription());
        typeResponse.setActionLogic(actionType.getActionLogic());
        typeResponse.setId(actionType.getId());
        typeResponse.setCreatedBy(actionType.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(actionType.getCreatedDateTime()));
        typeResponse.setModifiedBy(actionType.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(actionType.getModifiedDateTime()));
        typeResponse.setIsDeleted(actionType.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public ActionTypeResponse save(ActionTypeRequest request) {

        ActionType actionType = new ActionType();
        actionType.setDescription(request.getDescription());
        actionType.setActionLogic(request.getActionLogic());
        actionType.setIsDeleted(Deleted.NO);
        ActionType save = actionTypeRepository.save(actionType);

        return convert(save);
    }

    @Override
    @Transactional
    public ActionTypeResponse update(ActionTypeUpdateRequest request) {

        ActionType actionType = actionTypeRepository.findById(request.getId()).orElse(null);
        if (actionType == null) {
            return null;
        }

        actionType.setDescription(request.getDescription());
        actionType.setActionLogic(request.getActionLogic());
        ActionType updated = actionTypeRepository.save(actionType);

        return (convert(updated));
    }

    @Override
    public ActionTypeResponse getById(Long id) {

        return actionTypeRepository.findById(id).map(ActionTypeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ActionTypeResponse> getAll() {

        return actionTypeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ActionTypeServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ActionType got = actionTypeRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        actionTypeRepository.save(got);

        return 1;
    }
}
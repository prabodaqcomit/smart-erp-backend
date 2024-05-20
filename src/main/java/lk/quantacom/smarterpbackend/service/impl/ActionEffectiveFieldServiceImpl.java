package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ActionEffectiveFieldRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionEffectiveFieldUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionEffectiveFieldResponse;
import lk.quantacom.smarterpbackend.entity.ActionEffectiveField;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ActionEffectiveFieldRepository;
import lk.quantacom.smarterpbackend.service.ActionEffectiveFieldService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionEffectiveFieldServiceImpl implements ActionEffectiveFieldService {

    @Autowired
    private ActionEffectiveFieldRepository actionEffectiveFieldRepository;

    private static ActionEffectiveFieldResponse convert(ActionEffectiveField actionEffectiveField) {

        ActionEffectiveFieldResponse typeResponse = new ActionEffectiveFieldResponse();
        typeResponse.setId(actionEffectiveField.getId());
        typeResponse.setActionFieldId(actionEffectiveField.getActionFieldId());
        typeResponse.setEffectiveFieldAlias(actionEffectiveField.getEffectiveFieldAlias());
        typeResponse.setCreatedBy(actionEffectiveField.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(actionEffectiveField.getCreatedDateTime()));
        typeResponse.setModifiedBy(actionEffectiveField.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(actionEffectiveField.getModifiedDateTime()));
        typeResponse.setIsDeleted(actionEffectiveField.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public ActionEffectiveFieldResponse save(ActionEffectiveFieldRequest request) {

        ActionEffectiveField actionEffectiveField = new ActionEffectiveField();
        actionEffectiveField.setActionFieldId(request.getActionFieldId());
        actionEffectiveField.setEffectiveFieldAlias(request.getEffectiveFieldAlias());
        actionEffectiveField.setIsDeleted(Deleted.NO);
        ActionEffectiveField save = actionEffectiveFieldRepository.save(actionEffectiveField);

        return convert(save);
    }

    @Override
    @Transactional
    public ActionEffectiveFieldResponse update(ActionEffectiveFieldUpdateRequest request) {

        ActionEffectiveField actionEffectiveField = actionEffectiveFieldRepository.findById(request.getId()).orElse(null);
        if (actionEffectiveField == null) {
            return null;
        }

        actionEffectiveField.setActionFieldId(request.getActionFieldId());
        actionEffectiveField.setEffectiveFieldAlias(request.getEffectiveFieldAlias());
        ActionEffectiveField updated = actionEffectiveFieldRepository.save(actionEffectiveField);

        return (convert(updated));
    }

    @Override
    public ActionEffectiveFieldResponse getById(Long id) {

        return actionEffectiveFieldRepository.findById(id).map(ActionEffectiveFieldServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ActionEffectiveFieldResponse> getAll() {

        return actionEffectiveFieldRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ActionEffectiveFieldServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ActionEffectiveField got = actionEffectiveFieldRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        actionEffectiveFieldRepository.save(got);

        return 1;
    }
}
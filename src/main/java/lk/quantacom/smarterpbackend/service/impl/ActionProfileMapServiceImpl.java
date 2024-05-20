package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ActionProfileMapRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionProfileMapUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionProfileMapResponse;
import lk.quantacom.smarterpbackend.entity.ActionProfileMap;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ActionProfileMapRepository;
import lk.quantacom.smarterpbackend.service.ActionProfileMapService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionProfileMapServiceImpl implements ActionProfileMapService {

    @Autowired
    private ActionProfileMapRepository actionProfileMapRepository;

    private static ActionProfileMapResponse convert(ActionProfileMap actionProfileMap) {

        ActionProfileMapResponse typeResponse = new ActionProfileMapResponse();
        typeResponse.setActionFieldId(actionProfileMap.getActionFieldId());
        typeResponse.setProfileId(actionProfileMap.getProfileId());
        typeResponse.setId(actionProfileMap.getId());
        typeResponse.setCreatedBy(actionProfileMap.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(actionProfileMap.getCreatedDateTime()));
        typeResponse.setModifiedBy(actionProfileMap.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(actionProfileMap.getModifiedDateTime()));
        typeResponse.setIsDeleted(actionProfileMap.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public ActionProfileMapResponse save(ActionProfileMapRequest request) {

        ActionProfileMap actionProfileMap = new ActionProfileMap();
        actionProfileMap.setActionFieldId(request.getActionFieldId());
        actionProfileMap.setProfileId(request.getProfileId());
        actionProfileMap.setIsDeleted(Deleted.NO);
        ActionProfileMap save = actionProfileMapRepository.save(actionProfileMap);

        return convert(save);
    }

    @Override
    @Transactional
    public ActionProfileMapResponse update(ActionProfileMapUpdateRequest request) {

        ActionProfileMap actionProfileMap = actionProfileMapRepository.findById(request.getId()).orElse(null);
        if (actionProfileMap == null) {
            return null;
        }

        actionProfileMap.setId(request.getId());
        actionProfileMap.setActionFieldId(request.getActionFieldId());
        actionProfileMap.setProfileId(request.getProfileId());
        ActionProfileMap updated = actionProfileMapRepository.save(actionProfileMap);

        return (convert(updated));
    }

    @Override
    public ActionProfileMapResponse getById(Long id) {

        return actionProfileMapRepository.findById(id).map(ActionProfileMapServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ActionProfileMapResponse> getAll() {

        return actionProfileMapRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ActionProfileMapServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ActionProfileMap got = actionProfileMapRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        actionProfileMapRepository.save(got);

        return 1;
    }
}
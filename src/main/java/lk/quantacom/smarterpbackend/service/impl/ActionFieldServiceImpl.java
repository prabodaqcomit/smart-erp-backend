package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.ActionEffectiveFieldResponse;
import lk.quantacom.smarterpbackend.dto.response.ActionFieldResponse;
import lk.quantacom.smarterpbackend.dto.response.ProfileResponse;
import lk.quantacom.smarterpbackend.dto.response.getActionFieldByReferenceAliasAndProfileIdResponse;
import lk.quantacom.smarterpbackend.entity.ActionEffectiveField;
import lk.quantacom.smarterpbackend.entity.ActionField;
import lk.quantacom.smarterpbackend.entity.ActionProfileMap;
import lk.quantacom.smarterpbackend.entity.Profile;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.ActionFieldService;
import lk.quantacom.smarterpbackend.service.Bodmas;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionFieldServiceImpl implements ActionFieldService {

    @Autowired
    private ActionFieldRepository actionFieldRepository;

    @Autowired
    private ActionEffectiveFieldRepository actionEffectiveFieldRepository;

//    @Autowired
//    private StaffRepository staffRepository;

    @Autowired
    private ProfileFieldMappingRepository profileFieldMappingRepository;

    @Autowired
    private ActionProfileMapRepository actionProfileMapRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    @Transactional
    public ActionFieldResponse save(ActionFieldRequest request) {

        ActionField actionField = new ActionField();
        actionField.setActionAlias(request.getActionAlias());
        actionField.setActionDescription(request.getActionDescription());
        actionField.setReadOnly(request.getReadOnly());
        actionField.setIsInputUpperCase(request.getIsInputUpperCase());
        actionField.setIsHidden(request.getIsHidden());
        actionField.setReferenceAlias(request.getReferenceAlias());
        actionField.setFieldEvent(request.getFieldEvent());
        actionField.setFormula(request.getFormula());
        actionField.setActionTypeId(request.getActionTypeId());
        actionField.setIsNumeric(request.getIsNumeric());
        actionField.setIsMandatory(request.getIsMandatory());
        actionField.setIsDeleted(Deleted.NO);
        ActionField save = actionFieldRepository.save(actionField);

        return convert(save);
    }

    @Override
    @Transactional
    public ActionFieldResponse update(ActionFieldAndEffectiveUpdateRequest request) {

        ActionField actionField = actionFieldRepository.findById(request.getId()).orElse(null);
        if (actionField == null) {
            return null;
        }

        actionField.setActionAlias(request.getActionAlias());
        actionField.setActionDescription(request.getActionDescription());
        actionField.setReadOnly(request.getReadOnly());
        actionField.setIsInputUpperCase(request.getIsInputUpperCase());
        actionField.setIsHidden(request.getIsHidden());
        actionField.setReferenceAlias(request.getReferenceAlias());
        actionField.setFieldEvent(request.getFieldEvent());
        actionField.setFormula(request.getFormula());
        actionField.setActionTypeId(request.getActionTypeId());
        actionField.setIsMandatory(request.getIsMandatory());
        actionField.setIsDeleted(Deleted.NO);
        actionField.setIsNumeric(request.getIsNumeric());
        ActionField save = actionFieldRepository.save(actionField);

        actionProfileMapRepository.deleteByAction(actionField.getId());
        if (request.getProfileIds() != null) {
            for (Long profileId : request.getProfileIds()) {
                ActionProfileMap actionProfileMap = new ActionProfileMap();
                actionProfileMap.setActionFieldId(save.getId());
                actionProfileMap.setProfileId(profileId);
                actionProfileMap.setIsDeleted(Deleted.NO);
                actionProfileMapRepository.save(actionProfileMap);
            }
        }

        actionEffectiveFieldRepository.deleteByAction(actionField.getId());
        if (request.getActionEffectiveFieldRequests() != null) {
            for (ActionEffectiveFieldRequest actionEffectiveFieldRequest : request.getActionEffectiveFieldRequests()) {
                ActionEffectiveField actionEffectiveField = new ActionEffectiveField();
                actionEffectiveField.setActionFieldId(save.getId());
                actionEffectiveField.setEffectiveFieldAlias(actionEffectiveFieldRequest.getEffectiveFieldAlias());
                actionEffectiveField.setIsDeleted(Deleted.NO);
                actionEffectiveFieldRepository.save(actionEffectiveField);
            }
        }

        return (convert(save));
    }

    @Override
    public ActionFieldResponse getById(Long id) {

        ActionField resp = actionFieldRepository.findById(id).orElse(null);
        if (resp != null) {
            return convertOne(resp);
        }
        return null;
    }

    @Override
    public ActionFieldResponse getByAlias(String alias,String refAlias) {
        ActionField actionField = actionFieldRepository.findByActionAliasAndReferenceAliasAndIsDeleted(alias,refAlias, Deleted.NO);
        if (actionField != null) {
            return convertOne(actionField);
        }

        return null;
    }

    @Override
    public List<ActionFieldResponse> getAll() {

        List<ActionFieldResponse> responses = new ArrayList<>();

        List<ActionField> actionFields = actionFieldRepository.findByIsDeleted(Deleted.NO);

        for (ActionField actionField : actionFields) {
            responses.add(convertOne(actionField));
        }

        return responses;
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ActionField got = actionFieldRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        actionFieldRepository.save(got);

        return 1;
    }

    @Override
    @Transactional
    public ActionFieldResponse saveFieldEffective(ActionFieldAndEffectiveSaveRequest request) {

        ActionField actionField = new ActionField();
        actionField.setActionAlias(request.getActionAlias());
        actionField.setActionDescription(request.getActionDescription());
        actionField.setReadOnly(request.getReadOnly());
        actionField.setIsInputUpperCase(request.getIsInputUpperCase());
        actionField.setIsHidden(request.getIsHidden());
        actionField.setReferenceAlias(request.getReferenceAlias());
        actionField.setFieldEvent(request.getFieldEvent());
        actionField.setFormula(request.getFormula());
        actionField.setActionTypeId(request.getActionTypeId());
        actionField.setIsNumeric(request.getIsNumeric());
        actionField.setIsMandatory(request.getIsMandatory());
        actionField.setIsDeleted(Deleted.NO);
        ActionField save = actionFieldRepository.save(actionField);

        if (request.getProfileIds() != null) {
            for (Long profileId : request.getProfileIds()) {
                ActionProfileMap actionProfileMap = new ActionProfileMap();
                actionProfileMap.setActionFieldId(save.getId());
                actionProfileMap.setProfileId(profileId);
                actionProfileMap.setIsDeleted(Deleted.NO);
                actionProfileMapRepository.save(actionProfileMap);
            }
        }


        if (request.getActionEffectiveFieldRequests() != null) {
            for (ActionEffectiveFieldRequest actionEffectiveFieldRequest : request.getActionEffectiveFieldRequests()) {
                ActionEffectiveField actionEffectiveField = new ActionEffectiveField();
                actionEffectiveField.setActionFieldId(save.getId());
                actionEffectiveField.setEffectiveFieldAlias(actionEffectiveFieldRequest.getEffectiveFieldAlias());
                actionEffectiveField.setIsDeleted(Deleted.NO);
                actionEffectiveFieldRepository.save(actionEffectiveField);
            }
        }

        return convert(save);
    }

//    @Override
//    public List<ActionFieldResponse> getAllByStaffNo(Integer staffno) {
//        List<ActionFieldResponse> responseList = new ArrayList<>();
//        Staff staff = staffRepository.getById(staffno);
//        List<ProfileFieldMapping> list =
//                profileFieldMappingRepository.findByProfileAndIsDeleted(staff.getUserprofileid(), Deleted.NO);
//        for (ProfileFieldMapping profileFieldMapping : list) {
//            ActionFieldResponse actionField =
//                    actionFieldRepository.findById(profileFieldMapping.getActionField().getId())
//                            .map(ActionFieldServiceImpl::convert).orElse(null);
//            if(actionField.getIsDeleted()==Deleted.NO){
//                responseList.add(actionField);
//            }
//
//        }
//        return responseList;
//    }

    @Override
    public float evaluateNumericFormula(NumericFormulaRequest numericFormulaRequest) {
        float calAmount = 0.0f;
        String calFormula = "0";


        if (numericFormulaRequest.getFormula() == null || numericFormulaRequest.getFormula() == "") {
            calFormula = "0";
        } else {
            calFormula = numericFormulaRequest.getFormula();
        }

        for (ReplacementRequest replacementRequest : numericFormulaRequest.getReplacementRequests()) {
            calFormula = calFormula.replaceAll(replacementRequest.getAliasKey(), (replacementRequest.getAliasValue() + ""));
        }

        System.out.println(calFormula);

        calAmount = Float.parseFloat(Bodmas.findValueInBraces(calFormula.replaceAll("[A-Za-z]", "0")));

        return calAmount;
    }

    @Override
    public List<ActionFieldResponse> getAllByReferenceAliasAndProfileId(Long profileId, String referenceAlias) {

        List<ActionFieldResponse> responseList = new ArrayList<>();

        List<getActionFieldByReferenceAliasAndProfileIdResponse> list = actionProfileMapRepository.getByActionFieldIdAndProfileId(profileId, referenceAlias);

        for (getActionFieldByReferenceAliasAndProfileIdResponse response : list) {
            ActionFieldResponse actionFieldResponse = actionFieldRepository.findById(response.getID()).map(ActionFieldServiceImpl::convert).orElse(null);
            if(actionFieldResponse.getIsDeleted()==Deleted.NO){
                responseList.add(actionFieldResponse);
            }
        }

        return responseList;
    }

    @Override
    public List<ActionFieldResponse> getAllByReferenceAliasAndNumeric(String referenceAlias) {
        List<ActionFieldResponse> responses = new ArrayList<>();

        List<ActionField> actionFields = actionFieldRepository
                .findByIsNumericAndReferenceAliasAndIsDeleted(true,referenceAlias,Deleted.NO);

        for (ActionField actionField : actionFields) {
            responses.add(convertOne(actionField));
        }

        return responses;
    }

    private ActionFieldResponse convertOne(ActionField actionField) {

        ActionFieldResponse typeResponse = new ActionFieldResponse();
        typeResponse.setActionAlias(actionField.getActionAlias());
        typeResponse.setActionDescription(actionField.getActionDescription());
        typeResponse.setReadOnly(actionField.getReadOnly());
        typeResponse.setIsInputUpperCase(actionField.getIsInputUpperCase());
        typeResponse.setIsHidden(actionField.getIsHidden());
        typeResponse.setReferenceAlias(actionField.getReferenceAlias());
        typeResponse.setId(actionField.getId());
        typeResponse.setCreatedBy(actionField.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(actionField.getCreatedDateTime()));
        typeResponse.setModifiedBy(actionField.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(actionField.getModifiedDateTime()));
        typeResponse.setIsDeleted(actionField.getIsDeleted());
        typeResponse.setActionTypeId(actionField.getActionTypeId());
        typeResponse.setFormula(actionField.getFormula());
        typeResponse.setFieldEvent(actionField.getFieldEvent());
        typeResponse.setIsNumeric(actionField.getIsNumeric());
        typeResponse.setIsMandatory(actionField.getIsMandatory());

        List<ProfileResponse> profileResponses = new ArrayList<>();
        List<ActionProfileMap> profileMaps = actionProfileMapRepository.findByActionFieldIdAndIsDeleted(actionField.getId(), Deleted.NO);
        if (profileMaps != null) {
            for (ActionProfileMap map : profileMaps) {
                Profile profile = profileRepository.findById(map.getProfileId().intValue()).orElse(null);
                if (profile != null) {
                    profileResponses.add(convert(profile));
                }
            }
        }
        typeResponse.setProfiles(profileResponses);
        if (actionField.getActionEffectiveFields() != null) {
            List<ActionEffectiveFieldResponse> stockTransResponses = new ArrayList<>();
            for (ActionEffectiveField actionEffectiveField : actionField.getActionEffectiveFields()) {
                if (actionEffectiveField.getIsDeleted() == Deleted.NO) {
                    stockTransResponses.add(convertTwo(actionEffectiveField));
                }
            }
            typeResponse.setActionEffectiveFieldResponses(stockTransResponses);
        }

        return typeResponse;
    }

    private static ProfileResponse convert(Profile profile) {

        ProfileResponse typeResponse = new ProfileResponse();
        typeResponse.setId(profile.getId().intValue());
        typeResponse.setProfilename(profile.getProfilename());
        typeResponse.setDescription(profile.getDescription());
        typeResponse.setProfiletype(profile.getProfiletype());
        typeResponse.setPathref(profile.getPathref());
        typeResponse.setHashval(profile.getHashval());
        typeResponse.setParentid(profile.getParentid());
//        typeResponse.setId(profile.getId());
        typeResponse.setCreatedBy(profile.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profile.getCreatedDateTime()));
        typeResponse.setModifiedBy(profile.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profile.getModifiedDateTime()));
        typeResponse.setIsDeleted(profile.getIsDeleted());


        return typeResponse;
    }

    private static ActionFieldResponse convert(ActionField actionField) {

        ActionFieldResponse typeResponse = new ActionFieldResponse();
        typeResponse.setActionAlias(actionField.getActionAlias());
        typeResponse.setActionDescription(actionField.getActionDescription());
        typeResponse.setReadOnly(actionField.getReadOnly());
        typeResponse.setIsInputUpperCase(actionField.getIsInputUpperCase());
        typeResponse.setIsHidden(actionField.getIsHidden());
        typeResponse.setReferenceAlias(actionField.getReferenceAlias());
        //typeResponse.setProfileId(actionField.getProfileId());
        typeResponse.setId(actionField.getId());
        typeResponse.setCreatedBy(actionField.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(actionField.getCreatedDateTime()));
        typeResponse.setModifiedBy(actionField.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(actionField.getModifiedDateTime()));
        typeResponse.setIsDeleted(actionField.getIsDeleted());
        typeResponse.setActionTypeId(actionField.getActionTypeId());
        typeResponse.setFormula(actionField.getFormula());
        typeResponse.setIsNumeric(actionField.getIsNumeric());
        typeResponse.setFieldEvent(actionField.getFieldEvent());
        typeResponse.setIsMandatory(actionField.getIsMandatory());

        if (actionField.getActionEffectiveFields() != null) {
            List<ActionEffectiveFieldResponse> stockTransResponses = new ArrayList<>();
            for (ActionEffectiveField actionEffectiveField : actionField.getActionEffectiveFields()) {
                if (actionEffectiveField.getIsDeleted() == Deleted.NO) {
                    stockTransResponses.add(convertTwo(actionEffectiveField));
                }
            }
            typeResponse.setActionEffectiveFieldResponses(stockTransResponses);
        }

        return typeResponse;
    }

    private static ActionEffectiveFieldResponse convertTwo(ActionEffectiveField actionEffectiveField) {

        ActionEffectiveFieldResponse typeResponse = new ActionEffectiveFieldResponse();
        typeResponse.setId(actionEffectiveField.getId());
        typeResponse.setActionFieldId(actionEffectiveField.getActionFieldId());
        typeResponse.setEffectiveFieldAlias(actionEffectiveField.getEffectiveFieldAlias());
        typeResponse.setId(actionEffectiveField.getId());
        typeResponse.setCreatedBy(actionEffectiveField.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(actionEffectiveField.getCreatedDateTime()));
        typeResponse.setModifiedBy(actionEffectiveField.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(actionEffectiveField.getModifiedDateTime()));
        typeResponse.setIsDeleted(actionEffectiveField.getIsDeleted());

        return typeResponse;
    }
}
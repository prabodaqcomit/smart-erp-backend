package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.ActionFieldAndEffectiveSaveRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionFieldAndEffectiveUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionFieldRequest;
import lk.quantacom.smarterpbackend.dto.request.NumericFormulaRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionFieldResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActionFieldService {

    ActionFieldResponse save(ActionFieldRequest request);

    ActionFieldResponse update(ActionFieldAndEffectiveUpdateRequest request);

    ActionFieldResponse getById(Long id);

    ActionFieldResponse getByAlias(String alias,String refAlias);

    List<ActionFieldResponse> getAll();

    Integer delete(Long id);

    ActionFieldResponse saveFieldEffective(ActionFieldAndEffectiveSaveRequest request);

//    List<ActionFieldResponse> getAllByStaffNo(Integer staffno);

    float evaluateNumericFormula(NumericFormulaRequest numericFormulaRequest);

    List<ActionFieldResponse> getAllByReferenceAliasAndProfileId(Long profileId , String referenceAlias);

    List<ActionFieldResponse> getAllByReferenceAliasAndNumeric(String referenceAlias);
}
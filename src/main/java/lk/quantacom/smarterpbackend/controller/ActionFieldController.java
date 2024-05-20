package lk.quantacom.smarterpbackend.controller;


import lk.quantacom.smarterpbackend.dto.request.ActionFieldAndEffectiveSaveRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionFieldAndEffectiveUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.NumericFormulaRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionFieldResponse;
import lk.quantacom.smarterpbackend.service.ActionFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ActionField")
@RestController
@CrossOrigin
public class ActionFieldController {

    @Autowired
    private ActionFieldService actionFieldService;


//    @PostMapping
//    public ResponseEntity<ActionFieldResponse> save(@Valid @RequestBody ActionFieldRequest request){
//        ActionFieldResponse save = actionFieldService.save(request);
//        return ResponseEntity.ok(save);
//    }

    @PutMapping
    public ResponseEntity<ActionFieldResponse> update(@Valid @RequestBody ActionFieldAndEffectiveUpdateRequest request){
        ActionFieldResponse updated = actionFieldService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ActionFieldResponse> getById(@PathVariable("id") @NotBlank Long id){
        ActionFieldResponse get = actionFieldService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping("ByAlias/{refAlias}/{alias}")
    public ResponseEntity<ActionFieldResponse> getByAlias(@PathVariable("refAlias") @NotBlank String refAlias,@PathVariable("alias") @NotBlank String alias){
        ActionFieldResponse get = actionFieldService.getByAlias(alias,refAlias);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ActionFieldResponse>> getAll(){
        List<ActionFieldResponse> getall = actionFieldService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = actionFieldService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @PostMapping("SaveAll")
    public ResponseEntity<ActionFieldResponse> saveAll(@Valid @RequestBody ActionFieldAndEffectiveSaveRequest request){
        ActionFieldResponse save = actionFieldService.saveFieldEffective(request);
        return ResponseEntity.ok(save);
    }

//    @GetMapping("getAllByStaffNo/{staffno}")
//    public ResponseEntity<List<ActionFieldResponse>> getAllByStaffNo(@PathVariable("staffno") @NotBlank Integer staffno){
//        List<ActionFieldResponse> getall = actionFieldService.getAllByStaffNo(staffno);
//        return ResponseEntity.ok(getall);
//    }

    @PostMapping("evaluateCalculationFormula")
    public ResponseEntity<Float> evaluateNumericFormula(@Valid @RequestBody NumericFormulaRequest numericFormulaRequest){
        float save = actionFieldService.evaluateNumericFormula(numericFormulaRequest);
        return ResponseEntity.ok(save);
    }

    @GetMapping("profileId/{profileId}/referenceAlias/{referenceAlias}")
    public ResponseEntity<List<ActionFieldResponse>> getAllByReferenceAliasAndProfileId(@PathVariable("profileId") @NotBlank Long profileId,
                                                                                        @PathVariable("referenceAlias") @NotBlank String referenceAlias){
        List<ActionFieldResponse> getall = actionFieldService.getAllByReferenceAliasAndProfileId(profileId,referenceAlias);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("NumericAndReferenceAlias/{referenceAlias}")
    public ResponseEntity<List<ActionFieldResponse>> getAllByReferenceAliasAndNumeric(@PathVariable("referenceAlias") @NotBlank String referenceAlias){
        List<ActionFieldResponse> getall = actionFieldService.getAllByReferenceAliasAndNumeric(referenceAlias);
        return ResponseEntity.ok(getall);
    }
}
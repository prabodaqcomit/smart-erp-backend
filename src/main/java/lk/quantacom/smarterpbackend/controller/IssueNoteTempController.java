package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.IssueNoteTempRequest;
import lk.quantacom.smarterpbackend.dto.request.IssueNoteTempUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.IssueNoteTempResponse;
import lk.quantacom.smarterpbackend.service.IssueNoteTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("IssueNoteTemp")
@RestController
@CrossOrigin
public class IssueNoteTempController {

    @Autowired
    private IssueNoteTempService issueNoteTempService;


    @PostMapping
    public ResponseEntity<List<IssueNoteTempResponse>> save(@Valid @RequestBody List<IssueNoteTempRequest> request){
        List<IssueNoteTempResponse> save = issueNoteTempService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<IssueNoteTempResponse> update(@Valid @RequestBody IssueNoteTempUpdateRequest request){
        IssueNoteTempResponse updated = issueNoteTempService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<IssueNoteTempResponse> getById(@PathVariable("id") @NotBlank Long id){
        IssueNoteTempResponse get = issueNoteTempService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<IssueNoteTempResponse>> getAll(){
        List<IssueNoteTempResponse> getall = issueNoteTempService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = issueNoteTempService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}
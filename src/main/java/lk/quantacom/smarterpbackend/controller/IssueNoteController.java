package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.IssueNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.IssueNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.IssueNoteResponse;
import lk.quantacom.smarterpbackend.service.IssueNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("IssueNote")
@RestController
@CrossOrigin
public class IssueNoteController {

    @Autowired
    private IssueNoteService issueNoteService;


    @PostMapping
    public ResponseEntity<List<IssueNoteResponse>> save(@Valid @RequestBody List<IssueNoteRequest> request){
        List<IssueNoteResponse> save = issueNoteService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<IssueNoteResponse> update(@Valid @RequestBody IssueNoteUpdateRequest request){
        IssueNoteResponse updated = issueNoteService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<IssueNoteResponse> getById(@PathVariable("id") @NotBlank Long id){
        IssueNoteResponse get = issueNoteService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<IssueNoteResponse>> getAll(){
        List<IssueNoteResponse> getall = issueNoteService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = issueNoteService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}
package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.UserLoggerRequest;
import lk.quantacom.smarterpbackend.dto.request.UserLoggerUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UserLoggerResponse;
import lk.quantacom.smarterpbackend.service.UserLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("UserLogger")
@RestController
@CrossOrigin
public class UserLoggerController {

    @Autowired
    private UserLoggerService userLoggerService;


    @PostMapping
    public ResponseEntity<UserLoggerResponse> save(@Valid @RequestBody UserLoggerRequest request){
        UserLoggerResponse save = userLoggerService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<UserLoggerResponse> update(@Valid @RequestBody UserLoggerUpdateRequest request){
        UserLoggerResponse updated = userLoggerService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<UserLoggerResponse> getById(@PathVariable("id") @NotBlank Long id){
        UserLoggerResponse get = userLoggerService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<UserLoggerResponse>> getAll(){
        List<UserLoggerResponse> getall = userLoggerService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = userLoggerService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}
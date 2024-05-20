package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dataSeeders.RecurringPointOfTimeSeeder;
import lk.quantacom.smarterpbackend.dto.request.RecurringPointOfTimeRequest;
import lk.quantacom.smarterpbackend.dto.response.RecurringPointOfTimeResponse;
import lk.quantacom.smarterpbackend.service.RecurringPointOfTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("RecurringPointOfTime")
@RestController
@CrossOrigin
public class RecurringPointOfTimeController {

    @Autowired
    private RecurringPointOfTimeService recurringPointOfTimeService;


//    @PostMapping
//    public ResponseEntity<RecurringPointOfTimeResponse> save(@Valid @RequestBody RecurringPointOfTimeRequest request){
//        RecurringPointOfTimeResponse save = recurringPointOfTimeService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<RecurringPointOfTimeResponse> update(@Valid @RequestBody RecurringPointOfTimeUpdateRequest request){
//        RecurringPointOfTimeResponse updated = recurringPointOfTimeService.update(request);
//        if(updated==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


//    @GetMapping("{id}")
//    public ResponseEntity<RecurringPointOfTimeResponse> getById(@PathVariable("id") @NotBlank Long id){
//        RecurringPointOfTimeResponse get = recurringPointOfTimeService.getById(id);
//
//        if(get==null){
//
//            return  ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(get);
//    }


    private List<RecurringPointOfTimeResponse> _CallSeeder(){
        List<RecurringPointOfTimeResponse> dbRPOTs = new ArrayList<>();
        List<RecurringPointOfTimeRequest> pointOfTimeRequests = RecurringPointOfTimeSeeder.Seed();
        for(RecurringPointOfTimeRequest request: pointOfTimeRequests){
            RecurringPointOfTimeResponse save = recurringPointOfTimeService.save(request);
            dbRPOTs.add(save);
        }
        return dbRPOTs;
    }


    @GetMapping()
    public ResponseEntity<List<RecurringPointOfTimeResponse>> getAll(){
        List<RecurringPointOfTimeResponse> getall = recurringPointOfTimeService.getAll();

        //Seed if empty
        if(getall == null || getall.isEmpty()){
            getall = _CallSeeder();
        }

        return ResponseEntity.ok(getall);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
//        int deleted = recurringPointOfTimeService.delete(id);
//        if(deleted==0){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}
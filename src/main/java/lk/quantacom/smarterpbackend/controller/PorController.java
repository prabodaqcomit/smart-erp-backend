package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PorheaderRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporheaderRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporheaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import lk.quantacom.smarterpbackend.service.PorServices;
import lk.quantacom.smarterpbackend.service.TblporheaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PorServices")
@RestController
@CrossOrigin
public class PorController {

    @Autowired
    private PorServices porServices;


    @PostMapping
    public ResponseEntity<TblporheaderResponse> save(@Valid @RequestBody PorheaderRequest request){
        TblporheaderResponse save = porServices.save(request);
        return ResponseEntity.ok(save);
    }


}
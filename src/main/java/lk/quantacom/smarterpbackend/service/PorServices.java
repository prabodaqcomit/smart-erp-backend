package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PorheaderRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporheaderRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporheaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PorServices {

 TblporheaderResponse save(PorheaderRequest request);


}
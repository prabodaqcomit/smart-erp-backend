package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GrnHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnHeaderResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface GrnHeaderService {

    GrnHeaderResponse save(GrnHeaderRequest request);

    GrnHeaderResponse saveSupOB(GrnHeaderRequest request);

    GrnHeaderResponse update(GrnHeaderUpdateRequest request);

    GrnHeaderResponse getById(Long id);

    List<GrnHeaderResponse> getAll();

    List<String> getGrnIds();

    Integer delete(Long id);

    File grnReport(Integer grnNo, String type);

}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GlSubAccTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.GlSubAccTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlMainAccTypeResponse;
import lk.quantacom.smarterpbackend.dto.response.GlSubAccTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GlSubAccTypeService {

    GlSubAccTypeResponse save(GlSubAccTypeRequest request);

    GlSubAccTypeResponse update(GlSubAccTypeUpdateRequest request);

    GlSubAccTypeResponse getById(Long id);

    List<GlSubAccTypeResponse> getAll();

    Integer delete(Long id);

    List<GlSubAccTypeResponse> getByCatAndMain(Long catId,Long mainAccId);

    List<GlSubAccTypeResponse> getAllById(Long id);

}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GlMainAccTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.GlMainAccTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlMainAccTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GlMainAccTypeService {

    GlMainAccTypeResponse save(GlMainAccTypeRequest request);

    GlMainAccTypeResponse update(GlMainAccTypeUpdateRequest request);

    GlMainAccTypeResponse getById(Long id);

    List<GlMainAccTypeResponse> getAll();

    Integer delete(Long id);

    List<GlMainAccTypeResponse> getAllByCat(Long catId);

    List<GlMainAccTypeResponse> getAllById(Long id);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ColorRequest;
import lk.quantacom.smarterpbackend.dto.request.ColorUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ColorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColorService {

    ColorResponse save(ColorRequest request);

    ColorResponse update(ColorUpdateRequest request);

    ColorResponse getById(Long id);

    List<ColorResponse> getAll();


    Integer delete(Long id);
}
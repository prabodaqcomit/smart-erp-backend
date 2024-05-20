package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BinCardRequest;
import lk.quantacom.smarterpbackend.dto.request.BinCardUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BinCardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BinCardService {

    BinCardResponse save(BinCardRequest request);

    BinCardResponse update(BinCardUpdateRequest request);

    BinCardResponse getById(Long id);

    List<BinCardResponse> getAll();


    Integer delete(Long id);
}
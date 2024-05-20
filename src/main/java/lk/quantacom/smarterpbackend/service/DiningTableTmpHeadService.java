package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpHeadRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpHeadUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableTmpHeadResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiningTableTmpHeadService {

    DiningTableTmpHeadResponse save(DiningTableTmpHeadRequest request);

    DiningTableTmpHeadResponse update(DiningTableTmpHeadUpdateRequest request);

    DiningTableTmpHeadResponse getById(Long id);

    List<DiningTableTmpHeadResponse> getAll();

    Integer delete(Long id);

    DiningTableTmpHeadResponse getByTableId(Long id);

    DiningTableTmpHeadResponse closeBill(Long id);
}
package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GiftVoucherPrint;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherRequest;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherSearch;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GiftVoucherResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface GiftVoucherService {

    String getMaxSRNO();

    List<GiftVoucherResponse> saveBulk(List<GiftVoucherRequest> request);

    GiftVoucherResponse save(GiftVoucherRequest request);

    GiftVoucherResponse update(GiftVoucherUpdateRequest request);

    GiftVoucherResponse getById(Long id);

    List<GiftVoucherResponse> getAll();

    List<GiftVoucherResponse> searchAll(GiftVoucherSearch request);

    File print(GiftVoucherPrint request);

    Integer delete(Long id);
}
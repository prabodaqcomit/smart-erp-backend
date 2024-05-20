package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BarcodesRequest;
import lk.quantacom.smarterpbackend.dto.request.BarcodesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BarcodesResponse;
import lk.quantacom.smarterpbackend.dto.response.ItemMasterResponse;
import lk.quantacom.smarterpbackend.dto.response.ItemsByBarcodesResponse;
import lk.quantacom.smarterpbackend.entity.Barcodes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.BarcodesRepository;
import lk.quantacom.smarterpbackend.repository.ItemMasterRepository;
import lk.quantacom.smarterpbackend.service.BarcodesService;
import lk.quantacom.smarterpbackend.service.ItemMasterService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarcodesServiceImpl implements BarcodesService {

    @Autowired
    private BarcodesRepository barcodesRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private ItemMasterService itemMasterService;

    private static BarcodesResponse convert(Barcodes barcodes) {

        BarcodesResponse typeResponse = new BarcodesResponse();
        typeResponse.setId(barcodes.getId());
        typeResponse.setBarcode(barcodes.getBarcode());
        typeResponse.setItemCode(barcodes.getItemCode());
        typeResponse.setCreatedBy(barcodes.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(barcodes.getCreatedDateTime()));
        typeResponse.setModifiedBy(barcodes.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(barcodes.getModifiedDateTime()));
        typeResponse.setIsDeleted(barcodes.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public BarcodesResponse save(BarcodesRequest request) {

        Barcodes barcodes = new Barcodes();
        barcodes.setBarcode(request.getBarcode());
        barcodes.setItemCode(request.getItemCode());
        barcodes.setIsDeleted(Deleted.NO);
        Barcodes save = barcodesRepository.save(barcodes);

        return convert(save);
    }

    @Override
    @Transactional
    public BarcodesResponse update(BarcodesUpdateRequest request) {

        Barcodes barcodes = barcodesRepository.findById(request.getId()).orElse(null);
        if (barcodes == null) {
            return null;
        }

        barcodes.setId(request.getId());
        barcodes.setBarcode(request.getBarcode());
        barcodes.setItemCode(request.getItemCode());
        Barcodes updated = barcodesRepository.save(barcodes);

        return (convert(updated));
    }

    @Override
    public BarcodesResponse getById(Long id) {

        return barcodesRepository.findById(id).map(BarcodesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BarcodesResponse> getAll() {

        return barcodesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BarcodesServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Barcodes got = barcodesRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        barcodesRepository.save(got);

        return 1;
    }

    @Override
    public ItemsByBarcodesResponse getItemByBarcode(String barcode) {

        ItemsByBarcodesResponse response = new ItemsByBarcodesResponse();

        List<Barcodes> barcodes = barcodesRepository.findByBarcodeAndIsDeleted(barcode,Deleted.NO);
        if(barcodes==null){
            return null;
        }

        response.setBarcodesResponseList(barcodesRepository.findByBarcodeAndIsDeleted(barcode,Deleted.NO)
                .stream().map(BarcodesServiceImpl::convert).collect(Collectors.toList()));

        List<ItemMasterResponse> responseList = new ArrayList<>();
        for (Barcodes barcodes1 : barcodes){
            ItemMasterResponse itemMasterResponse = itemMasterService.getByItemCode(barcodes1.getItemCode());
            responseList.add(itemMasterResponse);
        }

        response.setItemMasterResponseList(responseList);
        return response;
    }
}
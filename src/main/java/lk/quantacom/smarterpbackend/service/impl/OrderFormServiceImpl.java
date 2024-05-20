package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.OrderFormRequest;
import lk.quantacom.smarterpbackend.dto.request.OrderFormUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.OrderFormResponse;
import lk.quantacom.smarterpbackend.entity.OrderForm;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.OrderFormRepository;
import lk.quantacom.smarterpbackend.service.OrderFormService;
import lk.quantacom.smarterpbackend.service.UserHeadService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFormServiceImpl implements OrderFormService {

    @Autowired
    private OrderFormRepository orderFormRepository;

    @Autowired
    private UserHeadService userHeadService;

    @Override
    @Transactional
    public List<OrderFormResponse> save(List<OrderFormRequest> requestList) {

        List<OrderFormResponse> res=new ArrayList<>();

        int nxt=1;
        Integer max=orderFormRepository.getMaxId();
        if(max!=null){
            nxt=max+1;
        }

        for(OrderFormRequest request:requestList){

            OrderForm orderForm = new OrderForm();
            orderForm.setOrderId(nxt);
            orderForm.setLineNo(request.getLineNo());
            orderForm.setBranchId(request.getBranchId());
            orderForm.setItemCode(request.getItemCode());
            orderForm.setItemDesc(request.getItemDesc());
            orderForm.setCategoryCode(request.getCategoryCode());
            orderForm.setCustomerId(request.getCustomerId());
            //       orderForm.setOrderDate(request.getOrderDate() == null ? null : ConvertUtils.convertStrToDate(request.getOrderDate()));
            //       orderForm.setOrderTime(request.getOrderTime() == null ? null : ConvertUtils.convertStrToDate(request.getOrderTime()));
            orderForm.setSellPrice(request.getSellPrice());
            orderForm.setOrderQty(request.getOrderQty());
            orderForm.setItemValue(request.getItemValue());
            orderForm.setGrandTotal(request.getGrandTotal());
            orderForm.setStatus(request.getStatus());
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal();
//            String username = userDetails.getUsername();
//            System.out.println(username);
//            orderForm.setUserDetId(username);
            orderForm.setBatchNo(request.getBatchNo());
            orderForm.setClosesales(request.getClosesales());
            orderForm.setIsdiscountenable(request.getIsdiscountenable());
            orderForm.setIsDeleted(Deleted.NO);
            OrderForm save = orderFormRepository.save(orderForm);

            res.add(convert(save));
        }



        return res;
    }

    @Override
    @Transactional
    public OrderFormResponse update(OrderFormUpdateRequest request) {

        OrderForm orderForm = orderFormRepository.findById(request.getId()).orElse(null);
        if (orderForm == null) {
            return null;
        }

        orderForm.setId(request.getId());
        orderForm.setOrderId(request.getOrderId());
        orderForm.setLineNo(request.getLineNo());
        orderForm.setBranchId(request.getBranchId());
        orderForm.setItemCode(request.getItemCode());
        orderForm.setItemDesc(request.getItemDesc());
        orderForm.setCategoryCode(request.getCategoryCode());
        orderForm.setCustomerId(request.getCustomerId());
        orderForm.setOrderDate(request.getOrderDate() == null ? null : ConvertUtils.convertStrToDate(request.getOrderDate()));
        orderForm.setOrderTime(request.getOrderTime() == null ? null : ConvertUtils.convertStrToDate(request.getOrderTime()));
        orderForm.setSellPrice(request.getSellPrice());
        orderForm.setOrderQty(request.getOrderQty());
        orderForm.setItemValue(request.getItemValue());
        orderForm.setGrandTotal(request.getGrandTotal());
        orderForm.setStatus(request.getStatus());
        orderForm.setUserDetId(request.getUserDetId());
        orderForm.setBatchNo(request.getBatchNo());
        orderForm.setClosesales(request.getClosesales());
        orderForm.setIsdiscountenable(request.getIsdiscountenable());
        OrderForm updated = orderFormRepository.save(orderForm);

        return (convert(updated));
    }

    @Override
    public OrderFormResponse getById(Long id) {

        return orderFormRepository.findById(id).map(OrderFormServiceImpl::convert).orElse(null);
    }

    @Override
    public List<OrderFormResponse> getAll() {

        return orderFormRepository.findByIsDeleted(Deleted.NO)
                .stream().map(OrderFormServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        OrderForm got = orderFormRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        orderFormRepository.save(got);

        return 1;
    }

    private static OrderFormResponse convert(OrderForm orderForm) {

        OrderFormResponse typeResponse = new OrderFormResponse();
        typeResponse.setOrderId(orderForm.getOrderId());
        typeResponse.setLineNo(orderForm.getLineNo());
        typeResponse.setBranchId(orderForm.getBranchId());
        typeResponse.setItemCode(orderForm.getItemCode());
        typeResponse.setItemDesc(orderForm.getItemDesc());
        typeResponse.setCategoryCode(orderForm.getCategoryCode());
        typeResponse.setCustomerId(orderForm.getCustomerId());
        typeResponse.setOrderDate(orderForm.getOrderDate());
        typeResponse.setOrderTime(orderForm.getOrderTime());
        typeResponse.setSellPrice(orderForm.getSellPrice());
        typeResponse.setOrderQty(orderForm.getOrderQty());
        typeResponse.setItemValue(orderForm.getItemValue());
        typeResponse.setGrandTotal(orderForm.getGrandTotal());
        typeResponse.setStatus(orderForm.getStatus());
        typeResponse.setUserDetId(orderForm.getUserDetId());
        typeResponse.setBatchNo(orderForm.getBatchNo());
        typeResponse.setClosesales(orderForm.getClosesales());
        typeResponse.setIsdiscountenable(orderForm.getIsdiscountenable());
        typeResponse.setId(orderForm.getId());
        typeResponse.setCreatedBy(orderForm.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(orderForm.getCreatedDateTime()));
        typeResponse.setModifiedBy(orderForm.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(orderForm.getModifiedDateTime()));
        //typeResponse.setIsDeleted(orderForm.getIsDeleted());

        return typeResponse;
    }
}
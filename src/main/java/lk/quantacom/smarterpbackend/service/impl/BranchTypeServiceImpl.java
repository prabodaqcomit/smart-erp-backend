package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BranchTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.BranchTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BranchTypeResponse;
import lk.quantacom.smarterpbackend.entity.BranchType;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.BranchTypeRepository;
import lk.quantacom.smarterpbackend.service.BranchTypeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchTypeServiceImpl implements BranchTypeService {

    @Autowired
    private BranchTypeRepository branchTypeRepository;



    @Override
@Transactional
    public BranchTypeResponse save(BranchTypeRequest request) {

        BranchType branchType=new BranchType();
branchType.setName(request.getName());
branchType.setIsActive(request.getIsActive());
branchType.setIsDeleted(Deleted.NO);
BranchType save=branchTypeRepository.save(branchType);

        return convert(save);
    }

    @Override
    @Transactional
    public BranchTypeResponse update(BranchTypeUpdateRequest request) {

BranchType branchType = branchTypeRepository.findById(request.getId()).orElse(null);
        if(branchType==null){
            return null;
        }

branchType.setId(request.getId());
branchType.setName(request.getName());
branchType.setIsActive(request.getIsActive());
BranchType updated=branchTypeRepository.save(branchType);

        return (convert(updated));
    }

    @Override
    public BranchTypeResponse getById(Long id) {

       return branchTypeRepository.findById(id).map(BranchTypeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BranchTypeResponse> getAll() {

        return  branchTypeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BranchTypeServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

BranchType got=branchTypeRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        branchTypeRepository.save(got);

        return  1;
    }

private static BranchTypeResponse convert(BranchType branchType){

        BranchTypeResponse typeResponse=new BranchTypeResponse();
typeResponse.setName(branchType.getName());
typeResponse.setIsActive(branchType.getIsActive());
        typeResponse.setId(branchType.getId());
        typeResponse.setCreatedBy(branchType.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(branchType.getCreatedDateTime()));
        typeResponse.setModifiedBy(branchType.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(branchType.getModifiedDateTime()));
        typeResponse.setIsDeleted(branchType.getIsDeleted());

return typeResponse;
    }
}
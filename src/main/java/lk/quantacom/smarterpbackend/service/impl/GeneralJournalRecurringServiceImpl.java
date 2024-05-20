package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalRecurringResponse;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.GeneralJournalRecurring;
import lk.quantacom.smarterpbackend.repository.GeneralJournalRecuringRepository;
import lk.quantacom.smarterpbackend.service.GeneralJournalRecurringService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralJournalRecurringServiceImpl implements GeneralJournalRecurringService {

    @Autowired
    private GeneralJournalRecuringRepository generalJournalRecuringRepository;

    @Override
    @Transactional
    public GeneralJournalRecurringResponse save(GeneralJournalRecurringRequest request) {

        GeneralJournalRecurring generalJournalRecurring = new GeneralJournalRecurring();

        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        generalJournalRecurring.setBranch(branch);

        generalJournalRecurring.setRecurringName(request.getRecurringName());
        generalJournalRecurring.setTemplateId(request.getTemplateId());
        generalJournalRecurring.setStartDateTime(request.getStartDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getStartDateTime()));
        generalJournalRecurring.setEndDateTime(request.getEndDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getEndDateTime()));
        generalJournalRecurring.setRecurringFrequency(request.getRecurringFrequency());
        generalJournalRecurring.setRecurringFrequencyPOT(request.getRecurringFrequencyPOT());
        generalJournalRecurring.setIsRunAtEndPOT(request.getIsRunAtEndPOT());
        generalJournalRecurring.setLastRunDateTime(request.getLastRunDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getLastRunDateTime()));
        generalJournalRecurring.setTotalRunCount(request.getTotalRunCount());
        generalJournalRecurring.setIsDeleted(Deleted.NO);
        GeneralJournalRecurring save = generalJournalRecuringRepository.save(generalJournalRecurring);

        return convert(save);
    }

    @Override
    @Transactional
    public GeneralJournalRecurringResponse update(GeneralJournalRecurringUpdateRequest request) {

        GeneralJournalRecurring generalJournalRecurring = generalJournalRecuringRepository.findById(request.getId()).orElse(null);
        if (generalJournalRecurring == null) {
            return null;
        }

        generalJournalRecurring.setId(request.getId());

        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        generalJournalRecurring.setBranch(branch);

        generalJournalRecurring.setRecurringName(request.getRecurringName());
        generalJournalRecurring.setTemplateId(request.getTemplateId());
        generalJournalRecurring.setStartDateTime(request.getStartDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getStartDateTime()));
        generalJournalRecurring.setEndDateTime(request.getEndDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getEndDateTime()));
        generalJournalRecurring.setRecurringFrequency(request.getRecurringFrequency());
        generalJournalRecurring.setRecurringFrequencyPOT(request.getRecurringFrequencyPOT());
        generalJournalRecurring.setIsRunAtEndPOT(request.getIsRunAtEndPOT());
        generalJournalRecurring.setLastRunDateTime(request.getLastRunDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getLastRunDateTime()));
        generalJournalRecurring.setTotalRunCount(request.getTotalRunCount());
        GeneralJournalRecurring updated = generalJournalRecuringRepository.save(generalJournalRecurring);

        return convert(updated);
    }

    @Override
    public GeneralJournalRecurringResponse getById(Long id) {

        return generalJournalRecuringRepository.findById(id).map(GeneralJournalRecurringServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GeneralJournalRecurringResponse> getAll() {

        return generalJournalRecuringRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GeneralJournalRecurringServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public Page<GeneralJournalRecurringResponse> getPaginatedAll(int pageNumber, int countPerPage){
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage);
        Page<GeneralJournalRecurring> _pgResult = generalJournalRecuringRepository.findByIsDeleted(Deleted.NO, pgRequest);
        List<GeneralJournalRecurringResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalRecurringServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<GeneralJournalRecurringResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage){
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage);
        Page<GeneralJournalRecurring> _pgResult = generalJournalRecuringRepository.findByIsDeletedAndBranchId(Deleted.NO, branchId, pgRequest);
        List<GeneralJournalRecurringResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalRecurringServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<GeneralJournalRecurringResponse> getSearchPaginated(int pageNumber, int countPerPage, GeneralJournalRecurringSearchRequest searchRequest){
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage);
        Page<GeneralJournalRecurring> _pgResult =
                generalJournalRecuringRepository.findByIsDeletedAndRecurringNameContaining(
                        Deleted.NO,
                        searchRequest.getRecurringName(),
                        pgRequest
                );
        List<GeneralJournalRecurringResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalRecurringServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<GeneralJournalRecurringResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, GeneralJournalRecurringSearchRequest searchRequest){
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage);
        Page<GeneralJournalRecurring> _pgResult =
                generalJournalRecuringRepository.findByIsDeletedAndBranchIdAndRecurringNameContaining(
                        Deleted.NO,
                        branchId,
                        searchRequest.getRecurringName(),
                        pgRequest
                );
        List<GeneralJournalRecurringResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalRecurringServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GeneralJournalRecurring got = generalJournalRecuringRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        generalJournalRecuringRepository.save(got);

        return 1;
    }

    public static GeneralJournalRecurringResponse convert(GeneralJournalRecurring generalJournalRecurring) {

        GeneralJournalRecurringResponse typeResponse = new GeneralJournalRecurringResponse();

        typeResponse.setBranch( BranchNetworkServiceImpl.convert(generalJournalRecurring.getBranch()) );

        typeResponse.setRecurringName(generalJournalRecurring.getRecurringName());
        typeResponse.setTemplateId(generalJournalRecurring.getTemplateId());
        typeResponse.setStartDateTime(generalJournalRecurring.getStartDateTime());
        typeResponse.setEndDateTime(generalJournalRecurring.getEndDateTime());
        typeResponse.setRecurringFrequency(generalJournalRecurring.getRecurringFrequency());
        typeResponse.setRecurringFrequencyPOT(generalJournalRecurring.getRecurringFrequencyPOT());
        typeResponse.setIsRunAtEndPOT(generalJournalRecurring.getIsRunAtEndPOT());
        typeResponse.setLastRunDateTime(generalJournalRecurring.getLastRunDateTime());
        typeResponse.setTotalRunCount(generalJournalRecurring.getTotalRunCount());
        typeResponse.setId(generalJournalRecurring.getId());
        typeResponse.setCreatedBy(generalJournalRecurring.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(generalJournalRecurring.getCreatedDateTime()));
        typeResponse.setModifiedBy(generalJournalRecurring.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(generalJournalRecurring.getModifiedDateTime()));
        typeResponse.setIsDeleted(generalJournalRecurring.getIsDeleted());

        return typeResponse;
    }

}
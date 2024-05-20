package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalReverseRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalReverseUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalReverseResponse;
import lk.quantacom.smarterpbackend.entity.GeneralJournalHeader;
import lk.quantacom.smarterpbackend.entity.GeneralJournalReverse;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.GeneralJournalHeaderRepository;
import lk.quantacom.smarterpbackend.repository.GeneralJournalReverseRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.GeneralJournalReverseService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralJournalReverseServiceImpl implements GeneralJournalReverseService {

    @Autowired
    private GeneralJournalReverseRepository generalJournalReverseRepository;

    @Autowired
    private GeneralJournalHeaderRepository generalJournalHeaderRepository;

    @Override
    @Transactional
    public GeneralJournalReverseResponse save(GeneralJournalReverseRequest request) {

        GeneralJournalReverse generalJournalReverse = new GeneralJournalReverse();

        GeneralJournalHeader reverseForHeader = generalJournalHeaderRepository.getById(request.getReverseForGeneralJournalId());
        //reverseForHeader.setId(request.getReverseForGeneralJournalId());
        generalJournalReverse.setReverseForJournalId(request.getReverseForGeneralJournalId());
        generalJournalReverse.setReverseForJournalNumber(reverseForHeader.getJournalNumber());


        GeneralJournalHeader reverseAtHeader = generalJournalHeaderRepository.getById(request.getReverseAtGeneralJournalId());
        //reverseAtHeader.setId(request.getReverseAtGeneralJournalId());
        generalJournalReverse.setReverseAtJournalId(request.getReverseAtGeneralJournalId());
        generalJournalReverse.setReverseAtJournalNumber(reverseAtHeader.getJournalNumber());

        generalJournalReverse.setIsDeleted(Deleted.NO);
        GeneralJournalReverse save = generalJournalReverseRepository.save(generalJournalReverse);

        return convert(save);
    }

    @Override
    @Transactional
    public GeneralJournalReverseResponse update(GeneralJournalReverseUpdateRequest request) {

        GeneralJournalReverse generalJournalReverse = generalJournalReverseRepository.findById(request.getId()).orElse(null);
        if (generalJournalReverse == null) {
            return null;
        }
        GeneralJournalHeader reverseForHeader = new GeneralJournalHeader();

        generalJournalReverse.setId(request.getId());
        reverseForHeader.setId(request.getReverseForGeneralJournalId());
        generalJournalReverse.setReverseForJournalNumber(reverseForHeader.getJournalNumber());
        generalJournalReverse.setReverseForJournalId(request.getReverseForGeneralJournalId());

        GeneralJournalHeader reverseAtHeader = new GeneralJournalHeader();
        reverseAtHeader.setId(request.getReverseAtGeneralJournalId());
        generalJournalReverse.setReverseAtJournalNumber(reverseAtHeader.getJournalNumber());
        generalJournalReverse.setReverseAtJournalId(request.getReverseAtGeneralJournalId());

        generalJournalReverse.setIsDeleted(Deleted.NO);

        GeneralJournalReverse updated = generalJournalReverseRepository.save(generalJournalReverse);

        return (convert(updated));
    }

    @Override
    public GeneralJournalReverseResponse getById(Long id) {

        return generalJournalReverseRepository.findById(id).map(GeneralJournalReverseServiceImpl::convert).orElse(null);
    }

    @Override
    public GeneralJournalReverseResponse getIfAlreadyReversed(Long journalId) {

        GeneralJournalReverse hasReversed = generalJournalReverseRepository.findByIsDeletedAndReverseForJournalId(
                Deleted.NO,
                journalId
        );

        if(hasReversed == null){
            return  null;
        }

        return convert(hasReversed);
    }

    @Override
    public List<GeneralJournalReverseResponse> getAll() {

        return generalJournalReverseRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GeneralJournalReverseServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        GeneralJournalReverse got = generalJournalReverseRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        generalJournalReverseRepository.save(got);

        return 1;
    }

    private static GeneralJournalReverseResponse convert(GeneralJournalReverse generalJournalReverse) {

        GeneralJournalReverseResponse typeResponse = new GeneralJournalReverseResponse();
        typeResponse.setReverseForJournalNumber(generalJournalReverse.getReverseForJournalNumber());
        typeResponse.setReverseAtJournalNumber(generalJournalReverse.getReverseAtJournalNumber());
        typeResponse.setId(generalJournalReverse.getId());
        typeResponse.setCreatedBy(generalJournalReverse.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(generalJournalReverse.getCreatedDateTime()));
        typeResponse.setModifiedBy(generalJournalReverse.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(generalJournalReverse.getModifiedDateTime()));
        typeResponse.setIsDeleted(generalJournalReverse.getIsDeleted());

        return typeResponse;
    }

}
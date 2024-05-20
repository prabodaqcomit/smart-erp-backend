package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalDetailResponse;
import lk.quantacom.smarterpbackend.entity.GeneralJournalDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeneralJournalDetailService {

    //GeneralJournalDetail save(GeneralJournalDetailRequest request);

    //GeneralJournalDetail update(GeneralJournalDetailUpdateRequest request);

    GeneralJournalDetailResponse getById(Long id);

    //List<GeneralJournalDetailResponse> getByGeneralJournalHeaderId(Long id);

    List<GeneralJournalDetailResponse> getAll();


    Integer delete(Long id);
}
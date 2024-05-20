package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalTemplateDetailResponse;
import lk.quantacom.smarterpbackend.entity.GeneralJournalTemplateDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeneralJournalTemplateDetailService {

    //GeneralJournalTemplateDetail save(GeneralJournalTemplateDetailRequest request);

    //GeneralJournalTemplateDetail update(GeneralJournalTemplateDetailUpdateRequest request);

    GeneralJournalTemplateDetailResponse getById(Long id);

    List<GeneralJournalTemplateDetailResponse> getAll();


    Integer delete(Long id);
}
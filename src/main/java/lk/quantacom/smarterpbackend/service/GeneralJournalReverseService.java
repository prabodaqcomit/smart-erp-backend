package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalReverseRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalReverseUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalReverseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeneralJournalReverseService {

    GeneralJournalReverseResponse save(GeneralJournalReverseRequest request);

    GeneralJournalReverseResponse update(GeneralJournalReverseUpdateRequest request);

    GeneralJournalReverseResponse getById(Long id);

    GeneralJournalReverseResponse getIfAlreadyReversed(Long journalId);

    List<GeneralJournalReverseResponse> getAll();


    Integer delete(Long id);
}
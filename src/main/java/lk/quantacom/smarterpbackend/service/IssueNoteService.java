package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.IssueNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.IssueNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.IssueNoteResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueNoteService {

    List<IssueNoteResponse> save(List<IssueNoteRequest> request);

    IssueNoteResponse update(IssueNoteUpdateRequest request);

    IssueNoteResponse getById(Long id);

    List<IssueNoteResponse> getAll();

    Integer delete(Long id);

}
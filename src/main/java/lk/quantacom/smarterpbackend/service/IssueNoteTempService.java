package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.IssueNoteTempRequest;
import lk.quantacom.smarterpbackend.dto.request.IssueNoteTempUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.IssueNoteTempResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueNoteTempService {

    List<IssueNoteTempResponse> save(List<IssueNoteTempRequest> request);

    IssueNoteTempResponse update(IssueNoteTempUpdateRequest request);

    IssueNoteTempResponse getById(Long id);

    List<IssueNoteTempResponse> getAll();

    Integer delete(Long id);
}
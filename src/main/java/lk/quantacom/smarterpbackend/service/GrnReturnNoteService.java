package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GrnReturnNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnReturnNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnReturnNoteResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface GrnReturnNoteService {

    List<GrnReturnNoteResponse> save(List<GrnReturnNoteRequest> request);

    GrnReturnNoteResponse update(GrnReturnNoteUpdateRequest request);

    GrnReturnNoteResponse getById(Long id);

    List<GrnReturnNoteResponse> getAll();

    File print(Long returnId);

    Integer delete(Long id);
}
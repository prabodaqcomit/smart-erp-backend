package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.Department4Request;
import lk.quantacom.smarterpbackend.dto.request.Department4UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department4Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Department4Service {

    Department4Response save(Department4Request request);

    Department4Response update(Department4UpdateRequest request);

    Department4Response getById(Long id);

    List<Department4Response> getAll();

    List<Department4Response> getAllActive();

    Integer delete(Long id);
}
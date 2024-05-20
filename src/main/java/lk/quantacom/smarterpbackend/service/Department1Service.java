package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.Department1Request;
import lk.quantacom.smarterpbackend.dto.request.Department1UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department1Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Department1Service {

    Department1Response save(Department1Request request);

    Department1Response update(Department1UpdateRequest request);

    Department1Response getById(Long id);

    List<Department1Response> getAll();

    List<Department1Response> getAllActive();

    Integer delete(Long id);
}
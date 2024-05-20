package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.Department3Request;
import lk.quantacom.smarterpbackend.dto.request.Department3UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department3Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Department3Service {

    Department3Response save(Department3Request request);

    Department3Response update(Department3UpdateRequest request);

    Department3Response getById(Long id);

    List<Department3Response> getAll();

    List<Department3Response> getAllActive();

    Integer delete(Long id);
}
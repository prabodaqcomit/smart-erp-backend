package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.Department2Request;
import lk.quantacom.smarterpbackend.dto.request.Department2UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department1Response;
import lk.quantacom.smarterpbackend.dto.response.Department2Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Department2Service {

    Department2Response save(Department2Request request);

    Department2Response update(Department2UpdateRequest request);

    Department2Response getById(Long id);

    List<Department2Response> getAll();

    List<Department2Response> getAllActive();

    Integer delete(Long id);
}
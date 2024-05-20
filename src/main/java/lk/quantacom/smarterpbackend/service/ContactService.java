package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ContactRequest;
import lk.quantacom.smarterpbackend.dto.request.ContactUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ContactResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    ContactResponse save(ContactRequest request);

    ContactResponse update(ContactUpdateRequest request);

    ContactResponse getById(Long id);

    List<ContactResponse> getAll();


    Integer delete(Long id);
}
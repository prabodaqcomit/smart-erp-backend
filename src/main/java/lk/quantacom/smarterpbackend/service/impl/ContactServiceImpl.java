package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ContactRequest;
import lk.quantacom.smarterpbackend.dto.request.ContactUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ContactResponse;
import lk.quantacom.smarterpbackend.entity.Contact;
import lk.quantacom.smarterpbackend.repository.ContactRepository;
import lk.quantacom.smarterpbackend.service.ContactService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;


    @Override
    @Transactional
    public ContactResponse save(ContactRequest request) {

        Contact contact = new Contact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setNicNumber(request.getNicNumber());
        contact.setMobileNumber(request.getMobileNumber());
        contact.setEmail(request.getEmail());
        contact.setAddress(request.getAddress());
        contact.setCustomerId(request.getCustomerId());
        contact.setSupplierId(request.getSupplierId());
        contact.setEmployeeId(request.getEmployeeId());
        contact.setIsDeleted(Deleted.NO);
        Contact save = contactRepository.save(contact);

        return convert(save);
    }

    @Override
    @Transactional
    public ContactResponse update(ContactUpdateRequest request) {

        Contact contact = contactRepository.findById(request.getId()).orElse(null);
        if (contact == null) {
            return null;
        }

        contact.setId(request.getId());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setNicNumber(request.getNicNumber());
        contact.setMobileNumber(request.getMobileNumber());
        contact.setEmail(request.getEmail());
        contact.setAddress(request.getAddress());
        contact.setCustomerId(request.getCustomerId());
        contact.setSupplierId(request.getSupplierId());
        contact.setEmployeeId(request.getEmployeeId());
        Contact updated = contactRepository.save(contact);

        return (convert(updated));
    }

    @Override
    public ContactResponse getById(Long id) {

        return contactRepository.findById(id).map(ContactServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ContactResponse> getAll() {

        return contactRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ContactServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Contact got = contactRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        contactRepository.save(got);

        return 1;
    }

    public static ContactResponse convert(Contact contact) {

        ContactResponse typeResponse = new ContactResponse();
        typeResponse.setFirstName(contact.getFirstName());
        typeResponse.setLastName(contact.getLastName());
        typeResponse.setNicNumber(contact.getNicNumber());
        typeResponse.setMobileNumber(contact.getMobileNumber());
        typeResponse.setEmail(contact.getEmail());
        typeResponse.setAddress(contact.getAddress());
        typeResponse.setCustomerId(contact.getCustomerId());
        typeResponse.setSupplierId(contact.getSupplierId());
        typeResponse.setEmployeeId(contact.getEmployeeId());
        typeResponse.setId(contact.getId());
        typeResponse.setCreatedBy(contact.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(contact.getCreatedDateTime()));
        typeResponse.setModifiedBy(contact.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(contact.getModifiedDateTime()));
        typeResponse.setIsDeleted(contact.getIsDeleted());

        return typeResponse;
    }
}
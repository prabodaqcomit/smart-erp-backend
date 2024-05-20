package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.RecurringPointOfTimeRequest;
import lk.quantacom.smarterpbackend.dto.request.RecurringPointOfTimeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.RecurringPointOfTimeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecurringPointOfTimeService {

    RecurringPointOfTimeResponse save(RecurringPointOfTimeRequest request);

    RecurringPointOfTimeResponse update(RecurringPointOfTimeUpdateRequest request);

    RecurringPointOfTimeResponse getById(Long id);

    List<RecurringPointOfTimeResponse> getAll();


    Integer delete(Long id);
}
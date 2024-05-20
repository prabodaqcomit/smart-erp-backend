package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.NumberToWordRequest;
import lk.quantacom.smarterpbackend.dto.response.NumberToWordResponse;

public interface UtilityService {

    NumberToWordResponse convertNumberToWord(NumberToWordRequest toWordRequest);

}

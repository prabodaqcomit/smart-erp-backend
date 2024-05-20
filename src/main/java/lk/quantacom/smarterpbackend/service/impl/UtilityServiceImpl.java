package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.NumberToWordRequest;
import lk.quantacom.smarterpbackend.dto.response.NumberToWordResponse;
import lk.quantacom.smarterpbackend.service.UtilityService;
import org.springframework.stereotype.Service;
import pl.allegro.finance.tradukisto.LongValueConverters;
import pl.allegro.finance.tradukisto.ValueConverters;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Override
    public NumberToWordResponse convertNumberToWord(NumberToWordRequest toWordRequest) {
        LongValueConverters valueConverter = null;
        NumberToWordResponse wordResponse = new NumberToWordResponse();
        wordResponse.setNumber(toWordRequest.getNumber());
        switch (toWordRequest.getLanguage().toUpperCase().trim()){
            case "EN":
            default:
                wordResponse.setLanguage("EN");
                valueConverter = LongValueConverters.ENGLISH_LONG;
                break;
        }

        wordResponse.setNumberInWord(valueConverter.asWords( toWordRequest.getNumber() ) );

        return  wordResponse;
    }
}

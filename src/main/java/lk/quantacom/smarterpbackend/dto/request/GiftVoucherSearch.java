package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

@Data
public class GiftVoucherSearch {

    private String fromDate;

    private String toDate;

    private String type;

}

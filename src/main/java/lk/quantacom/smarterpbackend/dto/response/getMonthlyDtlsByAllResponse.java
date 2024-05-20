package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

@Data
public class getMonthlyDtlsByAllResponse {

    public String FLD_INVNO;

    public String CUSTOMER;

    public String FLD_ITEMCODE;

    public Double getXS;

    public Double getS;

    public Double getM;

    public Double getL;

    public Double getXL;

    public Double get2XL;

    public Double get3XL;

    public Double get4XL;

    public Double get5XL;

    public Double get6XL;

    public Double QTY;

    public Double FLD_PRICE;

    public Double COST;

    public Double VALUE;

}
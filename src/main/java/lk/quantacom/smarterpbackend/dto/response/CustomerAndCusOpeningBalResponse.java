package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface CustomerAndCusOpeningBalResponse {

     Integer getCUSTOMER_OPENING_BALANCE_ID();

     String getFLD_CUSTOMER_ID();

     Date getFLD_DATE();

     Double getFLD_DUE_BALANCE();

     Double getFLD_NET_AMOUNT();

     Double getFLD_PAID_AMOUNT();

     String getNAME();


}
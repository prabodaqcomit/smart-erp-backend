package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GLPayMethodDetailsRequest {


    private Long glPayHeaderId;

    private Long payMethodId;

    private String payMethodName;

    private Double Amount;

    private String ChequeDate;

    private String Bank;

    private String Account;

    private String ChequeNumber;

    private String Reference;

    private String TransferDate;

    private String FromBank;

    private String FromAccount;

    private String ToBank;

    private String ToAccount;

    private String FromWallet;

    private String ToWallet;

    private String FromCard;

}
package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TabFormRequest {

    private String formAlias;

    private Boolean isNavTab;

    private String rootFormAlias;

    private String iconHex;

    private String iconStyle;

    private Integer navIndex;

    private String formName;

}
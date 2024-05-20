package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TabFormUpdateRequest {

    private Long id;

    private String formAlias;
    private String rootFormAlias;
    private Boolean isNavTab;

    private String iconHex;

    private String iconStyle;

    private Integer navIndex;

    private String formName;

}
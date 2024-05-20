package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TblinvListDtlsResponse {


 private TblinvhedResponse tblinvhedResponse;

 private List<TblinvdtlResponse> tblinvdtlResponseList;

 private TblpaydtlResponse tblpaydtlResponse;


 }
package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DepartmentResponse {

    private List<Department1Response> department1;

    private List<Department2Response> department2;

    private List<Department3Response> department3;

    private List<Department4Response> department4;

}
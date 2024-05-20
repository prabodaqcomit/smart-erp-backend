package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.GeneralJournalDetailResponse;
import lk.quantacom.smarterpbackend.entity.GeneralJournalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;
import java.util.Date;
import java.util.List;

public interface GeneralJournalDetailRepository extends JpaRepository<GeneralJournalDetail,Long> , JpaSpecificationExecutor {


    List<GeneralJournalDetail> findByIsDeleted(Deleted deleted);

    List<GeneralJournalDetailResponse> findByGeneralJournalHeaderIdAndIsDeleted(Long headerId, Deleted deleted);

}
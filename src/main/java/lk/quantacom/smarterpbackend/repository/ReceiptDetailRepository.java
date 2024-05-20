package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GeneralJournalHeader;
import lk.quantacom.smarterpbackend.entity.ReceiptDetail;
import lk.quantacom.smarterpbackend.entity.ReceiptHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;
import java.util.Date;
import java.util.List;

public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail,Long> , JpaSpecificationExecutor {


    List<ReceiptDetail> findByIsDeleted(Deleted deleted);

    List<ReceiptDetail> findByIsDeletedAndReceiptHeaderId(Deleted deleted, Long headerId);

    @Modifying
    @Query(value = "DELETE FROM receipt_detail d WHERE  d.receipt_header_id = ?1", nativeQuery = true)
    int deleteReceiptDetailsByHeaderId(Long headerId);
}
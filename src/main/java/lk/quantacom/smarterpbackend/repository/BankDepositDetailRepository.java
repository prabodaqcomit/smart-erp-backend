package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.BankDepositDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BankDepositDetailRepository extends JpaRepository<BankDepositDetail, Long>, JpaSpecificationExecutor<BankDepositDetail> {

    List<BankDepositDetail> findByIsDeleted(Deleted deleted);

    List<BankDepositDetail> findByIsDeletedAndBankDepositHeaderId(Deleted deleted, Long headerId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bank_deposit_detail d WHERE  d.bank_deposit_header_id = ?1", nativeQuery = true)
    int deleteBankDepositDetailsByHeaderId(Long headerId);

}
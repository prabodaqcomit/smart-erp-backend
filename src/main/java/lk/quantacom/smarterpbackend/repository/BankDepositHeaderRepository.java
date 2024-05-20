package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.BankDepositHeader;
import lk.quantacom.smarterpbackend.entity.GeneralJournalHeader;
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

public interface BankDepositHeaderRepository extends JpaRepository<BankDepositHeader, Long>, JpaSpecificationExecutor<BankDepositHeader> {

    @Query(value = "select deposit_number from bank_deposit_header \n" +
            "            where id = (select max(id) from bank_deposit_header)",nativeQuery = true)
    String getMaximumDepositNumber();

    List<BankDepositHeader> findByIsDeleted(Deleted deleted);


    //-------------------------------Paginated----------------------------------------------------------------
    Page<BankDepositHeader> findByIsDeleted(Deleted deleted, Pageable pageable);

    Page<BankDepositHeader> findByIsDeletedAndBranchId(Deleted deleted, Long branchId, Pageable pageable);

    @Query(value =
            "SELECT h.* FROM bank_deposit_header h\n" +
            "WHERE\n" +
            "h.id IN (\n" +
            "   SELECT \n" +
            "   DISTINCT h2.id \n" +
            "   FROM bank_deposit_header h2 \n" +
            "   INNER JOIN bank_deposit_detail d ON \n" +
            "   h2.id = d.bank_deposit_header_id \n" +
            "   WHERE \n" +
            "   h2.is_deleted = 0   \n" +
            "   AND CASE WHEN ?1 IS NOT NULL THEN h2.transaction_date BETWEEN ?1 AND ?2 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?3 IS NOT NULL THEN h2.deposit_number = ?3 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?4 IS NOT NULL THEN h2.to_account_id = ?4 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?5 IS NOT NULL THEN d.transaction_branch_id = ?5 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?6 IS NOT NULL THEN d.transaction_id = ?6 ELSE TRUE END \n" +
            "   AND CASE WHEN ?7 IS NOT NULL THEN h2.total_amount = ?7 ELSE TRUE END " +
            ")"
            ,countQuery =
            "SELECT count(h.id) FROM bank_deposit_header h\n" +
            "WHERE\n" +
            "h.id IN (\n" +
            "   SELECT \n" +
            "   DISTINCT h2.id \n" +
            "   FROM bank_deposit_header h2 \n" +
            "   INNER JOIN bank_deposit_detail d ON \n" +
            "   h2.id = d.bank_deposit_header_id \n" +
            "   WHERE \n" +
            "   h2.is_deleted = 0   \n" +
            "   AND CASE WHEN ?1 IS NOT NULL THEN h2.transaction_date BETWEEN ?1 AND ?2 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?3 IS NOT NULL THEN h2.deposit_number = ?3 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?4 IS NOT NULL THEN h2.to_account_id = ?4 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?5 IS NOT NULL THEN d.transaction_branch_id = ?5 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?6 IS NOT NULL THEN d.transaction_id = ?6 ELSE TRUE END \n" +
            "   AND CASE WHEN ?7 IS NOT NULL THEN h2.total_amount = ?7 ELSE TRUE END " +
            ")"
            ,nativeQuery = true)
    Page<BankDepositHeader> searchCustomFilterBankDeposits(
            Date transactionFromDate, Date transactionToDate,
            String depositNumber,
            Long toAccountId,
            String transactionBranchId,
            String transactionId,
            Double amount,
            Pageable pageable
            //Long ResultOffset,
            //Integer ResultLimit
    );

    @Query(value =
            "SELECT h.* FROM bank_deposit_header h \n" +
            "WHERE \n" +
            "h.id IN (\n" +
            "   SELECT \n" +
            "   DISTINCT h2.id \n" +
            "   FROM bank_deposit_header h2  \n" +
            "   INNER JOIN bank_deposit_detail d ON\n" +
            "   h2.id = d.bank_deposit_header_id \n" +
            "   WHERE   \n" +
            "   h2.is_deleted = 0   \n" +
            "   AND h.branch_id = ?1\n" +
            "   AND CASE WHEN ?2 IS NOT NULL THEN h2.transaction_date BETWEEN ?2 AND ?3 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?4 IS NOT NULL THEN h2.deposit_number = ?4 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?5 IS NOT NULL THEN h2.to_account_id = ?5 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?6 IS NOT NULL THEN d.transaction_branch_id = ?6 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?7 IS NOT NULL THEN d.transaction_id = ?7 ELSE TRUE END \n" +
            "   AND CASE WHEN ?8 IS NOT NULL THEN h2.total_amount = ?8 ELSE TRUE END " +
            ")"
            ,countQuery =
            "SELECT count(h.id) FROM bank_deposit_header h \n" +
            "WHERE \n" +
            "h.id IN (\n" +
            "   SELECT \n" +
            "   DISTINCT h2.id \n" +
            "   FROM bank_deposit_header h2  \n" +
            "   INNER JOIN bank_deposit_detail d ON\n" +
            "   h2.id = d.bank_deposit_header_id \n" +
            "   WHERE   \n" +
            "   h2.is_deleted = 0   \n" +
            "   AND h.branch_id = ?1\n" +
            "   AND CASE WHEN ?2 IS NOT NULL THEN h2.transaction_date BETWEEN ?2 AND ?3 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?4 IS NOT NULL THEN h2.deposit_number = ?4 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?5 IS NOT NULL THEN h2.to_account_id = ?5 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?6 IS NOT NULL THEN d.transaction_branch_id = ?6 ELSE TRUE END  \n" +
            "   AND CASE WHEN ?7 IS NOT NULL THEN d.transaction_id = ?7 ELSE TRUE END \n" +
            "   AND CASE WHEN ?8 IS NOT NULL THEN h2.total_amount = ?8 ELSE TRUE END " +
            ")"
            ,nativeQuery = true)
    Page<BankDepositHeader> searchCustomFilterBankDeposits(
            Date transactionFromDate, Date transactionToDate,
            String depositNumber,
            Long toAccountId,
            String transactionBranchId,
            String transactionId,
            Double amount,
            Long branchId,
            Pageable pageable
            //Long ResultOffset,
            //Integer ResultLimit
    );

    //-------------------------------Paginated----------------------------------------------------------------
}
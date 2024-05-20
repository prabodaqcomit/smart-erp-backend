package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.AccountTransfer;
import lk.quantacom.smarterpbackend.entity.ReceiptHeader;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.Date;
import java.util.List;

public interface AccountTransferRepository extends JpaRepository<AccountTransfer, Long>, JpaSpecificationExecutor<AccountTransfer> {


    @Query(value = "select transfer_number from account_transfer " +
            " where id = (select max(id) from account_transfer)", nativeQuery = true)
    String getMaximumReceiptNumber();

    List<AccountTransfer> findByIsDeleted(Deleted deleted, Sort sort);

    List<AccountTransfer> findByIsDeletedAndBranchId(Deleted deleted, Long branchId, Sort sort);


    @Query(value =
            "SELECT * FROM account_transfer h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND CASE WHEN ?1 IS NOT NULL THEN transfer_date BETWEEN ?1 AND ?2 ELSE TRUE END \n" +
                    "AND CASE WHEN ?3 IS NOT NULL THEN from_account_id = ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN to_account_id = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN amount = ?5 ELSE TRUE END \n" +
                    "ORDER BY h.id DESC"
            , nativeQuery = true)
    List<AccountTransfer> searchCustomFilter(
            Date transferFromDate, Date transferToDate,
            Long fromAccountId,
            Long toAccountId,
            Double amount
            );


    @Query(value =
            "SELECT * FROM account_transfer h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.branch_id = ?1 \n" +
                    "AND CASE WHEN ?2 IS NOT NULL THEN transfer_date BETWEEN ?2 AND ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN from_account_id = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN to_account_id = ?5 ELSE TRUE END \n" +
                    "AND CASE WHEN ?6 IS NOT NULL THEN amount = ?6 ELSE TRUE END \n" +
                    "ORDER BY h.id DESC"
            ,nativeQuery = true)
    List<AccountTransfer> searchCustomFilterBranch(
            Long branchId,
            Date transferFromDate, Date transferToDate,
            Long fromAccountId,
            Long toAccountId,
            Double amount
    );

}
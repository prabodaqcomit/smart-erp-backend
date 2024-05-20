package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.OwnChequeReturns;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface OwnChequeReturnsRepository extends JpaRepository<OwnChequeReturns,Long> , JpaSpecificationExecutor {


    List<OwnChequeReturns> findByIsDeleted(Deleted deleted);

    List<OwnChequeReturns> findByChequeNo(String chequeNo);

    List<OwnChequeReturns> findByChequeNoAndBranchId(String chequeNo,String branchId);

    @Query(value = "select c.payee_name from  own_cheque_returns o inner join cheque_issue_note c " +
            " on o.cheque_issue_note_id=c.id where  c.payee_name=?1 group by c.payee_name order by c.payee_name",nativeQuery = true)
    List<getByPayeeNameResponse> getByPayeeName(String payeeName);
    @Query(value = "select o.cheque_no from  own_cheque_returns o inner join  cheque_issue_note c  " +
            "on o.cheque_issue_note_id=c.id where o.cheque_no=?1 group by o.cheque_no order by o.cheque_no",nativeQuery = true)
    List<getByChequeNoResponse> getByChequeNo(String chequeNo);

    @Query(value = "select o.bank_code from  own_cheque_returns o inner join  cheque_issue_note c  " +
            "on o.cheque_issue_note_id=c.id where  o.bank_code=?1 group by o.bank_code order by o.bank_code",nativeQuery = true)
    List<getByBankCodeResponse> getByBankCode(String bankCode);

    @Query(value = "select o.branch_code from own_cheque_returns o inner join cheque_issue_note c on o.cheque_issue_note_id=c.id " +
            " where o.branch_code=?1 group by o.branch_code order by o.branch_code",nativeQuery = true)
    List<getByBranchCodeResponse> getByBranchCode(String BranchCode);


}
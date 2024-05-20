package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.getBankIdAndLadgerIdResponse;
import lk.quantacom.smarterpbackend.dto.response.getReceivedChequesAndCustomerByChequeNoResponse;
import lk.quantacom.smarterpbackend.entity.ChequeIssueNote;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface ChequeIssueNoteRepository extends JpaRepository<ChequeIssueNote,Long> , JpaSpecificationExecutor {

    List<ChequeIssueNote> findByIsDeleted(Deleted deleted);

    ChequeIssueNote findByChequeNo(String chequeNo);

    List<ChequeIssueNote> findByUpdateWindow(String updateWindow);

    List<ChequeIssueNote> findByPayeeName(String payeeName);

    List<ChequeIssueNote> findByAccName(String accName);

    List<ChequeIssueNote> findByBankCode(String bankCode);

    List<ChequeIssueNote> findByBranchCode(String branchCode);

    List<ChequeIssueNote> findByIsDeletedAndChequeNo(Deleted deleted,String chequeNo);

    @Query(value = "select b.acc_no as bacc_no from cheque_issue_note c inner join bank_account b on c.acc_name=b.acc_name   " +
            " where  b.acc_no=1 group by b.acc_no order by b.acc_no",nativeQuery = true)
    List<String> getByAccNo(String chequeNo);

    List<ChequeIssueNote> findByPayeeNameAndBranchCodeAndIssueDateBetween(String payeeName,String Branch,Date from,Date to);

    List<ChequeIssueNote> findByUpdateWindowAndBranchCodeAndIssueDateBetween(String updateWindow,String Branch,Date from,Date to);

    List<ChequeIssueNote> findByBankCodeAndBranchCodeAndIssueDateBetween(String bankCode,String Branch,Date from,Date to);

    List<ChequeIssueNote> findByChequeNoAndBranchCodeAndIssueDateBetween(String branchCode,String Branch,Date from,Date to);

    List<ChequeIssueNote> findByAccNameAndBranchCodeAndIssueDateBetween(String accName,String Branch,Date from,Date to);

    List<ChequeIssueNote> findByBranchCodeAndIssueDateBetween(String Branch,Date from,Date to);

    List<ChequeIssueNote> findByBranchCodeAndChequeDateBetween(String Branch,Date from,Date to);


}

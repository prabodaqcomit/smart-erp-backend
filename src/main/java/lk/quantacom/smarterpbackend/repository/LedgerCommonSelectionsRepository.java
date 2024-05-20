package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.CommonDebitAccountWithInvestmentResponse;
import lk.quantacom.smarterpbackend.entity.LedgerCashbookNotes;
import lk.quantacom.smarterpbackend.entity.LedgerCommonSelections;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LedgerCommonSelectionsRepository extends JpaRepository<LedgerCommonSelections,Integer> , JpaSpecificationExecutor {


    List<LedgerCommonSelections> findByFrameIdAndPayModeAndDescription(String frameId,String payMode,String description);

    List<LedgerCommonSelections> findByFrameIdAndPayModeAndDescriptionAndDebitAccNum(String frameId,String payMode,String description,String debitAccNum);

    @Query(value = "select a.id,a.acc_name from ledger_common_selections l inner join ladger_account a on l.debit_acc_id = a.id " +
            " where l.frame_id='M233' and l.pay_mode = '1' and l.description='open a new bank account with investment' and l.debit_acc_id=?1",nativeQuery = true)
    List<CommonDebitAccountWithInvestmentResponse> getCommonDebitAccountWithInvestment(@Param("debitAccId") Integer debitAccId);



}
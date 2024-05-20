package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.UnDepositedFundReferenceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.List;

public interface UnDepositedFundReferenceDetailRepository extends JpaRepository<UnDepositedFundReferenceDetail, Long>, JpaSpecificationExecutor<UnDepositedFundReferenceDetail> {


    List<UnDepositedFundReferenceDetail> findByIsDeleted(Deleted deleted);

    UnDepositedFundReferenceDetail findByUnDepositedFundReferenceHeaderIdAndLineNumber(Long headerId, Integer lineNumber);


    @Query(
            value = "SELECT \n" +
                    "NULL id, NULL created_by, NULL created_date_time, 0 is_deleted, NULL modified_by, NULL modified_date_time, \n" +
                    "NULL undeposited_fund_reference_header_id, \n" +
                    "ROW_NUMBER() OVER (ORDER BY pd.fld_InvPayDtlKey) line_number, \n" +
                    "pd.fld_PayMode payment_mode_id, \n" +
                    "1 currency_type_id, \n" +
                    "CASE \n" +
                    "   WHEN pd.fld_ExchngRate IS NULL THEN 1 \n" +
                    "   WHEN pd.fld_ExchngRate = 0 THEN 1 \n" +
                    "   ELSE pd.fld_ExchngRate \n" +
                    "END currency_type_exchange_rate, \n" +
                    "pd.fld_CrdCardNo payment_mode_remark, \n" +
                    "pd.fld_PayTypeAmt payment_mode_paid_amount, \n" +
                    "pd.fld_PayTypeAmt * (CASE  WHEN pd.fld_ExchngRate IS NULL THEN 1 WHEN pd.fld_ExchngRate = 0 THEN 1 ELSE pd.fld_ExchngRate END) paid_amount_value, \n" +
                    "pd.fld_PayTypeAmt undeposited_balance \n" +
                    "FROM \n" +
                    "tblpaydtl pd \n" +
                    "WHERE \n" +
                    "pd.fld_Location = 1? AND pd.fld_InvNo = 2? \n" +
                    "ORDER BY pd.fld_InvPayDtlKey ASC"
            ,nativeQuery = true
    )
    List<UnDepositedFundReferenceDetail> getUnSavedInvoiceUnDepositedFundDetails(String branchId, String transactionId);


    @Query(
            value = "SELECT \n" +
                    "NULL id, NULL created_by, NULL created_date_time, 0 is_deleted, NULL modified_by, NULL modified_date_time, \n" +
                    "NULL undeposited_fund_reference_header_id, \n" +
                    "ROW_NUMBER() OVER (ORDER BY rpm.id) line_number, \n" +
                    "rpm.payment_method_id payment_mode_id, \n" +
                    "rpm.currency_type_id currency_type_id, \n" +
                    "CASE  \n" +
                    "   WHEN rpm.currency_to_local_currency_rate IS NULL THEN 1 \n" +
                    "   WHEN rpm.currency_to_local_currency_rate = 0 THEN 1 \n" +
                    "   ELSE rpm.currency_to_local_currency_rate \n" +
                    "END currency_type_exchange_rate, \n" +
                    "rpm.reference payment_mode_remark, \n" +
                    "rpm.paid_amount payment_mode_paid_amount, \n" +
                    "rpm.paid_amount * (CASE  WHEN rpm.currency_to_local_currency_rate IS NULL THEN 1 WHEN rpm.currency_to_local_currency_rate = 0 THEN 1 ELSE rpm.currency_to_local_currency_rate END) paid_amount_value, \n" +
                    "rpm.paid_amount undeposited_balance \n" +
                    "FROM \n" +
                    "receipt_payment_method rpm \n" +
                    "WHERE \n" +
                    "rpm.branch_id = 1? AND rpm.receipt_header_id = 2? \n" +
                    "ORDER BY rpm.id ASC \n"
            ,nativeQuery = true
    )
    List<UnDepositedFundReferenceDetail> getUnSavedReceiptUnDepositedFundDetails(String branchId, String transactionId);


}
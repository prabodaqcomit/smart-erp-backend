package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Tblinvhed;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TblinvhedRepository extends JpaRepository<Tblinvhed, String>, JpaSpecificationExecutor {

    //
//    List<Tblinvhed> findByIsDeleted(Deleted deleted);
    @Query(value = "select max(fld_InvNo) FROM tblinvhed", nativeQuery = true)
    String getMaxId();

    Tblinvhed findByFldInvno(String fldInvno);

    Tblinvhed findByFldLocationAndFldInvno(String invoiceLocation, String invoiceNumber);

    @Query(
            value = "SELECT \n" +
                    "h.*\n" +
                    "FROM\n" +
                    "tblinvhed h\n" +
                    "INNER JOIN (\n" +
                    "\tSELECT \n" +
                    "\tpd.fld_Location,\n" +
                    "\tpd.fld_InvNo\n" +
                    "\tFROM \n" +
                    "\ttblpaydtl pd\n" +
                    "\tWHERE\n" +
                    "\tpd.fld_PayTypeCode = ?1 AND\n" + // Customer Code
                    "\t(\n" +
                    "\tpd.fld_PayMode = 11 \n" + //11 = Return invoice
                    "\tOR pd.fld_PayMode = 2 \n" + //2 = Credit invoice
                    "\t)\n" +
                    "\tGROUP BY pd.fld_Location, pd.fld_InvNo	\n" +
                    ") AS pd2 ON \n" +
                    "h.fld_Location = pd2.fld_Location AND h.fld_InvNo = pd2.fld_InvNo\n" +
                    "\tEXCEPT\n" +
                    "\t(\n" +
                    "\t\tSELECT \n" +
                    "\t\th.*\n" +
                    "\t\tFROM\n" +
                    "\t\ttblinvhed h\n" +
                    "\t\tINNER JOIN receipt_settle_invoice rsi ON\n" +
                    "\t\th.fld_Location = rsi.invoice_location AND h.fld_InvNo = rsi.invoice_number\n" +
                    "\t\tWHERE rsi.customer_id = ?1 \n" + // Customer Code
                    "\t\tAND rsi.balance_amount = 0\n" +
                    ")"
            ,nativeQuery = true
    )
    List<Tblinvhed> getPendingCreditInvoicesByCustomerId(Long customerId);

}
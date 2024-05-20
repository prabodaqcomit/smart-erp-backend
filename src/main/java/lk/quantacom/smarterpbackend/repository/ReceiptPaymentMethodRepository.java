package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.ReceiptHeader;
import lk.quantacom.smarterpbackend.entity.ReceiptPaymentMethod;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReceiptPaymentMethodRepository extends JpaRepository<ReceiptPaymentMethod, Long>, JpaSpecificationExecutor<ReceiptPaymentMethod> {

    List<ReceiptPaymentMethod> findByIsDeleted(Deleted deleted, Sort sort);

    List<ReceiptPaymentMethod> findByIsDeletedAndBranchId(Deleted deleted, Long branchId, Sort sort);

    List<ReceiptPaymentMethod> findByIsDeletedAndReceiptHeaderId(Deleted deleted, Long receiptHeaderId);

    @Modifying
    @Query(value = "DELETE FROM receipt_payment_method pm WHERE  pm.receipt_header_id = ?1", nativeQuery = true)
    int deleteReceiptPaymentsByHeaderId(Long headerId);
}
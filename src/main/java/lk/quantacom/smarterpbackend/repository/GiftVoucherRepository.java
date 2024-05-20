package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GiftVoucher;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface GiftVoucherRepository extends JpaRepository<GiftVoucher,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(gift_voucher_serial_no) from tblgiftvoucher",nativeQuery = true)
    String getMaxSRNO();

    List<GiftVoucher> findByIsDeleted(Deleted deleted);

    //used
    List<GiftVoucher> findByGiftVoucherInvoiceNoUsedIsNotNullAndIsDeleted(Deleted deleted);
    List<GiftVoucher> findByGiftVoucherInvoiceNoUsedIsNotNullAndIsDeletedAndGiftVoucherDateUsedBetween(Deleted deleted,Date from,Date to);

    //valid
    List<GiftVoucher> findByGiftVoucherExpiryAfterAndIsDeleted(Date today,Deleted deleted);
    List<GiftVoucher> findByGiftVoucherExpiryAfterAndIsDeletedAndGiftVoucherExpiryBetween(Date today,Deleted deleted,Date from,Date to);

    //sold
    // same as used
    List<GiftVoucher> findByGiftVoucherInvoiceNoUsedIsNotNullAndIsDeletedAndGiftVoucherDateSoldBetween(Deleted deleted,Date from,Date to);


}
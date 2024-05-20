package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.PromotionHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface PromotionHeaderRepository extends JpaRepository<PromotionHeader,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(promo_hed_code) from m_tblpromotion_header",nativeQuery = true)
    String getMaxCode();

    List<PromotionHeader> findByIsDeleted(Deleted deleted);

 }
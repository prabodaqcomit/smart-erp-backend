package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.PromotionHeader;
import lk.quantacom.smarterpbackend.entity.PromotionPayDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface PromotionPayDetailsRepository extends JpaRepository<PromotionPayDetails,Long> , JpaSpecificationExecutor {

    List<PromotionPayDetails> findByIsDeletedAndPromoHead(Deleted deleted, PromotionHeader promoHead);

    List<PromotionPayDetails> findByIsDeleted(Deleted deleted);

 }
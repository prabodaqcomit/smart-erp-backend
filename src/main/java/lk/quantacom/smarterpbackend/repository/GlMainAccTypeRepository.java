package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GlMainAccType;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface GlMainAccTypeRepository extends JpaRepository<GlMainAccType,Long> , JpaSpecificationExecutor {


    List<GlMainAccType> findByIsDeleted(Deleted deleted);

    List<GlMainAccType> findByIsDeletedAndCategoryId(Deleted deleted,Long categoryId);

}
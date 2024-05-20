package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> , JpaSpecificationExecutor {

    List<Category> findByIsDeleted(Deleted deleted);

    List<Category> findByIsDeletedAndIsMaterialCategory(Deleted deleted,Boolean isMaterialCategory);

    List<Category> findByCategoryName(String categoryName);

 }
package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.MenuItems;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MenuItemsRepository extends JpaRepository<MenuItems,Long> , JpaSpecificationExecutor {


    List<MenuItems> findByIsDeletedOrderByOrderNoAsc(Deleted deleted);


}
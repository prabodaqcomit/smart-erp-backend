package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GrnServiceItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GrnServiceItemsRepository extends JpaRepository<GrnServiceItems,Long> , JpaSpecificationExecutor {


 }
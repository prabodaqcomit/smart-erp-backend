package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ProfileTypeInfo;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProfileTypeInfoRepository extends JpaRepository<ProfileTypeInfo,Integer> , JpaSpecificationExecutor {


    List<ProfileTypeInfo> findByIsDeleted(Deleted deleted);


}
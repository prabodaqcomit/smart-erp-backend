package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.Profile;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Integer> , JpaSpecificationExecutor {


    List<Profile> findByIsDeleted(Deleted deleted);


}
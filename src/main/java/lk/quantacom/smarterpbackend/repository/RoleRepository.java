package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RoleRepository extends JpaRepository<Role,Long> , JpaSpecificationExecutor {



 }
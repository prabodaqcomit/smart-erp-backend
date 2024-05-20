package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Tblporsizes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TblporsizesRepository extends JpaRepository<Tblporsizes,Long> , JpaSpecificationExecutor {


    List<Tblporsizes> findByIsDeleted(Deleted deleted);

    Tblporsizes findByPorIdAndPorSizeId(String porId,Integer porSizeId);

    List<Tblporsizes> findByPorId(String porId);


}
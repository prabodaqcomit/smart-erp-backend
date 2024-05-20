package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Barcodes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface BarcodesRepository extends JpaRepository<Barcodes,Long> , JpaSpecificationExecutor {


    List<Barcodes> findByIsDeleted(Deleted deleted);

    List<Barcodes> findByBarcodeAndIsDeleted(String barcode,Deleted deleted);
}
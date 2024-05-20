package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BARCODES")
@Data
public class Barcodes extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String barcode;

    @Column(name = "ITEM_CODE", nullable = false, length = 25)
    private String itemCode;

}
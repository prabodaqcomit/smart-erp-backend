package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "MAIN_NAV_BUTTONS")
@Data
public class MainNavButtons extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "ROOT_TAB_NAME", length = 50, nullable = false)
    private String rootTabName;

    @Column(name = "ORDER_NO", nullable = false)
    private Integer orderNo;

    @Column(length = 50, nullable = false)
    private String caption;

    @Column(name = "ICON_HEX", length = 100)
    private String iconHex;

    @Column(name = "ICON_STYLE", length = 100)
    private String iconStyle;

    @Column(length = 100, nullable = false)
    private String rout;

}
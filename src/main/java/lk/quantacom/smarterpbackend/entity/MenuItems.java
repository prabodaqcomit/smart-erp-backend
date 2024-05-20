package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "MENU_ITEMS")
@Data
public class MenuItems extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "PARENT_ID", nullable = false)
    private Long parentId;

    @Column(length = 50)
    private String parentMenuName;

    @Column(name = "MENU_NAME", length = 50, nullable = false)
    private String menuName;

    @Column(name = "ICON_HEX", length = 50, nullable = false)
    private String iconHex;

    @Column(name = "ICON_STYLE", length = 100, nullable = false)
    private String iconStyle;

    @Column
    private String rout;

    @Column
    private Boolean isPopup=false;

    @Column
    private Integer orderNo;

}
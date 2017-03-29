package com.globex.model.entity.common;

import com.globex.model.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "alert")
public class Alert implements java.io.Serializable {

    @Id
    @Column(name = "ALERT_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
	private Integer alertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
	private User user;

    @Column(name = "FILE_ID", nullable = false)
	private int fileId;

    /*@Column(name = "ALERT_DATE")
	private Date alertDate;*/

    @Column(name = "INFORMATION", nullable = false, length = 50)
    private String information;

    @Column(name = "DETAILS", nullable = false)
    private String details;

    @Column(name = "STATUS", nullable = false)
    private int status;
}

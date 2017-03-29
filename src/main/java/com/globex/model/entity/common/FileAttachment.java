package com.globex.model.entity.common;

import com.globex.model.entity.common.File;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "file_attachment")
@Data
public class FileAttachment implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "FILE_ATTACHMENT_ID", unique = true, nullable = false)
	private Integer fileAttachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID", nullable = false)
	private File file;

    @Column(name = "FILE_NAME", nullable = false, length = 65535)
	private String fileName;
}

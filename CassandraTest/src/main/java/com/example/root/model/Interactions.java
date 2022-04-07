package com.example.root.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;



@Table
public @Data class Interactions implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Id
    private String id;
    //private String interactionName;
    private String interactionType;
    private String mediaType;
	@Column("content")
    private String content;
//    private Date startDate;
//    private Date endDate;
//    private String threadId;
//    private String parentId;
//    private String contactId;
//    private String startDateStr;
//    private String endDateStr;
}

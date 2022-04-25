package com.library.librayapi.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table
public class Book {
	
	@Id
	private String id;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String isbn;
	

	public void newId() {
		this.id = UUID.randomUUID().toString();
	}
	
}

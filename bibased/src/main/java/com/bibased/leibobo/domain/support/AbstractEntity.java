package com.bibased.leibobo.domain.support;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by boboLei on 2018/4/16.
 * 实现一个所有实体的父类
 */
@MappedSuperclass
@Data
public abstract class AbstractEntity implements Entity {

	@Id
	@GeneratedValue
	@Setter(AccessLevel.PROTECTED)
	@Column(name = "id")
	private Long id;

	@Column(name = "create_time", nullable = false, updatable = false)
	@Setter(AccessLevel.PRIVATE)
	private Date createTime;

	@Column(name = "update_time", nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Date updateTime;

	@Version
	@Column(name = "version", nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Integer version;

	protected AbstractEntity(){

	}

	protected AbstractEntity(Long id){
		setId(id);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void init() {
		this.version = 1;
	}

	@Override
	public int getVersion(){
		return this.version;
	}

	@Override
	public void validate(){
	}

	@PrePersist
	public void prePersist(){
		this.setCreateTime(new Date());
		this.setUpdateTime(new Date());
	}

	@PreUpdate
	public void preUpdate(){
		this.setUpdateTime(new Date());
	}
}

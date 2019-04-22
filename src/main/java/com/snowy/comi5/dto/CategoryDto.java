package com.snowy.comi5.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3774011701036857804L;
	
	private Long id;
	private String name;
	private String description;
	private Long parentId;
	private int dept;
	private CategoryDto parent;
	private List<CategoryDto> children;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public CategoryDto getParent() {
		return parent;
	}
	public void setParent(CategoryDto parent) {
		this.parent = parent;
	}
	public List<CategoryDto> getChildren() {
		return children;
	}
	public void setChildren(List<CategoryDto> children) {
		this.children = children;
	}
	
}

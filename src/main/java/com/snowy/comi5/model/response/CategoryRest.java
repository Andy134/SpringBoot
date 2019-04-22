package com.snowy.comi5.model.response;

import java.util.List;

public class CategoryRest {
	private Long id;
	private String name;
	private String description;
	private CategoryRest parent;
	private List<CategoryRest> children;
	
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
	public CategoryRest getParent() {
		return parent;
	}
	public void setParent(CategoryRest parent) {
		this.parent = parent;
	}
	public List<CategoryRest> getChildren() {
		return children;
	}
	public void setChildren(List<CategoryRest> children) {
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}

package com.snowy.comi5.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name="categories")
public class CategoryEntity extends Audit{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1094436035686758595L;

	@Id
    @GeneratedValue(generator = "category_generator")
    @SequenceGenerator(
            name = "category_generator",
            sequenceName = "category_sequence",
            initialValue = 1000
    )
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=true)
	private String description;
	
	@Column(nullable=true)
	private int dept;
	
	@ManyToOne
	@Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name="parent_id")
    private CategoryEntity parent;
   
    @OneToMany(mappedBy="parent")
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<CategoryEntity> children = new HashSet<CategoryEntity>();
    
    @OneToMany(mappedBy="categories")
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<PostEntity> posts = new HashSet<PostEntity>();
	
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
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public CategoryEntity getParent() {
		return parent;
	}
	public void setParent(CategoryEntity parent) {
		this.parent = parent;
	}
	public Set<CategoryEntity> getChildren() {
		return children;
	}
	public void setChildren(Set<CategoryEntity> children) {
		this.children = children;
	}
	public Set<PostEntity> getPosts() {
		return posts;
	}
	public void setPosts(Set<PostEntity> posts) {
		this.posts = posts;
	}
	
	
}

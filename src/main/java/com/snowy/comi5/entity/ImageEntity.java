package com.snowy.comi5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="images")
public class ImageEntity extends Audit{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4590600332710691440L;

	@Id
    @GeneratedValue(generator = "image_generator")
    @SequenceGenerator(
    		name = "image_generator",
            sequenceName = "image_sequence",
            initialValue = 1000
    )
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String source;
	
	@Column(nullable=true)
	private String alt;

	@OneToOne(fetch = FetchType.LAZY,
            mappedBy = "featureImage", optional = false)
    private PostEntity post;
	
	public PostEntity getPosts() {
		return post;
	}

	public void setPosts(PostEntity post) {
		this.post = post;
	}

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
	
	
}

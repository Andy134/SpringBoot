package com.snowy.comi5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.snowy.comi5.enums.PostStatusEnum;

@Entity(name="posts")
public class PostEntity extends Audit{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4535248648698242194L;

	@Id
    @GeneratedValue(generator = "post_generator")
    @SequenceGenerator(
            name = "post_generator",
            sequenceName = "post_sequence",
            initialValue = 1000
    )
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=true)
	private String subTitle;
	
	@Column(nullable=true)
	private String content;
	
	@Column(nullable=false)
	private PostStatusEnum status;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "feature_image", nullable = true)
	private ImageEntity featureImage;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryEntity categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PostStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PostStatusEnum status) {
		this.status = status;
	}

	public ImageEntity getFeatureImage() {
		return featureImage;
	}

	public void setFeatureImage(ImageEntity featureImage) {
		this.featureImage = featureImage;
	}

	public CategoryEntity getCategories() {
		return categories;
	}

	public void setCategories(CategoryEntity categories) {
		this.categories = categories;
	} 
	
}

package com.snowy.comi5.model.response;

import com.snowy.comi5.enums.PostStatusEnum;

public class PostRest {
	private Long id;
	private String title;
	private String subTitle;
	private String content;
	private PostStatusEnum status;
	private ImageRest featureImage;
	private CategoryRest category;
	
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
	
	public ImageRest getFeatureImage() {
		return featureImage;
	}
	public void setFeatureImage(ImageRest featureImage) {
		this.featureImage = featureImage;
	}
	public CategoryRest getCategoryRest() {
		return category;
	}
	public void setCategoryRest(CategoryRest categoryRest) {
		this.category = categoryRest;
	}
	
	
}

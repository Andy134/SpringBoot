package com.snowy.comi5.dto;

import java.io.Serializable;

import com.snowy.comi5.enums.PostStatusEnum;

public class PostDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2578271689283330307L;
	private Long id;
	private String title;
	private String subTitle;
	private String content;
	private PostStatusEnum status;
	
	private Long imgId;
	private Long categoryId;
	
	private ImageDto featureImage;
	private CategoryDto category;
	
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
	
	public CategoryDto getCategoryDto() {
		return category;
	}
	public void setCategoryDto(CategoryDto categoryDto) {
		this.category = categoryDto;
	}
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public ImageDto getFeatureImage() {
		return featureImage;
	}
	public void setFeatureImage(ImageDto featureImage) {
		this.featureImage = featureImage;
	}
	
}

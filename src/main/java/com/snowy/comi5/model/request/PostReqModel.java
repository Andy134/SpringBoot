package com.snowy.comi5.model.request;

import com.snowy.comi5.enums.PostStatusEnum;

public class PostReqModel {
	private String title;
	private String subTitle;
	private String content;
	private PostStatusEnum status;
	private Long imgId;
	private Long categoryId;
	
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
	
}

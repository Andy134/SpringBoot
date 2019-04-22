package com.snowy.comi5.utils;

public enum ErrorMessages {
	MISSING_REQUIRED_FIELD("Mising required field. Please check documentaion for the required fields"),
	RECORD_ALREADY_EXISTED("Record already existed"),
	INTERNAL_ERROR_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with provided id is not found"),
	AUTHENTICATION_FAILED("Authenticationfailed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"),
	COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
	OBJECT_NULL("Object is null"),
	
	// Category
	CAT_MAX_DEPT_CATEGORY_ERROR("Cannot create sub category exceed maximum dept."),
	CATEGORY_EXISTED("Category existed"),
	CATEGORY_NAME_REQUIRED("Category name required"),
	CATEGORY_NOT_FOUND("Category not found"),
	// Image
	IMAGE_NOT_FOUND("Image not found"),
	// Post
	POST_EXISTED("Post existed"),
	POST_NOT_FOUND("Post not found");
	
	private String errorMessages;

	private ErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

	/**
	 * @return the errorMessages
	 */
	public String getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @param errorMessages the errorMessages to set
	 */
	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}
	
}

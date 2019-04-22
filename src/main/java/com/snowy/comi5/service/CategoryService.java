package com.snowy.comi5.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snowy.comi5.dto.CategoryDto;
import com.snowy.comi5.entity.CategoryEntity;
import com.snowy.comi5.model.response.CategoryRest;

@Service
public interface CategoryService {
	public List<CategoryDto> getCategories(int page,int limit);
	public CategoryDto getCatById(Long id);
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto editCategory(Long id, CategoryDto categoryDto);
	public CategoryRest categoryFromDtoToRest(CategoryDto categoryDto);
	public CategoryDto categoryEntityToDto(CategoryEntity categoryEntity);
	public CategoryDto categoryEntityToDto(CategoryEntity categoryEntity, boolean displayChild);
	public CategoryEntity categoryDtoToEntity(CategoryDto categoryDto);
	
}

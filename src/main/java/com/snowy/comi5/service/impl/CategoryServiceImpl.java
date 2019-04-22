package com.snowy.comi5.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.snowy.comi5.dto.CategoryDto;
import com.snowy.comi5.entity.CategoryEntity;
import com.snowy.comi5.exceptions.UserServiceException;
import com.snowy.comi5.model.response.CategoryRest;
import com.snowy.comi5.repository.CategoryRepository;
import com.snowy.comi5.service.CategoryService;
import com.snowy.comi5.utils.ErrorMessages;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	private static final int MAX_DEPT_CATEGORY = 3;
	
	@Override
	public List<CategoryDto> getCategories(int page, int limit) {
		List<CategoryDto> returnValue = new ArrayList<>();
		
		// Set first page is 1.
		if(page > 0) page = page - 1;

		Pageable pageable = PageRequest.of(page, limit);
		Page<CategoryEntity> catPage = categoryRepository.findAll(pageable);
		List<CategoryEntity> cats = catPage.getContent();

		for (CategoryEntity catEntity : cats) {
			CategoryDto catDto = categoryEntityToDto(catEntity);
			returnValue.add(catDto);
		}
		return returnValue;
	}

	@Override
	public CategoryDto getCatById(Long id) {
		CategoryEntity catEntity = categoryRepository.findById(id).orElse(new CategoryEntity());

		if (catEntity == null || catEntity.getId() == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());

		return categoryEntityToDto(catEntity);
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		CategoryEntity startCat = categoryRepository.findByName(categoryDto.getName());
		if(startCat != null) throw new RuntimeException(ErrorMessages.CATEGORY_EXISTED.getErrorMessages());
		
		CategoryEntity catEntity = categoryDtoToEntity(categoryDto);
		
		CategoryEntity storedCategory = categoryRepository.save(catEntity);
		
		return categoryEntityToDto(storedCategory);
	}

	@Override
	public CategoryDto editCategory(Long id, CategoryDto categoryDto) {
		
		if(id == null || id < 1000) {
			throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		}
		if(categoryDto.getName() == null || "".equals(categoryDto.getName())) {
			throw new RuntimeException(ErrorMessages.CATEGORY_NAME_REQUIRED.getErrorMessages());
		}
		
		CategoryEntity catEntity = categoryDtoToEntity(categoryDto);
		catEntity.setId(id);
		
		CategoryEntity updatedCategory = categoryRepository.save(catEntity);

		return categoryEntityToDto(updatedCategory);
	}

	// Internal methods =======================================================
	@Override
	public CategoryEntity categoryDtoToEntity(CategoryDto categoryDto) {
		CategoryEntity returnValue = new CategoryEntity();
		BeanUtils.copyProperties(categoryDto, returnValue);
		
		// Get parent category and category dept
		CategoryEntity parentEnt = new CategoryEntity();
		if(categoryDto.getParentId()!=null) {
			parentEnt = categoryRepository.findById(categoryDto.getParentId()).orElse(new CategoryEntity());
		}
		
		if(parentEnt != null && parentEnt.getId() != null) {
			
			returnValue.setParent(parentEnt);
			returnValue.setDept(parentEnt.getDept()+1);
			
			if(MAX_DEPT_CATEGORY == categoryDto.getDept()) {
				throw new RuntimeException(ErrorMessages.CAT_MAX_DEPT_CATEGORY_ERROR.getErrorMessages());
			}
		}
		
		return returnValue;
	}
	@Override
	public CategoryDto categoryEntityToDto(CategoryEntity categoryEntity) {
		CategoryDto returnValue = new CategoryDto();
		returnValue.setId(categoryEntity.getId());
		returnValue.setName(categoryEntity.getName());
		returnValue.setDescription(categoryEntity.getDescription());
		
		// Set parent category
		if (categoryEntity.getParent() != null) {
			
			returnValue.setParentId(categoryEntity.getId());
			
			CategoryDto parentDto = new CategoryDto();
			parentDto.setId(categoryEntity.getParent().getId());
			parentDto.setName(categoryEntity.getParent().getName());
			parentDto.setDescription(categoryEntity.getParent().getDescription());
			returnValue.setParent(parentDto);
		}
		
		// Set list child category
		if(!categoryEntity.getChildren().isEmpty()) {
			List<CategoryDto> childrenDtoList = new ArrayList<CategoryDto>();
			//Iterator<CategoryEntity> iter = categoryEntity.getChildren().iterator();
			
			for (Iterator<CategoryEntity> iter = categoryEntity.getChildren().iterator(); iter.hasNext(); ) {
				CategoryEntity childrenEnt = (CategoryEntity) iter.next();
				CategoryDto childrenDto = new CategoryDto();
				childrenDto.setId(childrenEnt.getId());
				childrenDto.setName(childrenEnt.getName());
				childrenDto.setDescription(childrenEnt.getDescription());
				childrenDtoList.add(childrenDto);
			}
			returnValue.setChildren(childrenDtoList);
		}
		
		return returnValue;
	}
	
	/**
	 *  Get category without child category and parent category
	 *  param1: source entity
	 *  param2: flag determine do not get child
	 * */
	@Override
	public CategoryDto categoryEntityToDto(CategoryEntity categoryEntity, boolean displayChildCatrgory) {
		CategoryDto returnValue = new CategoryDto();
		returnValue.setId(categoryEntity.getId());
		returnValue.setName(categoryEntity.getName());
		returnValue.setDescription(categoryEntity.getDescription());
		
		return returnValue;
	}
	
	@Override
	public CategoryRest categoryFromDtoToRest(CategoryDto categoryDto) {
		CategoryRest returnValue = new CategoryRest();
		returnValue.setId(categoryDto.getId());
		returnValue.setName(categoryDto.getName());
		returnValue.setDescription(categoryDto.getDescription());
		
		if(categoryDto.getParent() != null) {
			CategoryRest parentCategory = new CategoryRest();
			parentCategory.setId(categoryDto.getParent().getId());
			parentCategory.setName(categoryDto.getParent().getName());
			parentCategory.setDescription(categoryDto.getParent().getDescription());
			returnValue.setParent(parentCategory);
		}
		
		if(categoryDto.getChildren() != null) {
			List<CategoryRest> childs = new ArrayList<CategoryRest>();
			
			
			for(CategoryDto child : categoryDto.getChildren()) {
				CategoryRest childCategory = new CategoryRest();
				childCategory.setId(child.getId());
				childCategory.setName(child.getName());
				childCategory.setDescription(child.getDescription());
				childs.add(childCategory);
			}
			
			returnValue.setChildren(childs);
		}
		
		return returnValue;
	}
}

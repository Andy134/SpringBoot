package com.snowy.comi5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snowy.comi5.dto.CategoryDto;
import com.snowy.comi5.model.request.CategoryReqModel;
import com.snowy.comi5.model.response.CategoryRest;
import com.snowy.comi5.service.CategoryService;
import com.snowy.comi5.utils.ErrorMessages;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	List<CategoryRest> getCategories(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="25") int limit){
		List<CategoryRest> returnValue = new ArrayList<>();
		
		List<CategoryDto> categories = categoryService.getCategories(page, limit);
		
		for(CategoryDto catDto : categories) {
			CategoryRest catModel = categoryService.categoryFromDtoToRest(catDto);
			returnValue.add(catModel);
		}
		
		return returnValue;
	}
	
	@GetMapping(path = "/{id}", 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public CategoryRest getCategory(@PathVariable Long id) {
		CategoryDto catDto = categoryService.getCatById(id);
		return categoryService.categoryFromDtoToRest(catDto);
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public CategoryRest createCategory(@RequestBody CategoryReqModel catReq) throws Exception { // Registration screen
		
		if(catReq.getName().isEmpty()) throw new NullPointerException("The object is null");

		CategoryDto catDto = new CategoryDto();
		BeanUtils.copyProperties(catReq, catDto);

		CategoryDto createdCategory = categoryService.createCategory(catDto);

		return categoryService.categoryFromDtoToRest(createdCategory);
	}
	
	@PutMapping(
			path = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public CategoryRest editCategory(@PathVariable Long id, @RequestBody CategoryReqModel catReq) throws Exception {
		
		if(catReq.getName().isEmpty()) throw new NullPointerException(ErrorMessages.OBJECT_NULL.getErrorMessages());
		
		CategoryDto catDto = new CategoryDto();
		BeanUtils.copyProperties(catReq, catDto);
		
		CategoryDto createdCategory = categoryService.editCategory(id, catDto);
		
		return categoryService.categoryFromDtoToRest(createdCategory);
		
	}
	
}

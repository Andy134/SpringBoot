package com.snowy.comi5.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.snowy.comi5.dto.CategoryDto;
import com.snowy.comi5.dto.PostDto;
import com.snowy.comi5.entity.CategoryEntity;
import com.snowy.comi5.entity.ImageEntity;
import com.snowy.comi5.entity.PostEntity;
import com.snowy.comi5.model.response.CategoryRest;
import com.snowy.comi5.model.response.PostRest;
import com.snowy.comi5.repository.CategoryRepository;
import com.snowy.comi5.repository.ImageRepository;
import com.snowy.comi5.repository.PostRepository;
import com.snowy.comi5.service.CategoryService;
import com.snowy.comi5.service.PostService;
import com.snowy.comi5.utils.ErrorMessages;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CategoryRepository catRepository;

	@Autowired
	CategoryService catService;

	@Autowired
	ImageRepository imageRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto getPostById(Long id) {
		PostEntity postEntity = postRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(ErrorMessages.POST_NOT_FOUND.getErrorMessages()));
		PostDto postDto = postFromEntityToDto(postEntity);

		return postDto;
	}
	
	@Override
	public PostDto editPost(PostDto postDto, long id) {
		postRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(ErrorMessages.POST_NOT_FOUND.getErrorMessages()));

		// PostEntity postEntity = categoryDtoToEntity(categoryDto);
		PostEntity postEntity = new PostEntity();
		
		BeanUtils.copyProperties(postDto, postEntity);
		postEntity.setId(id);

		if (postDto.getCategoryId() != null) {
			CategoryEntity categoryEntity = catRepository.findById(postDto.getCategoryId())
					.orElseThrow(() -> new RuntimeException(ErrorMessages.CATEGORY_NOT_FOUND.getErrorMessages()));
			postEntity.setCategories(categoryEntity);
		}

		if (postDto.getImgId() != null) {
			ImageEntity imageEntity = imageRepository.findById(postDto.getImgId())
					.orElseThrow(() -> new RuntimeException(ErrorMessages.IMAGE_NOT_FOUND.getErrorMessages()));
			postEntity.setFeatureImage(imageEntity);
		}

		PostEntity stored = postRepository.save(postEntity);

		PostDto returnValue = new PostDto();
		BeanUtils.copyProperties(stored, returnValue);

		return returnValue;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		PostEntity start = postRepository.findByTitle(postDto.getTitle());
		if (start != null)
			throw new RuntimeException(ErrorMessages.POST_EXISTED.getErrorMessages());

		// PostEntity postEntity = categoryDtoToEntity(categoryDto);
		PostEntity postEntity = new PostEntity();

		BeanUtils.copyProperties(postDto, postEntity);

		if (postDto.getCategoryId() != null) {
			CategoryEntity categoryEntity = catRepository.findById(postDto.getCategoryId())
					.orElseThrow(() -> new RuntimeException(ErrorMessages.CATEGORY_NOT_FOUND.getErrorMessages()));
			postEntity.setCategories(categoryEntity);
		}

		if (postDto.getImgId() != null) {
			ImageEntity imageEntity = imageRepository.findById(postDto.getImgId())
					.orElseThrow(() -> new RuntimeException(ErrorMessages.IMAGE_NOT_FOUND.getErrorMessages()));
			postEntity.setFeatureImage(imageEntity);
		}

		PostEntity stored = postRepository.save(postEntity);

		PostDto returnValue = new PostDto();
		BeanUtils.copyProperties(stored, returnValue);

		return returnValue;
	}

	@Override
	public List<PostDto> getPosts(int page, int limit) {
		List<PostDto> returnValue = new ArrayList<PostDto>();

		if (page > 0)
			page = page - 1;

		Pageable pageable = PageRequest.of(page, limit);
		Page<PostEntity> items = postRepository.findAll(pageable);
		List<PostEntity> posts = items.getContent();

		for (PostEntity postEntity : posts) {
			PostDto postDto = postFromEntityToDto(postEntity);
			returnValue.add(postDto);
		}
		return returnValue;
	}

	@Override
	public PostDto postFromEntityToDto(PostEntity postEntity) {
		PostDto postDto = new PostDto();
		BeanUtils.copyProperties(postEntity, postDto);

		if (postEntity.getCategories() != null) {
			CategoryDto categoryDto = catService.categoryEntityToDto(postEntity.getCategories(), true);
			postDto.setCategoryDto(categoryDto);
		}

		/*
		 * if (postDto.getImgId() != null) { ImageEntity imageEntity =
		 * imageRepository.findById(postDto.getImgId()) .orElseThrow(() -> new
		 * RuntimeException(ErrorMessages.IMAGE_NOT_FOUND.getErrorMessages()));
		 * postEntity.setFeatureImage(imageEntity); }
		 */
		return postDto;
	}

	@Override
	public PostRest postFromDtoToRest(PostDto postDto) {
		PostRest postRest = new PostRest();
		BeanUtils.copyProperties(postDto, postRest);

		if (postDto.getCategoryDto() != null) {
			CategoryRest categoryRest = catService.categoryFromDtoToRest(postDto.getCategoryDto());
			postRest.setCategoryRest(categoryRest);
		}

		return postRest;
	}


	@Override
	public void deletePost(Long id) {
		PostEntity postEntity = findPostById(id);
		postRepository.delete(postEntity);
	}
	
	// Internal method
	private PostEntity findPostById(Long id) {
		return postRepository.findById(id)
		.orElseThrow(() -> new RuntimeException(ErrorMessages.POST_NOT_FOUND.getErrorMessages()));
	}

}

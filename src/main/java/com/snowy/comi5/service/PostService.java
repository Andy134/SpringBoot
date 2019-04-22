package com.snowy.comi5.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.snowy.comi5.dto.PostDto;
import com.snowy.comi5.entity.PostEntity;
import com.snowy.comi5.model.response.PostRest;

@Service
public interface PostService extends UserDetailsService{
	List<PostDto> getPosts(int page, int limit);
	PostDto getPostById(Long id);
	PostDto createPost(PostDto postDto);
	PostDto editPost(PostDto postDto, long id);
	
	PostRest postFromDtoToRest(PostDto postDto);
	PostDto postFromEntityToDto(PostEntity postEntity);
	void deletePost(Long id);
	
}

package com.snowy.comi5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snowy.comi5.dto.PostDto;
import com.snowy.comi5.model.request.PostReqModel;
import com.snowy.comi5.model.response.OperationStatusModel;
import com.snowy.comi5.model.response.PostRest;
import com.snowy.comi5.service.PostService;
import com.snowy.comi5.utils.RequestOperationName;
import com.snowy.comi5.utils.RequestOperationStatus;

@RestController
@RequestMapping("posts")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping(
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	List<PostRest> getPosts(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="25") int limit){
		List<PostRest> returnValue = new ArrayList<>();
		
		List<PostDto> posts = postService.getPosts(page, limit);
		
		for(PostDto postDto : posts) {
			PostRest postModel = postService.postFromDtoToRest(postDto);
			returnValue.add(postModel);
		}
		BeanUtils.copyProperties(posts, returnValue);
		
		return returnValue;
	}
	
	@GetMapping(path = "/{id}", 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public PostRest getPost(@PathVariable Long id) {
		PostDto postDto = postService.getPostById(id);
		return postService.postFromDtoToRest(postDto);
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	PostRest createCategory(@RequestBody PostReqModel postReq) throws Exception { // Registration screen
		
		if(postReq.getTitle().isEmpty()) throw new NullPointerException("The title is empty");
		
		PostDto postDto = new PostDto();
		BeanUtils.copyProperties(postReq, postDto);

		PostDto createdPost = postService.createPost(postDto);
		
		PostRest returnValue = new PostRest();
		BeanUtils.copyProperties(createdPost, returnValue);

		return returnValue;
	}
	
	@PutMapping(
			path = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	PostRest createCategory(@PathVariable Long id, @RequestBody PostReqModel postReq) throws Exception { // Registration screen
		
		if(postReq.getTitle().isEmpty()) throw new NullPointerException("The title is empty");
		
		PostDto postDto = new PostDto();
		BeanUtils.copyProperties(postReq, postDto);

		PostDto createdPost = postService.editPost(postDto, id);
		
		PostRest returnValue = new PostRest();
		BeanUtils.copyProperties(createdPost, returnValue);

		return returnValue;
	}
	
	@DeleteMapping(
			path = "/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public OperationStatusModel deleteUser(@PathVariable Long id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		postService.deletePost(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
}

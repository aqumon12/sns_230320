package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.dao.PostMapper;
import com.sns.post.dao.PostRepository;
import com.sns.post.entity.PostEntity;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostMapper postMapper;
	
	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public String getLoginid() {
		return postMapper.selectLoginId();
	}
}

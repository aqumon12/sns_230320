package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
	public List<Map<String, Object>> selectPostList();
	
	public String selectLoginId();
	
	public void deletePostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
}

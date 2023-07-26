package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
//	public int selectLikeCountByPostIdUserId(
//			@Param("postId") int postId,
//			@Param("userId") int userId);
	
//	public int selectLikeCountByPostId(int postId);
	
	// by postId userId, by postId  => 하나의 쿼리로 합친다.
	public int selectLikeCountPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deleteLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
}

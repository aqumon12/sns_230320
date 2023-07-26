package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	public void likeToggle(int postId, int userId) {
		if (likeMapper.selectLikeCountPostIdOrUserId(postId, userId) > 0) {
			likeMapper.deleteLike(postId, userId);
		} else {
			likeMapper.insertLike(postId, userId);
		}
	}
	
	// input: postId
	// output: 좋아요 개수(int)
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountPostIdOrUserId(postId, null);
	}
	
	// input: postId, userid
	// output: 좋아요 여부(boolean)
	public boolean filledLike(int postId, Integer userId) {
		// 비로그인
		if (userId == null) {
			return false;
		}
		
		// 로그인
		return likeMapper.selectLikeCountPostIdOrUserId(postId, userId) > 0;
		
	} 
}

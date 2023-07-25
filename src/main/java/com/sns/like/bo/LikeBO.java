package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	public void likeToggle(int postId, int userId) {
		if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
			likeMapper.deleteLike(postId, userId);
		} else {
			likeMapper.insertLike(postId, userId);
		}
	}
	
	public int CountLike(int postId) {
		
		return likeMapper.selectLikeCountByPostId(postId);
	}
	
	public boolean likeOrNot(int postId, int userId) {
		if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
			return true;
		} else {
			return false;
		}
	} 
}

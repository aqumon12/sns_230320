package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Data;

// 댓글 1개. comment와 1:1 매핑
@Data
public class CommentView {
	 // 댓글 1개
	private Comment comment;
	
	// 댓글쓴이
	private UserEntity user;
}

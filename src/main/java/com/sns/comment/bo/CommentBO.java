package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentMapper;
import com.sns.comment.domain.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public int addComment(int userId, int postId, String content) {
		return commentMapper.insertComment(userId, postId, content);
	}
	
	public List<Comment> getCommentList() {
		return commentMapper.selectCommentList();
	}
	
	
}

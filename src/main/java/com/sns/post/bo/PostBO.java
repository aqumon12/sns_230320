package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostMapper;
import com.sns.post.dao.PostRepository;
import com.sns.post.entity.PostEntity;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManagerService fileManager;
	
	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public String getLoginid() {
		return postMapper.selectLoginId();
	}
	
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null;
		
		// 이미지가 있으면 업로드 후 imagePath를 받아옴
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());

	}
	
	public void deletePostByPostIdUserId(int postId, int userId) {
		// 글 불러오기
		PostEntity post = postRepository.findByIdAndUserId(postId, userId);
		if (post == null) {
			logger.warn("###[글 삭제] post is null. postId:{}, userId{}", postId, userId);
			return;
		}
		// 그림있으면, 그림삭제
		if (post.getImagePath() != null) {
			fileManager.deleteFile(post.getImagePath());
		}
		// 댓글삭제
		commentBO.deleteCommentByPostId(postId);
		// 좋아요삭제
		likeBO.deleteLikeByPostId(postId);
		// 글 삭제
		postMapper.deletePostByPostIdUserId(postId, userId);
	}
}

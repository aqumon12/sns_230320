package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class TimelineBO {

	@Autowired
	private PostBO postBO;

	@Autowired
	private UserBO userBO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// input: X
	// output: List<CardView>
	public List<CardView> generateCardViewList(int userId) {
		List<CardView> cardViewList = new ArrayList<>();

		// 글 목록 가져오기
		List<PostEntity> postList = postBO.getPostList();
		// 글 목록 반복문 순회
		// postEntity => cardView => cardViewList 에 담는다.
		for (PostEntity post : postList) {
			// post에 대응되는 하나의 카드를 만든다.
			CardView card = new CardView();
			
			// 글을 세팅한다.
			card.setPost(post);
			
			// 글쓴이를 세팅한다.
			UserEntity user = userBO.getUserEntityById(post.getUserId());
			card.setUser(user);
			
			// 댓글들을 세팅한다.
			List<CommentView> commentList = commentBO.generateCommentViewList(post.getId());
			card.setCommentList(commentList);
			
			// 좋아요 개수
			int count = likeBO.CountLike(post.getId());
			card.setLikeCount(count);
			
			// 좋아요 눌렀는지 여부
			boolean likeOrNot = likeBO.likeOrNot(post.getId(), userId);
			card.setFilledLike(likeOrNot);
			
			// **** cardViewList에 담는다.
			cardViewList.add(card);
		}

		return cardViewList;
	}
}

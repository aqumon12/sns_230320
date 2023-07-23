package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@RequestMapping("/timeline")
@Controller	
public class TimelineController {
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/timeline_view")
	public String timelineView(HttpSession session, Model model) {
		// commentList => model
		List<Comment> commentList = commentBO.getCommentList();
		
		// postList (jpa로 가져오기)
		List<PostEntity> postList = postBO.getPostList();
		
		
		// user 정보
		List<UserEntity> userList = userBO.getUserList();
		
		model.addAttribute("userList", userList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("postList", postList);
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
}

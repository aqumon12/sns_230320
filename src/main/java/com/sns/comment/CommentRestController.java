package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;
	// 확인 후 POST로 변경
	@GetMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session, Model model) {
		
		Map<String, Object> result = new HashMap<>();
		int userId = (int)session.getAttribute("userId");
		
		String userLoginId = (String)session.getAttribute("loginId");
		
		int a = commentBO.addComment(userId, postId, userLoginId, content);
		return result;
	}
}

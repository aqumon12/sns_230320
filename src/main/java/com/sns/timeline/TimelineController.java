package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/timeline_view")
	public String timelineView(HttpSession session, Model model) {
		// postList (jpa로 가져오기)
		List<PostEntity> postList = postBO.getPostList();
		model.addAttribute("postList", postList);
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
}

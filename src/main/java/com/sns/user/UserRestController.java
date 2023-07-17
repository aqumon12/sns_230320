package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.user.bo.UserBO;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping("is_duplicated_id")
	public Map<String, Object> isDuplicated(
			@RequestParam("loginId") String loginId){
		
		// select
		userBO.getUserEntityByLoginId(loginId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("isDuplicatedId", true);
		return result;
	}
 }

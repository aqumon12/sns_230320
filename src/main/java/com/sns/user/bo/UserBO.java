package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.user.dao.UserRepository;
import com.sns.user.entity.UserEntity;

@Service
public class UserBO {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FileManagerService fileManager;

	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}

	public Integer addUser(String loginId, String password, String name, String email, MultipartFile file) {
		String imagePath = null;
		
		// 이미지가 있으면 업로드 후 imagePath 받아옴
		if (file != null) {
			imagePath = fileManager.saveFile(loginId, file);
		}
		
		UserEntity userEntity = userRepository
				.save(UserEntity.builder()
						.loginId(loginId)
						.password(password)
						.name(name)
						.email(email)
						.profileImagePath(imagePath)
						.build());
		return userEntity == null ? null : userEntity.getId();
	}
	
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
	
	
	public UserEntity getUserEntityById(int id) {
		return userRepository.findById(id).orElse(null);
	}
}

package com.sns.post.domain;

import java.time.ZonedDateTime;

import lombok.Data;

@Data // getter, setter
public class Post {
	private int id;
	private int userId;
	private String content;
	private String imagePath;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}	

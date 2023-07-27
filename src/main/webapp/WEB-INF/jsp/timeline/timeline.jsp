<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>

			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한것처럼 효과를 준다 --%>
					<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="d-none">
					
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
					
					<%-- 업로드 된 임시 파일 이름 저장되는 곳--%>
					<div id="fileName" class="ml-2"></div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		<%--// 글쓰기 영역 끝 --%>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
		
		<c:forEach items="${cardList}" var="card">
		
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">${card.user.loginId}</span>

					<%-- 더보기 ... -> 내가 쓴 글일 때만 노출 --%>
					<c:if test="${userId eq card.post.userId}">
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
					</c:if>
				</div>

				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imagePath}" class="w-100" height="600" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<%-- 좋아요가 눌려져 있지 않을때 or 비로그인 => 빈 하트 --%>
					<c:if test="${card.filledLike == false}">
						<a href="#" class="like-btn" data-post-id="${card.post.id}">
							<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="filled heart">
						</a>
					</c:if>
					<%-- 좋아요가 눌려져 있을때 => 빈 하트 --%>
					<c:if test="${card.filledLike}">
						<a href="#" class="like-btn" data-post-id="${card.post.id}">
							<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18" height="18" alt="empty heart">
						</a>
					</c:if>
					좋아요 ${card.likeCount}개
				</div>

				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${card.user.loginId}</span>
					<span>${card.post.content}</span>
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>

				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<%-- 댓글 내용들 --%>
					<c:forEach items="${card.commentList}" var="commentView">
					<div class="card-comment m-1">
						<span class="font-weight-bold">${commentView.user.loginId}</span>
						<span>${commentView.comment.content}</span>
						
						<%-- 댓글 삭제 버튼 - 로그인 된 사람의 댓글의 댓글일 때 삭제 버튼 노출--%>
						<c:if test="${commentView.comment.userId eq userId}">
						<a href="#" class="comment-del-btn" data-comment-id="${commentView.comment.id}">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
						</a>
						</c:if>
					</div>
					</c:forEach>
									
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
					</div>
				</div> <%--// 댓글 목록 끝 --%>
			</div> <%--// 카드1 끝 --%>
		</c:forEach>
		</div> <%--// 타임라인 영역 끝  --%>
	</div> <%--// contents-box 끝  --%>
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<%-- modal-sm/lg/xl: 모달 사이즈 조절 --%>
	<%-- midal-dialog-center: 모달창을 수직 기준 가운데에 위치 --%>
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content text-center">
			<div class="py-3 border-bottom">
				<a href="#" id="deletePostBtn">삭제하기</a>
			</div>
			<div class="py-3 border-bottom">
				<a href="/post/post_update_view" id="updatePostBtn">수정하기</a>
			</div>
			<div class="py-3">
				<a href="#" data-dismiss="modal">취소하기</a>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	// 파일이미지 클릭 => 숨겨져 있는 type="file"을 동작시킨다.
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // a태그의 스크롤 올라가는 현상 방지
		$('#file').click(); // input file을 클릭한 것과 같은 효과
	});
	
	// 사용자가 이미지를 선택하는 순간 유효성 확인 및 업로드된 파일명 노출
	$('#file').on('change', function(e) {
		let fileName = e.target.files[0].name; // 20230513_114155.jpg
		console.log(fileName);
		
		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		// alert(ext);
		if (ext != "jpg" && ext != "png" && ext != "gif" && ext != "jpeg") {
			alert("이미지 파일만 업로드할 수 있습니다.");
			$('#file').val(""); // 파일 태그에 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의할 것!!)
			$('#fileName').text(''); // 파일 이름 비우기
			return;
		}
		
		// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
		$('#fileName').text(fileName);
	});
	
	// 글쓰기
	$('#writeBtn').on('click', function() {
		let content = $('#writeTextArea').val();
		let file = $('#file').val();
		if (content.length < 1) {
			alert("글 내용을 입력해주세요");
			return;
		}
		if (file == '') {
			alert('파일을 업로드 해주세요');
			return;
		}
		
		// 파일이 업로드 된 경우 확장자 체크
		let ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
		if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
			alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
			$('#file').val(''); // 파일을 비운다.
			return;
		}
		
		let formData = new FormData();
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]); // $('#file')[0]은 첫번째 input file 태그를 의미, files[0]는 업로드된 첫번째 파일
		
		// AJAX
		$.ajax({
			type:'post'
			, url:'/post/create'
			, data: formData
			, enctype: "multipart/form-data"    // 파일 업로드를 위한 필수 설정
			, processData: false    // 파일 업로드를 위한 필수 설정
			, contentType: false    // 파일 업로드를 위한 필수 설정
			, success: function(data) {
				if (data.code == 1) {
					location.reload();
				} else if (data.code == 500) { // 비로그인 일 때
					alert(data.errorMessage);
					location.href = "/user/sign_in_view";
				}
			}
			, error: function(e) {
				alert("글 저장에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	
	
	
	// 댓글 작성
	$('.comment-btn').on('click', function() {
		// alert('111');
		let postId = $(this).data('post-id');
		// alert(postId);
		
		// 댓글 내용 가져오기
		let content = $(this).prev().val().trim();
		// let content = $(this).siblings('input').val().trim();
		// alert(content);		
		$.ajax({
			type:"post"
			, url:"/comment/create"
			, data:{"postId":postId, "content": content}
		
			, success:function(data) {
				if (data.code == 1) {
					location.reload();
				} else if (data.code == 300) {
					alert(data.errorMessage);
					location.href= "/user/sign_in_view";
				} else {
					alert(data.errorMessage);
					return;
				}
			}
		});
	});
	
	// 댓글삭제
	$('.comment-del-btn').on('click', function(e) {
		// 댓글삭제버튼에 comment-id 담아서 ajax로 넘기기
		e.preventDefault();
		let commentId = $(this).data('comment-id');
		// alert(commentId);
		
		$.ajax({
			type:"delete"
			, url:"/comment/delete"
			, data:{"commentId":commentId}
			, success:function(data) {
				if (data.code == 1) {
					location.reload(true);
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("댓글 삭제 실패했습니다.");
			}
		});
	});
	
	// 좋아요 토글
	$('.like-btn').on('click', function(e) {
		e.preventDefault();
		// 로그인 체크
		/* let userId = "${userId}";
		alert(userId); */
		
		let postId = $(this).data('post-id');
		
		$.ajax({
			url:"/like/" + postId
			, success:function(data) {
				if (data.code == 1) {
					location.reload(true);
				} else if (data.code == 300) {
					alert(data.errorMessage);
					location.href = "/user/sign_in_view";
				}
			}
			, error:function(request, status, error) {
				alert("좋아요/해제에 실패했습니다.");
			}
		})
	})
	
	// 글 삭제(... 더보기 버튼 클릭) => 모달 띄우기
	$('.more-btn').on('click', function(e) {
		e.preventDefault(); // a태그 위로 올라감 방지
		
		let postId = $(this).data('post-id'); // getting
		// alert(postId);
		
		// 한개의 모달 태그에(재활용) data-post-id를 심어줌
		$('#modal').data('post-id', postId);  // setting
	});
	
	// 모달 안에 있는 삭제하기 클릭 => 진짜 삭제
	$('#modal #deletePostBtn').on('click', function(e) {
		e.preventDefault();
		
		let postId = $('#modal').data('post-id');
		//alert(postId);
		
		$.ajax({
			type:"delete"
			, url:"/post/delete"
			, data:{"postId":postId}
			, success:function(data) {
				if (data.code == 1) {
					alert("글을 삭제했습니다!")
					location.reaload(true);
				}
			}
			, error:function(request, status, error) {
				alert("글 삭제에 실패했습니다.")
			}
		});
	});
	
});
</script>
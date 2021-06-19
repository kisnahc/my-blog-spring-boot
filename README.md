# Blog Project

# 기능구현
————————————
# 회원 (Member)
### 회원가입
	email (unique)
	username
	age

### 회원 조회
	findMembers(전체 회원 조회)
	findById(회원 조회)

### 회원 수정은 email만 가능하다.

### 회원 삭제
	deleteByEmail.


# 게시판(Board)
### 게시글 목록
	게시글들의 목록을  보여줌.
		postId 	title		author	createDate	lastModifiedDate


# 게시글(Post)
### 게시글 등록
	게시글 등록은 회원가입이 된 유저만 가능.
		title	
		author
		content
		createDate
		lastModifiedDate
		commentList(Page)	  

### 게시글 수정은 제목과 내용만 수정이 가능하고 작성자만 수정가능.

### 게시글 조회
	findPostByTitle(제목으로 조회)
	findPostsByAuthor(작정사로 조회)

###  게시글 삭제
	작성자가 등록한 게시글만 삭제 가능.


#  댓글(Comment)
### 댓글 등록
	댓글 작성은 회원가입이 된 유저만 가능.
		author
		content
		createDate
		lastModifiedDate

### 댓글 수정
	댓글을 내용만 수정가능.

### 댓글 삭제
	작성자가 등록한 댓글만 삭제 가능.

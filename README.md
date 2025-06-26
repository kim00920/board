# 프로젝트 소개
- Spring Boot 기반 게시글 REST API 입니다
- Spring Boot + JPA 로 구현했습니다.
- 개발 기간 : 25.04.11 ~ 25.04.30
- 참여 인원 : 1명

# 기술 스택
- Language : Java
- JDK : 17
- Framework : Spring 6.2.5 , SpringBoot 3.4.4
- Library :  Spring Security 6.4.4, Query DSL 5.1.0
- BuildTool : Gradle
- DB : MySQL
- File : AWS S3
- Server : AWS EC2, Linux
- CI / CD : Docker, Docker Hub


# ERD
<img src="https://github.com/user-attachments/assets/3f6d3aba-6ce7-4f95-868c-50837a9353fd"/><br>
<img src="https://github.com/user-attachments/assets/ec176a48-4031-43fd-9e33-cf159921642a"/><br>


# 프로젝트 아키텍쳐
<img src="https://github.com/user-attachments/assets/15f01586-91a7-40da-8106-a680c00b258d" width="550" height="300"/>


# API 설계

- 회원<br>
<img src="https://github.com/user-attachments/assets/a402e034-f088-4def-8174-c14a2b010d5f"/><br>


- 게시글<br>
  <img src="https://github.com/user-attachments/assets/4bc8b2b1-4fef-40ca-82b2-c684bbba42a7"/><br>


- 댓글<br>
  <img src="https://github.com/user-attachments/assets/349bb5a8-a5e7-4147-9896-00186b245b9e"/><br>


- 대댓글<br>
  <img src="https://github.com/user-attachments/assets/d7f505a0-486f-47a6-bf6c-99021d8e5470"/><br>


- 카테고리<br>
  <img src="https://github.com/user-attachments/assets/58bccfec-3635-414a-9147-67952298809d"/><br>


- 좋아요<br>
  <img src="https://github.com/user-attachments/assets/102b7c12-85c9-4f0c-85db-2bbddbd9bbbf"/><br>


- 탈퇴회원<br>
  <img src="https://github.com/user-attachments/assets/fcbcfde5-c952-40e7-8333-8ba634035c15"/><br>


# 프로젝트 구조
  <img src="https://github.com/user-attachments/assets/0052d5ee-0bcb-475b-af1d-ce1e15178269"/><br>

# 프로젝트 기능
<details>
<summary>회원(User)</summary>

- 내 정보 조회하기 <br>
- 회원 전체 조회<br>
- 회원 가입<br>
- 회원 가입시 로그인 중복 체크<br>
- 로그인<br>
- 회원 정보 수정<br>
- 회원 비밀번호 변경<br>
- 회원 탈퇴<br>

    + 탈퇴 시, 회원 테이블에서 삭제되며 탈퇴한 회원은 탈퇴한유저(DeleteUser) 테이블에 저장
</details>

<details>
<summary>게시글(Board)</summary>

- 게시글 조회<br>
  + 사용자가 게시글 조회수가 1 증가

- 게시글 전체 조회<br>
- 게시글 정렬 조회<br>
  + 일반 게시글 내에서 조회수(viewCount) 또는 좋아요(likeCount) 수로 정렬 조회


- 게시글 키워드 검색<br>

- 게시글 생성<br>
    + 게시글을 등록할 때 사용자는 제목, 내용, 카테고리를 필수로 설정
    + 이미지는 첨부하지 않거나 1개 이상 등록 가능
    + 이미지는 AWS S3 에 저장



- 게시글 수정<br>
- 게시글 공지글 설정<br>
- 게시글 삭제<br>

</details>

<details>
<summary>댓글(Comment)</summary>

- 댓글 생성<br>
- 댓글 조회<br>
- 댓글 수정<br>
- 댓글 삭제<br>

</details>

<details>
<summary>대댓글(ReplyComment)</summary>

- 대댓글 생성<br>
- 대댓글 수정<br>
- 대댓글 삭제<br>

</details>

<details>
<summary>카테고리(Category)</summary>

- 카테고리 생성<br>
- 카테고리 전체 조회<br>
- 카테고리 수정<br>
- 카테고리 삭제<br>

</details>

<details>
<summary>좋아요(BoardLike)</summary>

- 게시글 좋아요 조회<br>
- 좋아요 누르기<br>
- 좋아요 취소<br>

</details>

<details>
<summary>탈퇴 회원(DeleteUser)</summary>

- 탈퇴 회원 단건 조회<br>
- 탈퇴 회원 전체 조회<br>
- 탈퇴 회원 삭제<br>

</details>

# 테스트 진행
Mockito + Junit5 을 통해서 단위테스트로 진행했습니다.

<details>
<summary>테스트</summary>


- 회원(User)<br>


  <img src="https://github.com/user-attachments/assets/a1ebde24-7dae-4b78-b6d3-09f1820ba780"/><br>


- 게시글(Board)<br>


  <img src="https://github.com/user-attachments/assets/94d1572a-ecff-4500-9a8c-629a912132b0"/><br>


- 댓글(Comment)<br>


  <img src="https://github.com/user-attachments/assets/bc402c14-475e-43cc-b23a-e01dd79bb44b"/><br>
  

- 대댓글(ReplyComment)<br>


  <img src="https://github.com/user-attachments/assets/a9f3438e-9d81-4bc4-b8e7-f8700e5cc992"/><br>


- 카테고리(Category)<br>

  <img src="https://github.com/user-attachments/assets/b80a4824-f1b3-4857-b94d-54c90f9a8f97"/><br>

- 좋아요(BoardLike)<br>


  <img src="https://github.com/user-attachments/assets/d118e2d4-df48-4ecb-92a9-c62065125099"/><br>

</details>

# 트러블 슈팅 및 성능 개선

- request 와 response 에서 Entity 를 사용하게 되면 해당 Entity에 존재하는 모든 필드값이 반환되기 때문에 불필요하다고 판단됐습니다.  <br>
→ 필요한 필드만 갖고있는 DTO 를 만들어서 리팩토링했습니다.

  

- 댓글 조회 시 N + 1 문제  
사용자가 특정 게시글의 댓글을 전체 조회 시, 댓글 N개 와 회원 M개를 조회하는 쿼리가 발생  <br>
→ @BatchSize 와 FetchJoin 을 통해서 N + 1 문제를 해결하였고, 더미 데이터 와 Apache JMeter 를 통해 성능테스트를 진행했습니다.<br>


  
- 조회수 / 좋아요 기준 게시글 정렬 시 발생한 Full Table Scan 문제  <br>
사용자가 일반 게시글(isNotice = 0)을 조회수(viewCount) 또는 좋아요 수(likeCount) 기준으로 정렬 조회할 때 Full Table Scan 이 발생  <br>
→ (isNotice, viewCount), (isNotice, likeCount) 복합 인덱스를 설정하여 조회 성능을 개선했습니다.<br>


  
- 트랜잭션 격리 수준으로 인해 발생하는 이상 현상 문제<br>  
사용자의 API 요청 처리 시에 Lost Update, Dirty Read, Dirty Write 등 다음과 같은 이상 현상이 발생 <br> 
→ 이상현상에 따라서 비관 락, 낙관 락, Serializable 격리 수준을 적용하여 문제를 해결<br>  
→ Apache JMeter 를 통해서 동시성 문제 해결 및 비관 락, 낙관 락에 따른 성능 테스트 및 비교 분석을 했습니다.<br>

### [N + 1, 인덱스 해결과정 및 테스트 과정](https://kim00920.tistory.com/4)  
### [트랜잭션 해결과정 및 테스트 과정](https://kim00920.tistory.com/5)

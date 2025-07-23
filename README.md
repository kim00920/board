# 프로젝트 소개
- Spring Boot 기반 게시글 REST API 입니다
- Spring Boot + JPA 로 구현했습니다.
- 개발 기간 : 25.04.11 ~ 25.04.30
- 참여 인원 : 1명
- ### [프론트 구현](https://github.com/kim00920/boardJSP)

# 기술 스택
- Language : Java 17
- Framework : Spring 6.2.5 , SpringBoot 3.4.4
- Library :  Spring Security 6.4.4 (세션), Query DSL 5.1.0
- BuildTool : Gradle
- DB : MySQL 
- File : AWS S3
- Server : AWS EC2, Linux
- CI / CD : Docker, Docker Compose
- TestTool: Postman, Apache JMeter 
- Message Queue : RabbitMQ 4.1.1

# ERD
<img src="https://github.com/user-attachments/assets/96a4da24-e94b-438d-8972-56806e840de4"/><br>
<img src="https://github.com/user-attachments/assets/99ea12ad-ea05-49d6-bb1d-7c7cb4bced8a"/><br>


# 프로젝트 아키텍쳐
<img src="https://github.com/user-attachments/assets/5dff28b4-fa2e-4f81-8577-91dd6e608d6f"/>


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


- 알림<br>
  <img src="https://github.com/user-attachments/assets/6360d74c-20a3-4886-a325-4f2598a0b419"/><br>


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

<details>
<summary>알림(Notification)</summary>

- 알림 전체 조회<br>
- 회원이 읽지 않는 알림 개수 조회<br>
- 알림 읽음 처리<br>
- 알림 삭제<br>
 
++ 어떤 게시글에 좋아요를 누르면 그 작성자 회원에게 좋아요를 눌렀다는 알림메세지가 rabbitMQ 의 exchange , 라우팅 키 값을 통해 @RabbitListener 에서 알림 메세지를 생성한다<br>
++ 알림은 송신자 id, 수신자 id, 수신자의 게시글 id 를 갖고있으며, 같은 회원의 중복 알림 생성을 방지<br> 
</details>

# 테스트 진행
Mockito + Junit5 을 사용하여 단위테스트로 진행했습니다.

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

- 알림(Notification)<br>
  <img src="https://github.com/user-attachments/assets/c8af7841-a71d-45c7-bfe0-ae710d4d4a2a"/><br>
</details>


# 트러블 슈팅 및 성능 개선

- request 와 response 에서 Entity 를 사용하게 되면 해당 Entity 에 존재하는 모든 필드값이 반환되기 때문에 불필요하다고 판단<br>
→ 필요한 필드만 갖고있는 DTO 를 만들어서 리팩토링<br>

  

- 댓글 조회 시 N + 1 문제 <br>
사용자가 특정 게시글의 댓글을 전체 조회 시, 댓글 N개 와 회원 M개를 조회하는 쿼리가 발생  <br>
→ @BatchSize 와 FetchJoin 을 통해서 N + 1 문제를 해결하였고, 더미 데이터 와 Apache JMeter 를 통해 성능테스트를 진행<br>


  
- 조회수 / 좋아요 기준 게시글 정렬 시 발생한 Full Table Scan 문제  <br>
사용자가 일반 게시글(isNotice = 0)을 조회수(viewCount) 또는 좋아요 수(likeCount) 기준으로 정렬 조회할 때 Full Table Scan 이 발생  <br>
→ (isNotice, viewCount), (isNotice, likeCount) 복합 인덱스를 설정하여 조회 성능을 개선<br>


  
- 트랜잭션 격리 수준으로 인해 발생하는 이상 현상 문제<br>  
사용자의 API 요청 처리 시에 Lost Update, Dirty Read, Dirty Write 등 다음과 같은 이상 현상이 발생 <br> 
→ 이상현상에 따라서 비관 락, 낙관 락, Serializable 격리 수준을 적용하여 문제를 해결<br>  
→ Apache JMeter 를 통해서 동시성 문제 해결 및 비관 락, 낙관 락에 따른 성능 테스트 및 비교 분석을 했습니다.<br>

### [N + 1, 인덱스 해결과정 및 테스트 과정](https://kim00920.tistory.com/4)  
### [트랜잭션 해결과정 및 테스트 과정](https://kim00920.tistory.com/5)

<details>
<summary>추가 기능 구현(알림)</summary>

# 추가 기능 구현(07-08 갱신)
- 어떤 회원이 게시글에 좋아요를 눌렀을떄 게시글 작성자에게 게시글 좋아요를 눌렀다는 알림 메세지를 구현하고 싶었다

알림 기능을 구현하기 위해서 생각한 방법은 다음과 같다<br>

1. 스프링 AOP 에 있는 @Async 를 통해서 구현<br>
   장점 : 로컬에서도 쉽게 구현 및 처리가 가능하다<br>
   단점 : 로컬에서만 작동하기 때문에 확장성이 낮다, 장애가 발생했을떄 장애 복구가 힘들다<br><br>

2. Kafka 를 사용한다<br>
   장점 : 대용량 데이터에 처리에 능하며, 서비스 간 결합도를 최소화<br>
   단점 : 간단한 알림 메시지 시스템에는 너무 과한 선택이다<br><br>

3. RabbitMQ 를 사용한다<br>
   장점 : ACK 기반이므로 신뢰성있는 메시지 전송, Spring AMQP 를 통해 기존 프로젝트의 구조를 꺠지않으면서 사용가능<br>
   단점 : Kafka 에 비해 대용량처리에는 한계가 있지만, 게시글같이 단순 알림 메시지에는 적합 할 거라 생각<br>

→  RabbitMQ를 사용하기로 결정했다<br>

## 알림(Notification) 생성 및 처리과정
좋아요를 누른 회원(송신자)이 보낸 알림 메시지를 게시글 주인(송신자) 이 받는 기능을 만들거기 떄문에 다음과 같이 생성했다<br>
<img src="https://github.com/user-attachments/assets/30561998-9635-4b62-b6b4-30b95071f991"/><br>

1. 작성글 회원은 다른 회원이 좋아요를 누를떄마다 알림 메세지를 받게된다<br>
→ 이떄 알림 메시지를 만들때는 (송신자 id, 수신자 id, 수신자 게시글 id) 파라미터로 받게된다<br>

2. 이후 Rabbit MQ 빈 등록에서 설정한 경로로 convertAndSend(exchange, 라우팅 키, 알림 메시지 (json 직렬화)) 를 통해 큐 저장소에 저장되게된다<br>
<img src="https://github.com/user-attachments/assets/424f2ce7-f747-4a8c-b56d-e40e2471f96a"/><br>

3. 큐에 저장된 알림 메시지는 @RabbitListener 에서 꺼내오고, 아까 json 으로 받은 데이터를 객체로 역직렬화 후, Notification 을 저장한다<br>
<img src="https://github.com/user-attachments/assets/1ed16db6-617b-4edd-a592-981b6ba83647"/><br>

4. 만약에 송신자가 좋아요를 누르고 다시 취소하고를 반복하면 알림이 계속해서 갈 경우를 염려하여, (송신자 id, 수신자 id, 수신자 게시글 id) 가 DB 상에 존재할떄 return; 으로했다<br>
→ 그리고 기본적으로 생성된 알림 메시지의 읽은 여부는 false 로 하고 수신자가 그 알림을 눌렀을떄 읽음 처리(true) 로 바뀌게 구현했다<br>
<img src="https://github.com/user-attachments/assets/6fbd9917-9fa8-4984-bf35-8cdba24b0a07"/><br><br>


## 알림 API 흐름
1. 읽지 않는 알림 갱신 및 전체 조회<br>
![Image](https://github.com/user-attachments/assets/dd7483c0-1ffb-4fc5-ae9a-58f7c425a9da)<br>

2. 알림을 누르면 읽음 처리 및 갱신<br>
![Image](https://github.com/user-attachments/assets/450b70b8-189f-4307-96a2-27ad61e9acc8)<br>

3. 알림 삭제<br>
![Image](https://github.com/user-attachments/assets/2310e5c3-cb07-4c95-b759-43e80ee6732d)<br>

   
</details>

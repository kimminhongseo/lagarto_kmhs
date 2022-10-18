# Lagarto
경매를 이용한 쇼핑몰 기획 및 개발

<br>

# 목차
- [개발 환경](#개발-환경)
- [사용 기술](#사용-기술)
    * [백엔드](#백엔드)
    * [프론트엔드](#프론트엔드)
    * [기타 주요 라이브러리](#기타-주요-라이브러리)
- [E-R 다이어그램](#e-r-다이어그램)
- [프로젝트 소개](#프로젝트-소개)
- [핵심 기능](#핵심-기능)
    * [USER 관련 기능](#USER-관련-기능)
    * [게시판](#게시판)
    * [포인트 충전 및 결제 기능](#포인트-충전-및-결제-기능)
- [역할 분담](#역할-분담)

<br>

## 개발 환경
- IntelliJ IDEA
- Postman
- GitHub
- HeidiSQL
- Visual Studio Code

<br>

## 사용 기술
### 백엔드
#### 주요 프레임워크 / 라이브러리
- Java 11
- Spring Boot 2.6.3
- Mybatis

#### Build tool
- Maven

#### Database
- MariaDB

### 프론트엔드
- Thymeleaf
- HTML/CSS
- Javascript
- jQuery

#### 기타 주요 라이브러리
- Lombok
- JSON
- Apache Commons FileUpload
- Java Mail Sender

<br>

## E-R 다이어그램
![ERD](https://user-images.githubusercontent.com/83940731/194038323-4faa1148-547d-45f9-8def-2d3737bf891d.jpg)

<br>

## 프로젝트 소개

<img width="1792" alt="main" src="https://user-images.githubusercontent.com/83940731/194530641-8101c689-8a8d-439b-96a6-3cb1288eb942.png">

<br>

팀을 결성하고 아이디어 회의를 하던 중 팀원의 지인이 경매를 이용해 샵을 운영한다는 이야기에 아이디어를 얻었습니다.
새로운 소비문화의 형성으로 앱과 사이트를 이용한 거래 시스템이 활성화되고 있습니다. 
평소 여러 리셀 사이트의 이용 경험으로 경매 시스템을 구현해 보고자 했습니다.
저희는 경매 물품과 일반 물품을 분류하여 판매할 수 있도록 기획했고 일반 물품은 자체적으로 샵에서만 판매할 수 있도록 작업했습니다.
또한 같은 취미를 공유하는 유저들을 위해 정보 교환이 가능한 커뮤니티 게시판을 구성하여 작업했습니다.
회원의 활동 내용으로 회원 포인트를 적립하고 신뢰도를 가질 수 있는 우수 회원과 불량 회원의 레벨을 나누어 관리할 수 있도록 했습니다.

<br>

## 핵심 기능
### USER 관련 기능
#### 회원가입
정규식을 활용하여 프론트엔드와 백엔드에서 정보 입력 시 오류 체크를 작업했습니다. 간편한 가입을 위해 최소한의 정보만으로 가입할 수 있게 하고, 사이트 이용마다 필요한 정보를 요청하도록 했습니다. 소셜 가입 시에도 기존 회원의 정보를 체크하여 여러 플랫폼에서 중복 가입이 불가하도록 구현했습니다.

<br>

<img width="1792" alt="join" src="https://user-images.githubusercontent.com/83940731/194067428-eef9553c-3533-4e14-a35a-816b903a7ccb.png">
[회원가입]

<br>

<br>

<br>

<img width="48%" alt="join-check-contact" src="https://user-images.githubusercontent.com/83940731/194067746-d447efea-d3f6-4e2a-8473-b0553cd6bc63.png"> <img width="48%" align="right" alt="join-desc" src="https://user-images.githubusercontent.com/83940731/194069606-35c3522c-f68d-43f6-bcf4-c8a7c1de3c7f.png">
[데이터 중복 및 정보 미입력 체크, 이용약관 안내 모달창]

<br>

<br>

#### 로그인
쿠키를 이용해 아이디 저장 기능을 작업했습니다. 아이디 저장에 체크 후 로그인을 하게 되면 쿠키에 아이디가 저장됩니다. 로그인 창을 띄울 때 쿠키에 저장된 값의 유무를 파악 후 로그인 창에 해당 값을 띄우도록 하였습니다. 또 네이버, 카카오, 구글의 소셜 로그인 API를 이용해 간편 회원가입을 할 수 있도록 구현했습니다. 소셜 가입 시에도 간단하게 휴대폰 정보를 입력받아 일반 가입과 중복되는 정보는 가입이 불가하도록 하였습니다. 불법 로그인을 막기 위해 구글의 reCAPTCHA API를 사용해 작업했습니다.

<br>

<img width="1792" alt="login-auto" src="https://user-images.githubusercontent.com/83940731/194133137-6a3f14d3-a498-411d-bbff-c4c1935d0ad6.png">
[로그인 및 아이디 저장, reCAPTCHA API 기능]

<br>

<br>

<br>

<img width="48%" alt="join-google-1" src="https://user-images.githubusercontent.com/83940731/194133651-89ff8446-ddec-4711-b598-6b2224a1d222.png"> <img width="48%" align="right" alt="join-google-2" src="https://user-images.githubusercontent.com/83940731/194133666-50f44b6b-c8bc-4820-ade5-e498f0655237.png">
[구글 로그인 API 및 소셜 회원가입]

<br>

<br>

#### 아이디, 비밀번호 찾기
DB에 저장되어 있는 회원 정보와 일치하는 정보를 입력하게 되면 가입 플랫폼과 해당 아이디를 성공 페이지에서 띄울 수 있도록 했습니다. 비밀번호는 Java Mail Sender 라이브러리를 이용하여 작업했습니다. 일치하는 아이디(이메일)로 생성된 임시 비밀번호를 보내어 임시 비밀번호로 로그인할 수 있도록 했습니다.

<br>

<img width="48%" alt="forgot-id" src="https://user-images.githubusercontent.com/83940731/194133970-e8d2c20a-49d8-4041-9e22-5d14a90ea6cc.png"> <img width="48%" align="right" alt="forgot-id-google" src="https://user-images.githubusercontent.com/83940731/194134040-7391d003-e3ff-4c04-bc75-c9f8a4df58a9.png">
[아이디 찾기]

<br>

<br>

<img width="48%" alt="forgot-password" src="https://user-images.githubusercontent.com/83940731/194134347-2ad452be-f49e-4dd8-a059-8a9205f3851a.png"> <img width="48%" align="right" alt="forgot-password-email" src="https://user-images.githubusercontent.com/83940731/194136980-a217e553-6bb4-4261-82b9-8efc5b9c48e8.png">
[비밀번호 찾기 성공 시 임시 비밀번호 메일 전송]

<br>

<br>

#### 회원 레벨 기능
회원에 등급을 부여하여 관리할 수 있도록 했습니다. 로그인(출석), 글쓰기, 댓글 쓰기, 거래 등의 활동으로 포인트가 쌓이고, 일정 포인트에 도달할 시 레벨이 오를 수 있도록 작업했습니다. 거래 규칙을 위반한 회원은 불량 회원으로 분류해 서비스를 이용할 수 없게 정지시키고, 레벨이 높은 우수 회원은 신뢰도를 확인할 수 있습니다. 고객센터는 관리자만 답변을 쓸 수 있는 권한을 적용했습니다.

<br>

<a href="https://github.com/kimminhongseo/lagarto_kmhs/blob/609d2d42294bd5b003b3913ac6aae6c4fd2f68f0/src/main/java/com/portfolio/lagarto/user/UserController.java#L92-L100">회원 레벨 기능</a>

<br>

<br>

#### 마이페이지
마이페이지에서는 ajax 비동기 방식을 이용해 페이지 이동 없이 회원 정보를 수정할 수 있도록 작업했습니다. 비밀번호 찾기와 동일하게 이메일로 코드를 전달받아 이메일 인증을 할 수 있도록 작업했습니다. 카카오 우편번호 API를 이용해 주소 찾기를 구현했습니다. 내가 팔로우하고 나를 팔로잉 하는 상대를 마이페이지에서 확인할 수 있도록 했습니다. 판매하거나 구매한 내역을 관리할 수 있는 거래 내역 탭과 작성한 리뷰를 관리할 수 있는 나의 리뷰 탭을 구현했습니다.

<br>

<img width="48%" alt="mypage" src="https://user-images.githubusercontent.com/83940731/194756649-928bd907-cc02-408b-8125-c2957578890c.png"> <img width="48%" align="right" alt="mypage-address" src="https://user-images.githubusercontent.com/83940731/194757396-2c3c5598-f335-4f32-8c62-ae8944e501dc.png">
[마이페이지, 회원정보 수정 및 주소 찾기 API]

<br>

<br>

<img width="1792" alt="transaction-list" src="https://user-images.githubusercontent.com/83940731/194861167-c4757f23-0580-41ca-b141-0f7b10788985.png">
[거래 내역]

<br>

<br>

<br>

#### 팔로우 기능
팔로우 기능을 위해 USER FOLLOW 테이블을 만들고 각 유저 정보를 받아와 테이블에 추가될 수 있게 작업했습니다. 그리고 이미 팔로우 중인 유저는 해당 컬럼을 삭제하여 팔로우를 끊을 수 있게 하는 언팔로우 기능도 작업했습니다. 글 상세 페이지에서 글쓴이를 클릭하면 유저 정보와 팔로우 할 수 있는 창이 뜨도록 구현하였습니다. 유저의 팔로잉 팔로워 목록을 모아 마이 페이지에서 관리할 수 있게 하였습니다.

<br>

<a href="https://github.com/kimminhongseo/lagarto_kmhs/blob/609d2d42294bd5b003b3913ac6aae6c4fd2f68f0/src/main/java/com/portfolio/lagarto/follow/FollowController.java#L15-L81">팔로우 기능 구현</a>

<br>

<br>

<br>

### 게시판
#### 경매 게시판
판매자가 시작가와 즉시 구매가를 등록합니다. 구매자가 즉시 구매가를 입력하게 되면 남은 기간과 상관없이 경매가 종료되고 낙찰되도록 하였습니다. 글을 올린 시점부터 정해진 기간까지 경매를 진행하고, 남은 기간이 실시간으로 보이게 하였습니다. 유찰이 아닐 때 입찰 참여자들에게 입찰 시도한 금액을 반환하는 시스템을 작업했습니다.

<br>

<img width="1792" alt="auction" src="https://user-images.githubusercontent.com/83940731/194873568-cf9366a4-552c-4241-b6c8-6adf2fc2f52e.png">
[경매 상세 페이지]

<br>

<br>

<br>

<img width="48%" alt="auction-buy" src="https://user-images.githubusercontent.com/83940731/194876273-6b7d1e55-18bb-4a65-b763-951175f9479d.png"> <img width="48%" align="right" alt="auction-success" src="https://user-images.githubusercontent.com/83940731/194876279-f40564e1-f9f2-46b4-a263-b622bf4c92bc.png">
[경매 참여, 낙찰]

<br>

<br>

<a href="https://github.com/kimminhongseo/lagarto_kmhs/blob/609d2d42294bd5b003b3913ac6aae6c4fd2f68f0/src/main/java/com/portfolio/lagarto/auction/bid/AuctionBidService.java#L16-L43">경매 시스템 구현</a>

<br>

<br>

#### 용품 게시판, 장바구니 기능
일반 상품을 구매할 수 있는 용품 게시판을 작업했습니다. 제품 상세페이지에서 수량을 선택해 장바구니에 담고, 충전한 쇼핑몰 포인트로 결제할 수 있습니다. 장바구니에서 개수 증가에 따라 금액이 변동되도록 작업했습니다.

<br>

<img width="1793" alt="cart" src="https://user-images.githubusercontent.com/83940731/195096291-ea6189e3-4480-4214-8cf2-e4d56a09a962.png">
[장바구니]

<br>

<br>

<br>

#### 리뷰, 커뮤니티, 고객센터 게시판
Spring MVC 프레임워크를 이용해 글 작성, 수정, 삭제 기능과 게시글 검색 기능을 작업했습니다. 리뷰 게시판은 상품 구매 후에만 글을 남길 수 있도록 작업했습니다. 자유롭게 정보를 나눌 수 있는 커뮤니티 게시판과 문의를 남길 수 있는 고객센터 게시판을 작업했습니다. 고객센터 게시판은 글 작성 시 비밀글 체크를 통해 해당 유저와 관리자만 글에 접근할 수 있도록 하였습니다. 그리고 게시글 상세 페이지에서 좋아요와 댓글을 남길 수 있게 작업했습니다. 이미지 테이블을 따로 만들어 여러 장의 이미지를 업로드할 수 있는 다중 업로드 기능을 구현했습니다.

<br>

<img width="1792" alt="community-search" src="https://user-images.githubusercontent.com/83940731/195107924-895ffc8e-fb48-4b7b-9703-ba9a25ed1d21.png">
[커뮤니티 게시판, 검색 기능]

<br>

<br>

<br>

<img width="48%" alt="customer-detail" src="https://user-images.githubusercontent.com/83940731/195108411-096955cd-2606-45fe-86ca-d4f264dd251a.png"> <img width="48%" align="right" alt="customer-comment" src="https://user-images.githubusercontent.com/83940731/195108392-8c21b728-e6ad-4f9b-98b3-853a348ddd3c.png">
[고객센터 게시판]

<br>

<a href="https://github.com/kimminhongseo/lagarto_kmhs/blob/609d2d42294bd5b003b3913ac6aae6c4fd2f68f0/src/main/resources/views/customer/list.html#L75">비밀글 기능</a>

<br> 

<a href="https://github.com/kimminhongseo/lagarto_kmhs/blob/609d2d42294bd5b003b3913ac6aae6c4fd2f68f0/src/main/java/com/portfolio/lagarto/customer/CustomerService.java#L31-L46">다중 업로드 기능</a>

<br>

<br>

<br>

### 포인트 충전 및 결제 기능
iamport API를 이용해 카카오페이 결제 시스템을 구현했습니다. 실제로 테스트 결제가 가능하도록 작업했습니다. 카카오페이 결제를 통해 쇼핑몰 포인트가 충전되고 해당 포인트로 경매 참여와 일반 상품 구매가 가능합니다.

<br>

<img width="48%" alt="money-charge" src="https://user-images.githubusercontent.com/83940731/195324194-8c6fccf2-5e96-463d-a601-6f6512437f56.png"> <img width="48%" align="right" alt="money-charge2" src="https://user-images.githubusercontent.com/83940731/195324202-d87f358d-f5d8-4680-80fc-7540afd9953b.png">
[카카오페이 이용한 포인트 충전 기능]

<br>

<a href="https://github.com/kimminhongseo/lagarto_kmhs/blob/609d2d42294bd5b003b3913ac6aae6c4fd2f68f0/src/main/resources/static/js/cart/cart.js#L95-L138">포인트를 이용한 결제 기능</a>

<br>

<br>

## 역할 분담

김대용 (eodyd0313@gmail.com) : 로그인, reCAPTCHA, 마이페이지, 팔로우 기능, 카카오 결제 구현


민균기 (mingg95@naver.com) : 경매 시스템, 용품 게시판, 장바구니, SNS API 로그인(네이버) 구현


서유영 (sidney222@naver.com) : 리뷰 게시판, 커뮤니티 게시판, 고객센터 게시판, SNS API 로그인(카카오) 구현


홍수아 (suaah.96@gmail.com) : 회원가입, 아이디 저장, 아이디/비밀번호 찾기, SNS API 로그인(구글), 회원 레벨, 메인 페이지 구현



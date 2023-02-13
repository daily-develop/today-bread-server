<div align="center">
	<h1>Today Bread Server</h1>
	<p>
		<b>Naver Cloud Platform Appetizer Development Supporters Team 5</b>
	</p>
	<br>
</div>

## 1. 서비스 소개

### 1-1. 오늘의 빵이란?

'오늘의 빵'은 전국에 있는 베이커리의 맛있는 빵 패키지를 **구독**하여, 주기적으로 신선한 빵을 배송 받을 수 있는 **빵 구독 플랫폼**입니다.

### 1-2. 주요 기능

- **베이커리 등록 및 조회**
  - 베이커리 사장님은 자신의 베이커리를 '오늘의 빵'에 등록할 수 있습니다.
  - 소비자는 등록되어 있는 베이커리를 조회할 수 있습니다.

- **빵 패키지 등록 및 조회**
  - 베이커리 사장님은 자신의 베이커리에 빵 패키지를 구독 상품으로 등록하여 판매할 수 있습니다.
  - 소비자는 판매중인 빵 패키지를 조회할 수 있습니다.

- **빵 패키지 랭킹 및 추천**
  - 판매중인 빵 패키지의 판매 혹은 별점 순으로 랭킹을 조회할 수 있습니다.

- **빵 패키지 구독 결제**
  - 스텝페이의 구독 API를 사용하여, 빵 패키지를 매달 구독하여 결제할 수 있습니다.

<br />

## 2. 서비스 구조

### 2-1. 사용 기술

![플로우 차트](https://user-images.githubusercontent.com/65934968/212262048-d03a21f3-a9cf-487e-97e1-5e6cd1f42159.png)

![GraphQL](https://img.shields.io/badge/GraphQL-E10098?style=flat-square&logo=GraphQL&logoColor=white)

![Typescript](https://img.shields.io/badge/Typescript-3178C6?style=flat-square&logo=Typescript&logoColor=white)
![React](https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=React&logoColor=white)
![Expo](https://img.shields.io/badge/Expo-000020?style=flat-square&logo=Expo&logoColor=white)
![Apollo GraphQL](https://img.shields.io/badge/Apollo%20GraphQL-311C87?style=flat-square&logo=Apollo-GraphQL&logoColor=white)

![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=Spring-Boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square&logo=Spring-Security&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=Hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=PostgreSQL&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=Redis&logoColor=white)

![Naver Cloud Platform](https://img.shields.io/badge/Naver%20Cloud%20Platform-03C75A?style=flat-square&logo=Naver&logoColor=white)
![Kubernetes](https://img.shields.io/badge/Kubernetes-326CE5?style=flat-square&logo=Kubernetes&logoColor=white)
![Argo](https://img.shields.io/badge/Argo-EF7B4D?style=flat-square&logo=Argo&logoColor=white)
![Elastic Search](https://img.shields.io/badge/Elastic%20Search-005571?style=flat-square&logo=Elastic&logoColor=white)
![Fluent Bit](https://img.shields.io/badge/Fluent%20Bit-49BDA5?style=flat-square&logo=Fluent-Bit&logoColor=white)
![Kibana](https://img.shields.io/badge/Kibana-005571?style=flat-square&logo=Kibana&logoColor=white)

### 2-2. 배포 구조

![배포](https://user-images.githubusercontent.com/65934968/212266577-1b06defd-ad98-466c-a7a2-8072634dcbc9.png)

### 2-3. GraphQL Schema

[GraphQL Schema Link](https://github.com/daily-develop/today-bread-server/tree/main/src/main/resources/graphql)

### 2-4. Database Schema

![Database Schema](https://user-images.githubusercontent.com/65934968/212277572-4283dd64-b313-4529-8dae-5e7e25d5abf0.png)

<br />

## 3. 구현 모습

[![Youtube](https://user-images.githubusercontent.com/65934968/212269508-b3e3c0c1-463a-4f22-9614-2a57185e7282.png)](https://www.youtube.com/watch?v=EjCbz7YSqbA&t)

### 3-1. 인증 화면

<img src="docs/images/회원가입.gif" alt="회원가입" width="200px">
<img src="docs/images/로그인.gif" alt="로그인" width="200px">

### 3-2. 홈 화면

<img src="docs/images/홈%20(빵%20종류별%20조회).gif" alt="홈 (빵 종류별 조회)" width="200px">
<img src="docs/images/홈%20(추천%20패키지).gif" alt="홈 (추천 패키지)" width="200px">
<img src="docs/images/홈%20(최신%20베이커리).gif" alt="홈 (최신 베이커리)" width="200px">

### 3-3. 검색

<img src="docs/images/검색.gif" alt="검색" width="200px">

### 3-4. 결제(구독)

<img src="docs/images/결제(구독).gif" alt="결제(구독)" width="200px">

### 3-5. 프로필

<img src="docs/images/프로필.gif" alt="프로필" width="200px">

### 3-6. 리뷰

<img src="docs/images/리뷰.gif" alt="리뷰" width="200px">

### 3-7. 패키지 랭킹

<img src="docs/images/패키지%20랭킹.gif" alt="패키지 랭킹" width="200px">

### 3-8. 베이커리 생성

<img src="docs/images/베이커리%20생성.gif" alt="베이커리 생성" width="200px">

### 3-9. 패키지 생성

<img src="docs/images/패키지%20생성.gif" alt="패키지 생성" width="200px">

### 3-10. 베이커리 주문 조회

<img src="docs/images/베이커리%20주문%20조회.gif" alt="베이커리 주문 조회" width="200px">


### 3-11. 판매 종료

<img src="docs/images/판매%20종료.gif" alt="판매 종료" width="200px">

### 3-12. 배포

![GraphQL Playground](./docs/images/GraphQL%20Playground.gif)
![ArgoCD](./docs/images/ArgoCD.gif)
![ArgoCD](./docs/images/Kibana.gif)

<br />

## 4. 팀원

<table align="center">
  <tr align="center">
    <td>
        <img src="https://avatars.githubusercontent.com/u/65934968?v=4" width=160px height=160px alt="himitery" />
    </td>
    <td>
        <img src="https://avatars.githubusercontent.com/u/81179951?v=4" width=160px height=160px alt="govl6113" />
    </td>
  </tr>    
  <tr align="center">
    <td>
      <a href="https://github.com/himitery">
        <span>단국대학교 소프트웨어학과 이학진</span>
      </a>
    </td>
    <td>
      <a href="https://github.com/govl6113">
        <span>단국대학교 응용컴퓨터공학과 배지현</span>
      </a>
    </td>
  </tr>
  <tr align="center">
    <td>
      <span>Server & Application</span>
    </td>
    <td>
      <span>Server</span>
    </td>
  </tr>
</table>

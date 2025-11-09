
<p align="center">
  <img src="https://github.com/Munhak-Zip/Moviezip_Back/assets/110006845/e8d64401-4e07-45d5-abd1-923abf6a17d4" alt="화면 캡처 2024-07-08 231353">
</p>
<br>

## [💻 WEB] 영화 리뷰 모음, 예매 및 추천 웹사이트, MOVIE.ZIP (2024.03 ~ 2024.06)
<p align="center">
  <img src="https://github.com/Munhak-Zip/Moviezip_Back/assets/110006845/8f2ead82-be3e-4fb0-a2ec-04430d1a8cf5">
</p>

<br>

| 회원가입 | 로그인 | 취향 선택  |
|:-:|:-:|:-:|
| <img src="https://github.com/user-attachments/assets/5a32011f-1dd4-4dd9-ac99-13b8edf4ad8c" alt="회원가입" width="450"/> | <img src="https://github.com/user-attachments/assets/8e81fe3e-56b8-4f6f-a9d0-bba771d12118" alt="로그인" width="450"/> | <img src="https://github.com/user-attachments/assets/c6433997-3e3e-4a57-adac-05dcb0d34660" alt="취향선택" width="450"/>

| 메인화면 최신영화 | 메인화면 추천영화 | 영화 검색  |
|:-:|:-:|:-:|
| <img src="https://github.com/user-attachments/assets/c474ea04-9c53-4015-a41b-f177336766b2" alt="최신영화" width="450"/> | <img src="https://github.com/user-attachments/assets/7ee5c7b3-a962-4d32-911c-4c97bdf1131b" alt="추천영화" width="450"/> | <img src="https://github.com/user-attachments/assets/1d069c69-9854-4832-baff-c3a400caca1c" alt="영화검색" width="450"/>

| 영화 상세조회 | 리뷰 작성 | 영화 스크랩  |
|:-:|:-:|:-:|
| <img src="https://github.com/user-attachments/assets/440e360b-8db0-46f3-9cf2-c2e4e0c4ecee" alt="영화상세보기" width="450"/> | <img src="https://github.com/user-attachments/assets/9c7b0390-47a8-4b18-9c6b-95faea3af083" alt="리뷰작성" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/4226bd46-3477-4651-b204-91d4e1572398" alt="영화스크랩" width="450" height="190"/>

| 내가 좋아한 영화 | 내가 작성한 리뷰   | 리뷰 수정 및 삭제  |
|:-:|:-:|:-:|
| <img src="https://github.com/user-attachments/assets/f8c1fb57-10ff-445b-8806-3fa2eaf79b89" alt="내가좋아한영화" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/bfe5b9ee-68f5-4470-9853-1b585896ac54" alt="리뷰리스트" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/7c1d32e1-5fd8-491b-b815-bf786b3101e4" alt="리뷰 수정" width="450" height="190"/>


| 영화관 선택 화면 | 영화 좌석 예매 화면   | 결제화면  |
|:-:|:-:|:-:|
| <img src="https://github.com/user-attachments/assets/4719f0dc-9441-4786-bfb5-d66e7d557d4b" alt="영화관선택화면" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/bf4d9a2a-0237-4500-8945-d6b472161f07" alt="영화좌석예매화면" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/fda9fa59-9c59-45aa-90bd-70d1e02b53eb" alt="결제화면" width="450" height="190"/>

| 마이페이지 | 비밀번호 변경  | 아이디 찾기  |
|:-:|:-:|:-:|
| <img src="https://github.com/user-attachments/assets/541a1bc7-74b7-40b3-921a-f1dc0c078514" alt="마이페이지" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/db1502f1-b064-49b2-b622-7d23cb87f9a1" alt="비밀번호 변경" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/2789237d-13d2-4b98-b357-dd4b8b28f16a" alt="아이디찾기" width="450" height="190"/>


| 채팅방 리스트 | 채팅 화면  | 로그아웃  |
|:-:|:-:|:-:|
| <img src="https://github.com/user-attachments/assets/ccebfa2e-184d-484f-b788-2515307da38f" alt="채팅방리스트" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/9e52b7b0-c7bb-4d9c-a060-97cbc7f86502" alt="채팅화면" width="450" height="190"/> | <img src="https://github.com/user-attachments/assets/699278da-856c-4ca9-a9c0-c932ec330c99" alt="로그아웃" width="450" height="190"/>

## 🧱 ERD

### 📌1차 ERD

<p align="center">
  <img src="https://github.com/user-attachments/assets/5548e8b7-9ac7-49e0-a744-4455d42d995e">
</p>

### 📌2차 ERD - 리뷰 테이블 구조 개선 및 스크랩 기능 분리

- 기존에는 유사한 구조의 리뷰 관련 테이블이 2개로 나뉘어 있었으나, 중복을 줄이고 유지 보수를 용이하게 하기 위해 하나의 테이블로 통합하였습니다.

- 또한, 영화 스크랩과 리뷰 스크랩은 다른 도메인이므로 구분하여 관리할 수 있도록 별도의 테이블로 분리하였습니다.

- MovieScrap: 사용자(User)와 영화(Movie) 간의 스크랩 관계 (User 1:N MovieScrap, Movie 1:N MovieScrap)

- ReviewScrap: 사용자(User)와 리뷰(Review) 간의 스크랩 관계 (User 1:N ReviewScrap, Review 1:N ReviewScrap)

<p align="center">
  <img src="https://github.com/user-attachments/assets/97673c66-7f88-499a-88e0-282cd15e0113">
</p>



### 🎟️ 3차 ERD  영화 예매, 문의 채팅 기능 설계 및 테이블 구조 개선

#### 🎬 예매 기능 개선

기존의 예매 테이블은 단순히 날짜 및 좌석 정보만 저장하고 있어, 실제 영화 예매 시스템의 흐름을 반영하기 어려웠습니다.
이에 따라 일반적인 영화 예매 플로우를 기준으로 필요한 테이블들을 재정의하였습니다.

#### 💬 1:1 고객센터 채팅 기능

사용자와 관리자가 실시간으로 소통할 수 있는 1:1 채팅 기능을 구현하였습니다.
MongoDB를 사용해 비관계형 데이터 구조로 채팅 데이터를 저장하고 있으며, 가독성을 위해 ERD 형식으로 시각화하였습니다.
Oracle 기반으로 기존 설계되었던 데이터베이스와의 직접적인 연동은 없습니다.

#### 🔐 인증 방식 개선 (Session → JWT)

기존에는 Spring Security의 세션 기반 인증을 사용하였으나, JWT 기반 인증 방식으로 전환한 이유는 다음과 같습니다.

- 본 프로젝트는 웹뿐만 아니라 모바일 애플리케이션과의 연동 가능성까지 고려하여 설계되었습니다.
  
- 세션 기반 인증은 서버 측 상태 저장이 필요하고 확장성에 한계가 있는 반면, JWT는 Stateless 구조로 클라이언트가 토큰을 보관하며, 모바일 환경에서도 효율적인 인증 처리가 가능합니다.

결과적으로 JWT 기반 인증을 통해 유지 보수성과 확장성을 높일 수 있었습니다.


### 
<p align="center">
  <img src="https://github.com/user-attachments/assets/a3cc4810-ff4c-48a0-b5f1-60f983a3b757">
</p>

<br>

## 🛠 성능 최적화: 실시간 추천 캐싱 전략

#### 🧩 Problem: 영화 추천 API가 너무 느리다

- 기존 추천 API /main/recommend는 Spark ALS 기반 연산을 요청마다 수행함
- 평균 응답 18.4초 → 사용자가 매번 기다려야 하는 심각한 UX 문제
- 원인: ALS 연산(학습/예측)이 CPU·I/O를 많이 사용하며, 매 요청 시 재계산하는 구조
<img width="548" height="137" alt="image" src="https://github.com/user-attachments/assets/5e0ed2d3-9c2f-40f2-9c6f-8ae719ccd2ca" />

#### ⚡️ Solution: Redis 캐시 + Cache-aside 패턴 도입

첫 요청: 캐시 MISS → ALS로 추천 계산 → Redis 저장(24h TTL)

이후 요청: 캐시 HIT → Redis에서 즉시 응답

| 항목       | 캐시 미적용           | Redis 캐시 적용    |
| -------- | ---------------- | -------------- |
| 평균 응답 시간 | 18,426ms(~18초) |3ms            |
| 속도 향상    | -                | 🔥 **6000배** |

- 키 전략: recommend:{userId}

- TTL: 24시간 (데이터 신선성 확보 + 과도한 재계산 방지)

- 패턴: Cache-aside (애플리케이션이 캐시를 직접 관리)

#### 🧱 구조

1. Controller → MovieCacheRecommenderService#getRecommendations(userId) 호출

2. Redis 조회(HIT면 즉시 반환)

3. MISS면 Spark ALS로 계산 → JSON 직렬화해 Redis에 저장(TTL=1day)

4. 이후 동일 사용자 요청은 캐시에서 반환(3ms)

#### 💡 핵심 코드 (Cache-aside)
```
    public List<String> getRecommendations(int userId) {
        String key = "recommend:" + userId;
        String cachedJson = (String) redisTemplate.opsForValue().get(key);

        if (cachedJson != null) {
            System.out.println("[CACHE HIT] " + key);
            try {
                return objectMapper.readValue(cachedJson, List.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("JSON 파싱 실패", e);
            }
        }
        System.out.println("[CACHE MISS] " + key);
        // 미스 → 계산 후 JSON 저장
        List<String> result = recommendMovies(userId);
        try {
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(result), 1, TimeUnit.DAYS);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 직렬화 실패", e);
        }
        return result;
    }
}
```


<img width="594" height="197" alt="image" src="https://github.com/user-attachments/assets/9f7fc298-a2d0-4c8d-985b-3dd31c71cb2f" />



#### 🧠 Spark + JDK 호환 문제 해결

```
java.lang.IllegalAccessError: class org.apache.spark.storage.StorageUtils$ cannot access class sun.nio.ch.DirectBuffer
```
#### 원인
- JDK 9+ 부터 내부 패키지 접근 제한 → Spark 2.x와 충돌

#### 해결 방법

```
--add-exports java.base/sun.nio.ch=ALL-UNNAMED
```
Spark 내부 클래스에서 DirectBuffer 접근 허용

### 4. 🔌 **포스팅**

| 주제 | 담당자 | 관련 문서 |
|------|--------|-------------|
| 세션 기반 인증에서 JWT로 전환 회고 | 허민영 | [바로가기 🔗](https://dragonair148.tistory.com/entry/Spring-Security-JWT-%EC%84%B8%EC%85%98-%EA%B8%B0%EB%B0%98-%EC%9D%B8%EC%A6%9D%EC%97%90%EC%84%9C-JWT%EB%A1%9C-%EC%A0%84%ED%99%98-%ED%9A%8C%EA%B3%A0) |
| Spring Security + JWT 사용자 인증(1) - 로그인 & 토큰 발급 | 허민영 | [바로가기 🔗](https://dragonair148.tistory.com/entry/Spring-Spring-Security-JWT-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%9D%B8%EC%A6%9D1-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%ED%86%A0%ED%81%B0-%EB%B0%9C%EA%B8%89) |
| Spring Security + JWT 사용자 인증(2) - Spring Security 설정 및 필터 적용 | 허민영 | [바로가기 🔗](https://dragonair148.tistory.com/entry/Spring-Spring-Security-JWT-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%9D%B8%EC%A6%9D2-Spring-Security-%EC%84%A4%EC%A0%95-%EB%B0%8F-%ED%95%84%ED%84%B0-%EC%A0%81%EC%9A%A9) |
| Spring Security + JWT 사용자 인증(3) - Refresh Token을 활용한 인증 갱신 | 허민영 | [바로가기 🔗](https://dragonair148.tistory.com/entry/Spring-Spring-Security-JWT-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%9D%B8%EC%A6%9D3-Refresh-Token%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9D%B8%EC%A6%9D-%EA%B0%B1%EC%8B%A0) |

## 📦 주요 기술 스택

| 분야                | 기술                                                        |
|---------------------|-------------------------------------------------------------|
| **Language**        | Java 17                                                     |
| **Framework**       | Spring Boot 2.7.18                                          |
| **ORM**             | MyBatis                                                |
| **Database**        | OracleDB, MongoDB                                           |
| **Security**        | Spring Security, JWT (JJWT 0.11.5)                          |
| **WebSocket**       | Spring WebSocket                                            |
| **Data Store**      | Redis                                                       |                 |
| **ML/추천엔진**     | Apache Spark (spark-core, spark-mllib)                      |
| **Validation**      | Hibernate Validator                                         |
| **Test**            | JUnit, Spring Security Test                                 |
| **Build Tool**      | Maven                                                       |

<br>

## ✨ Member
<div align="center">
  <table>
    <tr>
      <td align="center"><img src="https://avatars.githubusercontent.com/ldayun" width="100" height="100" /></td>
      <td align="center"><img src="https://avatars.githubusercontent.com/somflower" width="100" height="100" /></td>
      <td align="center"><img src="https://avatars.githubusercontent.com/yjin-jo" width="100" height="100" /></td>
      <td align="center"><img src="https://avatars.githubusercontent.com/MinCodeHub" width="100" height="100" /></td>
    </tr>
    <tr>
      <td align="center"><a href="https://github.com/ldayun">이다윤</a></td>
      <td align="center"><a href="https://github.com/somflower">김미현</a></td>
      <td align="center"><a href="https://github.com/yjin-jo">조예진</a></td>
      <td align="center"><a href="https://github.com/MinCodeHub">허민영</a></td>
    </tr>
  </table>
</div>


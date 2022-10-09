# Place Search Service

### 장소 검색 서비스 구현
###
#### 주요 기능
* [기본 : 장소 검색]
* [기본 : 검색 키워드 목록]
* [추가 : -필요시 개발간 추가 예정-]

### 각 API 프로젝트 공통 스펙
- 언어 / 버전 : java / 11
- 프레임워크 : Spring boot 2.7.1
- RDBMS : MariaDB 10.9.3
- IN-MEMORY DB : Redis
- Swagger 2.0 적용


* * *
####[첨부 파일 목록 및 설명]
1. resources/schema.sql : spring boot 실행시 MariaDB에 넣을 기본 스키마 및 기초데이터 .sql 파일이다.
2. 개발간 사용한 mariadb, redis 이미지 첨부.
3. Swagger 접속 정보
- http://localhost:9802/swagger-ui.html#

#### [부가설명]
- Maria DB와 Redis는 구현 후 테스트간 직접 로컬PC에 Docker-container 형태로 기동하여 테스트 하였음.
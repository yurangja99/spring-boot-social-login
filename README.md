## Social Login Test Project
스프링 부트와 Mustache(프론트엔드 대용)를 사용하여 카카오 등의 소셜 로그인을 구현하여 보는 프로젝트. 

## Spring Initializer 설정
- Gradle
- Kotlin
- 2.3.3
- com.namsaeng.sociallogin
- JAVA 8
- Spring Web, Devtools, Mustache

## 실험 방법
- **src/main/resources/application.properties 파일은 Github Repository에 업로드하지 않았으므로 개인적으로 얻어야 함.**
1. MySQL을 설치하고, user 테이블을 생성. 칼럼 구조는 아래 참조.
    - id bigint not null auto_increment primary key
    - name text not null
    - email text not null
    - picture text
    - role text not null 
2. 원하는 디렉토리로 이동
3. git clone https://github.com/yurangja99/spring-boot-social-login.git
4. 개인적으로 받은 application.properties 파일에서 spring.datasource.로 시작하는 속성들을 자신의 MySQL 서버에 맞게 수정
5. 프로젝트 디렉토리에서 gradlew bootjar 명령 실행
6. build\libs 폴더로 이동
7. java -jar sociallogin-0.0.1-SNAPSHOT 실행
8. http://localhost:8080에 접속하여 실험

## 진행상황
### 2020/09/10 (#1)
- Initial Commit
- Mustache 연동
### 2020/09/11 (#2)
- 구글 로그인 연동
- 로그아웃과 다른 소셜 계정으로 로그인하는 것은 카카오 로그인도 적용 후 확인하기로 함.
# OSS_project
1. 실행환경 

Java 설치

Java Development Kit (JDK) 21이 설치되어 있어야 합니다.
https://www.oracle.com/java/technologies/downloads/#java21?er=221886

Gradle 빌드 도구가 설치되어 있어야 합니다 https://gradle.org/releases/

원하는 버전을 다운로드 -> 디렉토리(C:\gradle\) 생성 -> 다운로드한 압축 파일 해제
시스템 환경 변수 편집 -> 환경 변수 -> 시스템 변수 -> Path를 선택한 후 편집 -> 새로 만들기를 클릭
bin 디렉토리 경로를 입력한다
ex) Gradle을 C:\gradle\gradle-8.14.2-bin\gradle-8.14.2\bin 에 설치했다면 해당 경로를 추가

powershell을 열고 gradle -v 입력, 정상적으로 설치되면 Gradle 버전 정보 출력

ngrok 계정 및 ngrok CLI https://ngrok.com/

Git, IDE( IntelliJ, VS Code 등)

2. 프로젝트 실행 방법

소스코드 zip 파일을 다운받은 후에 zip 파일을 압축해제한다.

2-1

1) 프로젝트의 application.properties이 JAR 파일과 같은 디렉토리에 있어야 한다
2) 인텔리제이에서 프로젝트를 연다
3) build-gradle 파일을 열고 플러그인이 포함되어 있는지 확인한다

plugins {
	id 'java'
	id 'org.springframework.boot' version '3.4.5'
	id 'io.spring.dependency-management' version '1.1.7'
}

group = 'com.buskspot'
version = '0.0.1-SNAPSHOT'

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
}

tasks.named('test') {
	useJUnitPlatform()
}

4) 터미널(cmd)를 열고 프로젝트 루트 파일 디렉토리로 이동
5) .\gradlew clean build 명령어 입력해 JAR 파일을 생성
6) build/libs 디렉토리 안에 buskspot-0.0.1-SNAPSHOT.jar 파일이 생성
7) 터미널(cmd)를 열고 JAR 파일이 있는 디렉토리로 이동한다
8) java -jar buskspot-0.0.1-SNAPSHOT.jar 명령어 입력해 애플리케이션을 시작한다
9) 브라우저를 열고 http://localhost:8080 으로 접속하면 애플리케이션을 이용할 수 있다

2-2

1) 소스코드 zip 파일을 다운로드 받아서 Powershell을 관리자 권한으로 실행, zip 파일이 있는 디렉토리로 이동한다
ex) 파일을 바탕화면 - project라는 파일에 저장했으면
cd "C:\Users\<사용자 이름>\OneDrive\바탕 화면\project"

2) 압축을 풀어준다. 압축은 직접 파일 탐색기에서 압축 해제를 하던지 아니면
Expand-Archive -Path "buskspot.zip" -DestinationPath "buskspot" 명령어를 입력해 압축을 풀어준다

3) 압축을 푼 프로젝트 디렉토리로 이동한다 ex) cd buskspot

4) Gradle 빌드를 위해 Gradle 혹은 gradlew.bat이 있는 디렉토리로 이동한다

cd buskspot ( 지금의 경우는 C:\Users\<사용자이름>\OneDrive\바탕 화면\project\buskspot\buskspot 에 존재)
 
5) .\gradlew bootRun 명령어를 실행시켜 애플리케이션을 실행한다

6) http://localhost:8080 으로 접속을 하면 애플리케이션을 이용할 수 있다

3. 실제 서버 배포처럼 작동시켜보기

다른 사람들이 url을 통해서 들어오게 하기 위해서는 ngrok 터널링으로 접속을 허용해줄 수 있다

ngrok 계정을 생성하고 powershell에서 관리자 권한으로 winget install ngrok.ngrok 명령어 입력

ngrok 사이트에서 Your Authtoken에서 인증 토큰을 받아서 ngrok authtoken <인증받은 토큰> 명령어를 입력한다 이 방법은 한번만 진행하면 된다

ngrok http 8080 명령어을 입력해서 실행

Forwarding 에 있는 URL ex ) https://dd43-118-41-8-163.ngrok-free.app 에 다른 사람들이 접속하면 사이트에 접속할 수 있다.

현재는 재실행시킬때마다 주소가 바뀌기 때문에 잘 확인해야된다.

그리고 이 과정은 애플리케이션을 실행시킨 상태여야지 다른 사람들이 접속 가능하다

4. 간단한 사이트 이용 방법

사이트에 처음 접속을 하게 되면 회원가입을 하고 로그인을 해야 메인 페이지로 이동 가능하다

로그인은 일반 사용자, 관리자 두개가 있는데 관리자는 admin@admin.com / admin 으로 로그인 하면 관리자 페이지로 넘어간다

일반 사용자 로그인

- 정보 확인 : 본인의 정보를 확인 할 수 있다. 본인의 이메일 하고 닉네임을 확인 할 수 있음

- 공연 등록 : 본인의 버스킹 공연 제목, 장소, 날짜를 등록 할 수 있고 등록한 현황과 승인/거절 현황도 확인 가능. 현황은 공연 날짜를 기준으로 오름차순하여 10개까지 확인 가능

- 장비 대여 : 필요한 장비와 대여 날짜, 반납 날짜를 등록 할 수 있고 대여 현황과 승인/거절 현황도 확인 가능. 현황은 대여 날짜를 오름차순으로 정리하여 총 10개까지 확인 가능

- 규칙 / 공연 보기 : 관리자가 홍보를 승인한 공연들은 여기에 다 올려져서 사람들이 볼 수 있고 관리자가 올린 사이트 운영 규칙도 여기서 같이 확인 할 수 있다.

- 로그아웃 : 로그아웃을 해서 로그인 / 회원가입 페이지로 넘어간다

- 회원탈퇴 : 더 이상 이 사이트를 이용하지 않을 때 회원탈퇴를 통해서 본인의 로그인 정보를 삭제 시킨다. 회원 탈퇴를 하고 다시 똑같은 계정으로 회원가입을 하고 와도 그전까지의 모든 내용은 삭제 되어있다.

관리자 로그인

- 공연 등록 관리 : 일반 사용자가 신청한 공연들을 모두 볼 수 있으며 홍보를 승인/거절 할 수 있다. 승인한 공연 정보는 관리자가 규칙 / 공연 보기에 추가시켜서 모든 일반 사용자가 볼 수 있게 한다

- 장비 대여 관리 : 일반 사용자가 신청한 장비 대여를 모두 볼 수 있으며 장비 대여를 승인/거절 할 수 있다

- 규칙 / 공연 저장 : 규칙 및 공연 내용등을 정리해서 텍스트로 작성한 후 규칙 및 공연 저장을 누르면 일반 사용자가 규칙 / 공연 보기 페이지에서 최신화된 내용들을 확인 할 수 있다.

- 로그아웃 : 로그아웃을 해서 로그인 / 회원가입 페이지로 넘어간다


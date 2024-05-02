# 각 스테이지는 순차적으로 실행되며, 각 스테이지는 이전 스테이지에서 생성한 파일을 사용할 수 있다.

# 첫번째 스테이지 :
# 어플리케이션을 빌드하고 필요한 파일들만 추출하여 레이어로 만든다.

# 베이스 이미지로 openjdk:17을 사용한다. 이 스테이지를 builder로 명명한다.
FROM openjdk:17 as builder

# 작업 디렉토리를 layer로 설정하고, build/libs 디렉토리에 있는 모든 jar 파일을 컨테이너의 application.jar 파일로 복사한다.
WORKDIR layer
COPY build/libs/*.jar application.jar

# application.jar 파일을 layertools 모드로 실행하여, 레이어를 추출한다.
# Djarmode=layertools 옵션은 jar 파일을 특정 레이어로 분할아여 관리할 수 있게 해준다.
RUN java -Djarmode=layertools -jar application.jar extract

# 두번째 스테이지
# 첫번째 스테이지에서 추출한 레이어를 사용하여 어플리케이션을 실행한다.
# 베이스 이미지로 azul/zulu-openjdk:17을 사용한다.
FROM azul/zulu-openjdk:17

# 패키지 업데이트를 진행하고, curl을 설치한다.
# curl은 HTTP 요청을 보내거나 받을 수 있는 명령어이다.
RUN apt-get update
RUN apt-get install -y curl

# /app/log 디렉토리를 볼륨으로 설정한다.
# 로그 파일을 컨테이너 외부로 빼내어 저장할 수 있게 해준다.
VOLUME /app/log

# 작업 디렉토리를 layer로 설정하고, builder 스테이지에서 추출한 레이어를 복사한다.
WORKDIR layer
# builder 스테이지에서 추출한 dependencies 레이어를 현재 디렉토리로 복사한다.
COPY --from=builder layer/dependencies/ ./
# builder 스테이지에서 추출한 spring-boot-loader 레이어를 현재 디렉토리로 복사한다.
COPY --from=builder layer/spring-boot-loader/ ./
# builder 스테이지에서 추출한 snapshot-dependencies 레이어를 현재 디렉토리로 복사한다.
COPY --from=builder layer/snapshot-dependencies/ ./
# builder 스테이지에서 추출한 application 레이어를 현재 디렉토리로 복사한다.
COPY --from=builder layer/application/ ./

# 컨테이너가 실행될 때, java 명령어로 org.springframework.boot.loader.JarLauncher를 실행한다.
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

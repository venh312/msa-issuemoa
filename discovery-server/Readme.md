## 📌 서비스 디스커버리 아키텍처
- 서비스 등록(service registration): 서비스가 디스커버리 에이전트에 등록하는 방법
- 클라이언트의 서비스 주소 검색(client lookup of service address): 서비스 클라이언트가 서비스 정보를 검색하는 방법
- 정보 공유(information sharing): 노드 간 서비스 정보를 공유하는 방법
- 상태 모니터링(health monitoring): 서비스가 서비스 디스커버리에 상태를 전달하는 방


## 📌도커로 실행하기
### 1. 도커 이미지 빌드
```$ docker build -t issuemoa/eureka .```
- -t 빌드할 이미지의 이름 
- . (현재 위치 기준에서 Dockerfile을 찾는다.)

### 2. 도커 이미지 확인
```$ docker image ls```
```
REPOSITORY      IMAGE ID        CREATED        SIZE     TAG          
issuemoa/eureka 954d2adb5a88    1 hours ago    510MB    latest
```

### 3. 도커 컨테이너 실행
```$ docker run -d --name=issuemoa-eureka -p8761:8761 issuemoa/eureka```
- -d 백그라운드로 실행
- --name 컨테이너명 설정
- -p 포트 설정
- 이미지명

---

## 📌 도커 컴포즈
### 도커 컴포즈 실행
```$ docker-compose up```

### 실행중 컨테이너 확인
```$ docker ps```

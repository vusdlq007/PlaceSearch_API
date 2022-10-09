
## Redis-cli 기동 및 접속 방법.
###
## [설명]
###1) docker 실행 후 Redis-server에 접근하기 위한 docker network 생성.
- 명령어 : docker network create redis-net
###2) Redis-cli 컨테이너 기동. 

- 명령어 -> [docker run -it --network redis-net --rm redis:latest redis-cli -h redis-server-7.0.5]
###3) 이후 cli 접속 성공 후 명령어를 통해 Key,Value 확인.
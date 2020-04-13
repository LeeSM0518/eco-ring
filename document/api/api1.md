# 1차 API 설계

# Spring REST API

## auth(인증)

### login

* **POST /auth/login**
  * 수신 데이터: email(이메일), password(비밀번호)
  * 송신 데이터
    * 성공 시: id(아이디), name(이름), email(이메일), password(비밀번호), authority(권한)
    * 실패 시: message(오류 메시지)
  * 성공 시, Session에 회원 정보를 담고 조회한 데이터 반환
  * 실패 시, 결과 코드 및 메시지 반환

<br>

### logout

* **POST /auth/logout**
  * 수신 데이터: email(이메일), password(비밀번호)
  * 송신 데이터: 
  * 성공 시, Session에서 회원 정보를 지운다.
  * 실패 시, 결과 코드 및 메시지 반환

<br>

### signup

* **POST /auth/signup**
  * 데이터: name(이름), email(이메일), password(비밀번호)
  * 중복되는 이메일이 존재하면 회원가입을 실패한다.
  * 성공 시, DB에 회원정보 저장
  * 실패 시, 결과 코드 및 메시지 반환

<br>

## devices(디바이스)

* **GET /devices**
  * 데이터: deviceId(디바이스 번호), latitude(위도), longitude(경도), address(주소), createdDate(만든 날짜)
  * DB로부터 디바이스 정보를 조회하고 JSON 형태로 반환한다.
* **POST /devices**
  * 데이터: latitude(위도), longitude(경도), address(주소)
  * 성공 시, 디바이스 번호와 결과 메시지 반환
  * 실패 시, 결과 메시지 반환
* **GET /devices/:id**
  * 데이터: latitude(위도), longitude(경도), address(주소), createdDate(만든 날짜)
  * DB로부터 특정 디바이스 정보를 조회하고 반환
* **PUT /devices/:id**
  * 데이터: deviceId(디바이스 번호), latitude(위도), longitude(경도), address(주소)
  * id 정보를 통해 DB의 디바이스 정보를 수정한다.
  * 성공, 실패 모두 결과 메시지 반환
* **DELETE /devices/:id**
  * 데이터: deviceId(디바이스 번호), address(주소)
  * 디바이스 번호와 주소가 같은 디바이스를 제거한다.
  * 성공 시, DB에서 디바이스를 제거하고 결과 메시지 반환
  * 실패 시, 결과 메시지 반환

<br>

## dust(미세먼지)

* **GET /devices/dust**
  * 데이터: deviceId(디바이스 번호), latitude(위도), longitude(경도), address(주소), density(농도), measuredDate(최근 측정한 날짜), severity(심각도)
  * 디바이스와 미세먼지를 JOIN하여 데이터들을 가져온다.
* **POST /devices/dust**
  * 데이터: deviceId(디바이스 번호), density(농도)
  * severity(심각도)는 전송된 density(농도)로부터 계산하여 저장하고, measuredDate 컬럼은 현재 시간으로 할당하고 dustId(먼지 식별자)는 DEFAULT로 추가한다.
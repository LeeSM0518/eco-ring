# 1차 API 설계

# HTTP Status Code

## 성공 응답은 `2XX` 로 응답한다.

* **200** : OK
* **201** : Created
  * POST, PUT에 사용
* **202** : Accepted
  * 클라이언트 요청을 받은 후 요청은 유효하나 서버가 아직 처리하지 않은 경우에 응답한다.
* **204** : No Content
  * 응답 body가 필요 없음.

<br>

## 실패 응답은 `4XX` 로 응답한다.

* **400** : Bad Request
  * 클라이언트 요청이 미리 정의된 파라미터 요구사항을 위반한 경우
* **401** : Unauthorized
* **403** : Forbidden
  * 해당 요청은 유효하나 서버 작업 중 접근이 허용되지 않은 자원을 조회하려는 경우
  * 권한이 없는 경우
* **404** : Not Found
* **405** : Method Not Allowed
  * ex) POST 요청을 해야하는데 GET 요청을 했을 때
* **409** : Conflict
  * 해당 요청의 처리가 비지니스 로직상 불가능하거나 모순이 생긴 경우
  * 모든 자원이 비어있는데 DELETE 요청을 했을 때
* **429** : Too Many Requests

<br>

## `5XX` 에러는 절대 사용자에게 나타내지 마라!

웹 서버가 오류일 때는 500 에러

<br>

# Spring REST API

## auth(인증)

### login

* **POST /auth/login**
  * 수신 데이터: email(이메일), password(비밀번호)
  * 송신 데이터
    * 성공 시: id(아이디), name(이름), email(이메일), password(비밀번호), authority(권한)
      * Session에 회원 정보를 저장하고 데이터 및 `200` 반환
    * 실패 시
      * 일치하는 아이디가 존재하지 않을 때 `404` 반환
      * 비밀번호가 맞지 않을 때  `401` 반환

<br>

### logout

* **POST /auth/logout**
  * 송신 데이터
    * 성공 시:  Session에서 정보 삭제 후 `200` 반환

<br>

### signup

* **POST /auth/signup**
  * 수신 데이터: name(이름), email(이메일), password(비밀번호)
  * 송신 데이터
    * 성공 시: DB에 정보 저장 후 `201` 반환
    * 실패 시: 중복되는 이메일 존재시 `409` 반환

<br>

## devices(디바이스)

* **GET /devices**
  * 송신 데이터
    * 성공 시: id(디바이스 번호), latitude(위도), longitude(경도), address(주소), createdDate(만든 날짜) 및 `200` 반환
* **POST /devices**
  * 수신 데이터: latitude(위도), longitude(경도), address(주소) 와 `201` 반환
  * 송신 데이터
    * 성공 시: deviceId(디바이스 번호) 와 `201` 반환
    * 실패 시: 주소가 중복될 경우 `400` 반환
* **GET /devices/:id**
  * 수신 데이터: deviceId(디바이스 번호)
  * 송신 데이터
    * 성공 시: latitude(위도), longitude(경도), address(주소), createdDate(만든 날짜) 와 `200` 반환
    * 실패 시: 없는 디바이스 번호일 경우 `404` 반환
* **PUT /devices/:id**
  * 수신 데이터: id(디바이스 번호), latitude(위도), longitude(경도), address(주소)
  * 송신 데이터
    * 성공 시: `201` 반환
    * 실패 시: 동일한 디바이스 번호가 존재하지 않으면 `404` 반환
* **DELETE /devices/:id**
  * 수신 데이터: id(디바이스 번호)
  * 송신 데이터
    * 성공 시: `200` 반환
    * 실패 시: 동일한 디바이스 번호가 존재하지 않으면 `404` 반환

<br>

## dust(미세먼지)

* **GET /devices/dust**
  * 송신 데이터: id(디바이스 번호), latitude(위도), longitude(경도), address(주소), density(농도) 와 measuredDate(최근 측정한 날짜), severity(심각도) 및 `200` 반환
    * 각 디바이스 마다 최근에 측정한 값만 가져온다.
  * 디바이스와 미세먼지를 JOIN하여 데이터들을 가져온다.
* **POST /devices/dust**
  * 수신 데이터: id(디바이스 번호), density(농도)
  * severity(심각도)는 전송된 density(농도)로부터 계산하여 저장하고, measuredDate 컬럼은 현재 시간으로 할당하고 dustId(먼지 식별자)는 DEFAULT로 추가한다.
  * 송신 데이터
    * 성공 시: `200` 반환
    * 실패 시: 동일한 디바이스 번호가 존재하지 않으면 `404` 반환
# 1차 DB 설계

## 요구사항

* 디바이스는 식별할 수 있는 번호와 미세먼지를 측정하는 위치(위도, 경도, 주소)와 생성 날짜를 갖고 있다.
* 회원은 식별할 수 있는 번호와 이름, 아이디(이메일), 비밀번호, 권한을 갖고 있다.
* 미세먼지는 식별할 수 있는 번호와 농도, 측정한 디바이스 번호, 측정한 날짜, 심각도를 갖는다.

<br>

## 명명

* **디바이스(Device)**
  * 번호(device_id)
  * 위도(latitude)
  * 경도(longitude)
  * 주소(address)
  * 생성 날짜(created_date)
* **회원(Member)**
  * 번호(member_id)
  * 이름(name)
  * 이메일(email)
  * 비밀번호(password)
  * 권한(authority)
* **미세먼지(Dust)**
  * 번호(dust_id)
  * 농도(density)
  * 측정한 디바이스 번호(measured_device_id)
  * 측정한 날짜(measured_date)
  * 심각도(severity)

<br>

## ER 모델

### 디바이스 & 미세먼지

<img src="https://user-images.githubusercontent.com/43431081/79062428-44a89a80-7cd5-11ea-8a3e-13757321cd10.png" alt="image" style="zoom:50%;" />

<br>

### 회원

![image](https://user-images.githubusercontent.com/43431081/79062447-7de10a80-7cd5-11ea-8fcd-7e81661e5c6f.png)

<br>

## ER Diagram

### 디바이스 & 미세먼지

![image](https://user-images.githubusercontent.com/43431081/79062804-3dcf5700-7cd8-11ea-9278-27916a09910c.png)

<br>

### 회원

<img src="https://user-images.githubusercontent.com/43431081/79062808-458efb80-7cd8-11ea-95c8-eda6d0e84d2b.png" alt="image" style="zoom:33%;" />

<br>

## SQL

* **DEVICE**

  ```sql
  create table DEVICE (
    device_id serial primary key,
    latitude double precision,
    longitude double precision,
    address text,
    created_date timestamp
  );
  ```

* **DUST**

  ```sql
  create table DUST (
    dust_id serial primary key,
    density integer,
    measured_date timestamp,
    severity text,
    measured_device_id integer,
    foreign key (measured_device_id) references device (device_id)
  );
  ```

* **MEMBER**

  ```sql
  create table MEMBER (
    member_id serial primary key,
    name text,
    email text,
    password text,
    authority text
  );
  ```
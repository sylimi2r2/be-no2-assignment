## API 명세서
| 메서드    | 경로                | 설명       | 성공 시 상태코드     |
| ------ | ----------------- | -------- | ------------- |
| POST   | `/schedules`      | 일정 생성    | `201 Created` |
| GET    | `/schedules`      | 일정 목록 조회 | `200 OK`      |
| GET    | `/schedules/{id}` | 특정 일정 조회 | `200 OK`      |
| PATCH  | `/schedules/{id}` | 일정 수정    | `200 OK`      |
| DELETE | `/schedules/{id}` | 일정 삭제    | `200 OK`      |


## ERD
![image](https://github.com/user-attachments/assets/1cc15834-6165-412e-84fd-8233a901275c)

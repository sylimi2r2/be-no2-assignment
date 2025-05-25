## API 명세서
| 기능명      | Method | URL                              | Request                                           | Response                    | 상태코드             |
| -------- | ------ | -------------------------------- | ------------------------------------------------- | --------------------------- | ---------------- |
| 일정 등록    | POST   | `/api/v1/schedules`              | 요청 bodt                       | 등록 정보       | `201 Created`    |
| 일정 단건 조회 | GET    | `/api/v1/schedules/{id}` | Path Variable                       | 단건 응답 정보       | `200 OK`         |
| 전체 일정 조회 | GET    | `/api/v1/schedules`              | 요청 param      | 다건 응답 정보 | `200 OK`         |
| 일정 수정    | PATCH  | `/api/v1/schedules/{id}` | 요청 body + Path Variable | 수정 정보       | `200 OK`         |
| 일정 삭제    | DELETE | `/api/v1/schedules/{id}` | 요청 body + Path Variable | 없음                          | `204 No Content` |

## ERD
![image](https://github.com/user-attachments/assets/1cc15834-6165-412e-84fd-8233a901275c)

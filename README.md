## API 명세서
| 기능명      | Method | URL                              | Request                                           | Response                    | 상태코드             |
| -------- | ------ | -------------------------------- | ------------------------------------------------- | --------------------------- | ---------------- |
| 일정 등록    | POST   | `/api/v1/schedules`              | `ScheduleRequestDto` (JSON)                       | `ScheduleResponseDto`       | `201 Created`    |
| 일정 단건 조회 | GET    | `/api/v1/schedules/{scheduleId}` | Path Variable: `scheduleId`                       | `ScheduleResponseDto`       | `200 OK`         |
| 전체 일정 조회 | GET    | `/api/v1/schedules`              | Query Param: `writer`, `date` (`YYYY-MM-DD`)      | `List<ScheduleResponseDto>` | `200 OK`         |
| 일정 수정    | PATCH  | `/api/v1/schedules/{scheduleId}` | `ScheduleUpdateRequestDto` (JSON) + Path Variable | `ScheduleResponseDto`       | `200 OK`         |
| 일정 삭제    | DELETE | `/api/v1/schedules/{scheduleId}` | `ScheduleDeleteRequestDto` (JSON) + Path Variable | 없음                          | `204 No Content` |

## ERD
![image](https://github.com/user-attachments/assets/1cc15834-6165-412e-84fd-8233a901275c)

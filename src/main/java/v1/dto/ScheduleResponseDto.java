package v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import v1.model.Schedule;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String writer;
    private String title;
    private String contents;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.writer = schedule.getWriter();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }
}

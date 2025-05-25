package v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String writer;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public Schedule(String writer, String password, String title, String contents, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.writer = writer;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}

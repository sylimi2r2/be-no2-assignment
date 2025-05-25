package v1.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import v1.dto.ScheduleResponseDto;
import v1.model.Schedule;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", schedule.getWriter());
        parameters.put("password", schedule.getPassword());
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        parameters.put("createdTime", schedule.getCreatedTime());
        parameters.put("updatedTime", schedule.getUpdatedTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getWriter(), schedule.getTitle(), schedule.getContents());
    }

    public List<Schedule> findSchedulesByDateOrWriter(String writer, String date) {
        String sql = "select * from schedule where 1=1";

        List<Object> params = new ArrayList<>();

        if (writer != null) {
            sql += " and writer = ?";
            params.add(writer);
        }

        if (date != null) {
            sql += " and date(updatedTime) = ?";
            params.add(date);
        }

        sql += " order by updatedTime desc";

        return jdbcTemplate.query(sql, params.toArray(), scheduleRowMapper());
    }

    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(), id);

        return result.stream().findAny();
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("writer"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdTime").toLocalDateTime(),
                        rs.getTimestamp("updatedTime").toLocalDateTime()
                );
            }
        };
    }

    public int updateSchedule(Long id, String writer, String contents, LocalDateTime updatedTime) {
        return jdbcTemplate.update("update schedule set writer = ?, contents = ?, updatedTime = ? where id = ?", writer, contents, updatedTime, id);
    }

    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id = ?", id);
    }

}

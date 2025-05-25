package v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import v1.dto.ScheduleDeleteRequestDto;
import v1.dto.ScheduleRequestDto;
import v1.dto.ScheduleResponseDto;
import v1.dto.ScheduleUpdateRequestDto;
import v1.model.Schedule;
import v1.repository.ScheduleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();

        Schedule schedule = new Schedule(requestDto.getWriter(), requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents(), now, now);

        return scheduleRepository.saveSchedule(schedule);
    }

    public List<ScheduleResponseDto> findSchedulesByDateOrWriter(String writer, String date) {
        return scheduleRepository.findSchedulesByDateOrWriter(writer, date).stream()
                .map(s -> new ScheduleResponseDto(
                        s.getId(), s.getWriter(), s.getTitle(), s.getContents()))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new ScheduleResponseDto(optionalSchedule.get());
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto requestDto) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (!optionalSchedule.get().getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Different password");
        }

        String writer = optionalSchedule.get().getWriter();
        String contents = optionalSchedule.get().getContents();

        if (requestDto.getWriter() != null)
            writer = requestDto.getWriter();

        if (requestDto.getContents() != null)
            contents = requestDto.getContents();

        scheduleRepository.updateSchedule(id, writer, contents, LocalDateTime.now());

        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }

    public void deleteSchedule(Long id, ScheduleDeleteRequestDto requestDto) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        if (!optionalSchedule.get().getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Different password");
        }

        scheduleRepository.deleteSchedule(id);
    }
}

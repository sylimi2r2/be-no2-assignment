package v1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v1.dto.ScheduleDeleteRequestDto;
import v1.dto.ScheduleRequestDto;
import v1.dto.ScheduleResponseDto;
import v1.dto.ScheduleUpdateRequestDto;
import v1.service.ScheduleService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    
    private final ScheduleService scheduleService;
    
    private ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.saveSchedule(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedule(@RequestParam(required = false) String writer, @RequestParam(required = false) String date) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDateOrWriter(writer, date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findScheduleById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleUpdateRequestDto updateRequestDto) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, updateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleDeleteRequestDto deleteRequestDto) {
        scheduleService.deleteSchedule(id, deleteRequestDto);
        return ResponseEntity.ok("deleted complete");
    }
}

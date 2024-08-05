package dev.changuii.project.controller;


import dev.changuii.project.dto.ReportDTO;
import dev.changuii.project.dto.response.ReportResponseDTO;
import dev.changuii.project.service.IdempotentService;
import dev.changuii.project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/wanted/{wantedId}/report")
public class ReportController {


    private final ReportService reportService;
    private final IdempotentService idempotentService;
    private final String NAME = "REPORT";

    public ReportController(
            @Autowired ReportService reportService,
            @Autowired IdempotentService idempotentService
    ){
        this.idempotentService=idempotentService;
        this.reportService = reportService;
    }

    // todo : 이메일 가져오는 로직 변경해야함

    @PostMapping()
    public ResponseEntity<ReportResponseDTO> createReport(
            @RequestPart("dto")ReportDTO reportDTO,
            @RequestPart("image")MultipartFile image,
            @PathVariable("wantedId")Long wantedId
            ) throws IOException {
        this.idempotentService.isValidIdempotent(Arrays.asList(new String[]{NAME, "POST", reportDTO.getUserEmail()}));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.reportService.createReport(reportDTO, wantedId, image));
    }

    @GetMapping()
    public ResponseEntity<List<ReportResponseDTO>> readAllByWantedId(
            @PathVariable("wantedId") Long wantedId
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.reportService.readAllReportByWantedId(wantedId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> readByReportId(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.reportService.readReportById(id));
    }


    @GetMapping("/all/{userEmail}")
    public ResponseEntity<List<ReportResponseDTO>> readAllByUserEmail(
            @PathVariable("userEmail") String userEmail
    ){
        //todo : query dsl로 해당 유저가 조회할 수 있는 모든 제보글을 조회해야함
        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(
            @PathVariable("id") Long id
    ){
        this.reportService.deleteReportById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}

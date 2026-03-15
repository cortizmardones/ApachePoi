package com.example.demo.controller;

import com.example.demo.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/v1/excel")
@RequiredArgsConstructor
@Slf4j
public class ExcelController {

    private final ExcelService excelService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH:mm:ss");

    @GetMapping("/generateExcel")
    public ResponseEntity<byte[]> generateExcel() {

        log.info("Generando reporte archivo Excel");

        byte[] excelBytes = excelService.generateExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "Reporte_ " + LocalDateTime.now().format(formatter) + ".xlsx");
        headers.setContentLength(excelBytes.length);

        log.info("Reporte archivo Excel finalizado");

        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }

}

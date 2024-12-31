package com.example.demo.controller;

import com.example.demo.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @GetMapping("/generateExcel")
    public ResponseEntity<byte[]> generateExcel() {

        byte[] excelBytes = excelService.generateExcel();

        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "reporte.xlsx");
        headers.setContentLength(excelBytes.length);

        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        //return new ResponseEntity<>(excelBytes, HttpStatus.OK);
    }

}

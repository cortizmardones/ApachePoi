package com.example.demo.service;

import com.example.demo.dto.Datos;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    @Override
    public byte[] generateExcel() {

        try {

            //HSSFWorkbook: .xls (Excel 97-2003)
            //XSSFWorkbook: .xlsx (2007 Y POSTERIORES)
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Datos");

            // TITULOS - ROW --> HACIA LATERAL
            String[] columnas = {"ID", "Nombre", "Correo Electrónico"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
            }

            // DATOS
            List<Datos> datosList = new ArrayList<Datos>();
            datosList.add(new Datos(1L, "Carlos", "cortizmardones@gmail.com"));
            datosList.add(new Datos(2L, "Walter", "etejeda@gmail.com"));

            int cont = 1;
            for (Datos datos : datosList) {
                Row row = sheet.createRow(cont++);
                row.createCell(0).setCellValue(datos.getId());
                row.createCell(1).setCellValue(datos.getName());
                row.createCell(2).setCellValue(datos.getEmail());
            }

            // AUTOSIZE()
            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            workbook.close();
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            log.error("Error al generar archivo Excel : {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

}

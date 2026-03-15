package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.utils.Messages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileJobService {
    public String convertExcelToTxt(MultipartFile multipartFile) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream is = multipartFile.getInputStream();
            Workbook workbook = new XSSFWorkbook(is)){
            
                Sheet sheet = workbook.getSheetAt(0);
                for(Row row: sheet) {
                    for(Cell cell: row) {
                       stringBuilder.append(getCellValueAsString(cell)).append("\t");
                    }
                    stringBuilder.append("\n");
                }
            } 
        return Messages.SUCCESS;
    }

    public String convertAndSave(MultipartFile file) throws IOException {
        String context = convertExcelToTxt(file);
        String fileName = generateFileName();
        Path path = Paths.get("src/main/resources/static/" + fileName);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        } 
        Files.write(path, context.getBytes(StandardCharsets.UTF_8));
        return Messages.SUCCESS;
    }

    // bổ trợ
    protected String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    protected String generateFileName() {
        return "export_file" + System.currentTimeMillis() + ".txt";
    }
}

package org.llamagas.servicelayer.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Service
public class ConvertService {

    public ResponseEntity<byte[]> convert(MultipartFile file) {
        try {
            // Leer el PDF
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setSortByPosition(true);
            String pdfText = pdfStripper.getText(document);
            document.close();

            // Procesar encabezado y tabla
            List<String> headerData = extractHeaderData(pdfText);
            List<String[]> tableData = extractTableData(pdfText);

            // Escribir datos en un archivo Excel en memoria
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            writeToExcel(headerData, tableData, outputStream);

            // Configurar la respuesta con el archivo Excel
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=EstadoCuenta.xlsx");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private List<String> extractHeaderData(String pdfText) {
        List<String> header = new ArrayList<>();
        String[] lines = pdfText.split("\\r?\\n");

        for (String line : lines) {
            if (line.contains("BANCO DE LA NACION") || line.contains("RUC :")
                    || line.contains("ESTADOS DE CUENTAS") || line.contains("CLIENTE :")
                    || line.contains("NRO :")
                    || line.contains("AGENCIA :") || line.contains("EMISOR :")) {
                header.add(line.trim());
            }
            if (line.contains("FECHA :")) {
                break;
            }
        }
        return header;
    }

    private List<String[]> extractTableData(String pdfText) {
        List<String[]> tableData = new ArrayList<>();
        String[] lines = pdfText.split("\\r?\\n");

        boolean tableStarted = false;
        String headerLine = "MES DIA CAJERO AGENCIA TRANSACCION IMPORTE";

        for (String line : lines) {
            if (line.contains(headerLine)) {
                tableStarted = true;
                if (tableData.isEmpty()) {
                    tableData.add(headerLine.split("\\s+"));
                }
                continue;
            }

            if (tableStarted && !line.trim().isEmpty()) {
                if (line.contains("SALDO INICIAL") || line.contains("SALDO FINAL")) {
                    tableData.add(new String[]{line.trim()});
                } else {
                    try {
                        String mes = line.substring(0, 3).trim();
                        String dia = line.substring(4, 6).trim();
                        String cajero = line.substring(7, 11).trim();
                        String agencia = line.substring(12, 16).trim();
                        String transaccion = line.substring(17, 54).trim();
                        String importe = line.substring(55).trim();

                        tableData.add(new String[]{mes, dia, cajero, agencia, transaccion, importe});
                    } catch (StringIndexOutOfBoundsException e) {
                        System.err.println("Error al procesar la línea: " + line);
                    }
                }
            }
        }
        return tableData;
    }

    private void writeToExcel(List<String> headerData, List<String[]> tableData, OutputStream outputStream) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Estado de Cuenta");
            int rowNum = 0;

            for (String headerLine : headerData) {
                Row row = sheet.createRow(rowNum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(headerLine);
                sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 5));
            }

            rowNum++;
            for (String[] rowData : tableData) {
                Row row = sheet.createRow(rowNum++);
                if (rowData.length == 1) {
                    Cell cell = row.createCell(0);
                    cell.setCellValue(rowData[0]);
                    sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 5));
                } else {
                    for (int colNum = 0; colNum < rowData.length; colNum++) {
                        row.createCell(colNum).setCellValue(rowData[colNum]);
                    }
                }
            }
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(outputStream);
        }
    }
}

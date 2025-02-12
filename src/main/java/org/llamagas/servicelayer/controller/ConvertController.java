package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.service.ConvertService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/convert")
public class ConvertController {

    private final ConvertService convertService;

    public ConvertController(ConvertService convertService) {
        this.convertService = convertService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('PERMISO_CONVERTIR_ARCHIVO')")
    public ResponseEntity<byte[]> convertPdfToExcel(@RequestParam("file") MultipartFile file) {
        return convertService.convert(file);
    }

}

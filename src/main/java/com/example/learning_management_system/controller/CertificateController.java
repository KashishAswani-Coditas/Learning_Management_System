package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.responseDTO.CertificateResponseDTO;
import com.example.learning_management_system.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping("/generate/{courseId}")
    private CertificateResponseDTO generateCertificate(@PathVariable String courseId){
        return certificateService.generateCertificate(courseId);
    }

    @GetMapping("/my")
    public List<CertificateResponseDTO> getMyCertificated(){
        return certificateService.getMyCertificates();
    }
}

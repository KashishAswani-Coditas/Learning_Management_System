package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.requestDTO.AdminRequestDTO;
import com.example.learning_management_system.dto.responseDTO.AdminResponseDTO;
import com.example.learning_management_system.entity.Admin;
import com.example.learning_management_system.mapper.AdminMapper;
import com.example.learning_management_system.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminResponseDTO addAdmin(AdminRequestDTO admin) {
        Admin originalAdmin = adminMapper.toEntity(admin);
        return adminMapper.toDto(adminRepository.save(originalAdmin));
    }
}

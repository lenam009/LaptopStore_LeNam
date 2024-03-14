package com.lenam.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lenam.laptopshop.domain.Role;
import com.lenam.laptopshop.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRole() {
        return this.roleRepository.findAll();
    }
}

package com.example.MedicalQuotes.Controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalQuotes.Dto.RoleDTO;
import com.example.MedicalQuotes.Services.RoleService;
@RestController
public class RoleController {
	@Autowired
	RoleService roleService;

	@GetMapping("/roles")
	public Collection<RoleDTO> roles() {
		return roleService.getRoles();
	}

	@GetMapping("/roles/{id}")
	public RoleDTO getRole(@PathVariable("id") Long id) {

		return roleService.getRoleById(id);

	}

	@PostMapping("/roles/create")
	public RoleDTO createRole(@RequestBody RoleDTO role) {

		return roleService.createRole(role);

	}

	@PutMapping("/roles/{id}")
	public RoleDTO updateRole(@RequestBody RoleDTO role, @PathVariable Long id) {

		return roleService.updateRole(role, id);

	}


}

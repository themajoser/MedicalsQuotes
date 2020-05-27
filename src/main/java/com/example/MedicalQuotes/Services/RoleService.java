package com.example.MedicalQuotes.Services;

import java.util.Collection;

import com.example.MedicalQuotes.Dto.RoleDTO;

public interface RoleService {

	public abstract Collection<RoleDTO> getRoles();

	public abstract RoleDTO getRoleById(Long id);

	public abstract RoleDTO createRole(RoleDTO role);

	public abstract RoleDTO updateRole(RoleDTO role, Long id);

}

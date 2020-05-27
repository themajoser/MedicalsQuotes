package com.example.MedicalQuotes.ServicesImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalQuotes.Dto.RoleDTO;
import com.example.MedicalQuotes.Models.Role;
import com.example.MedicalQuotes.Repositories.RoleRepository;
import com.example.MedicalQuotes.Services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Collection<RoleDTO> getRoles() {

		try {
			ModelMapper modelMapper = new ModelMapper();
			List<Role> rolesModelo = roleRepository.findAll();
			Collection<RoleDTO> rolesDTOList = new ArrayList<>();
			rolesModelo.forEach(role -> rolesDTOList.add(modelMapper.map(role, RoleDTO.class)));

			return rolesDTOList;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public RoleDTO getRoleById(Long id) {

		try {
			ModelMapper modelMapper = new ModelMapper();
			Role roleModel = roleRepository.findById(id).get();
		
			RoleDTO RoleDTO = modelMapper.map(roleModel, RoleDTO.class);

			return RoleDTO;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public RoleDTO createRole(RoleDTO roleDTO) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			Role role = roleRepository.save(modelMapper.map(roleDTO, Role.class));
			return modelMapper.map(role, RoleDTO.class);

		} catch (Exception e) {
			return null;
		}
	}

	public Role checkAttributes(RoleDTO role, Role roleBBDD) {
		if (role.getName() != null) {
			roleBBDD.setName(role.getName());
		}

		return roleBBDD;

	}

	@Override
	public RoleDTO updateRole(RoleDTO role, Long id) {

		ModelMapper modelMapper = new ModelMapper();
		Role roleBBDD = new Role();
		roleBBDD = roleRepository.findById(id).get();
		if (roleBBDD == null) {
			return null;
		}
		Role Role = roleRepository.save(checkAttributes(role, roleBBDD));

		return modelMapper.map(Role, RoleDTO.class);

	}





}

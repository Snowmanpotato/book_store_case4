package com.cg.service.role;
import com.cg.model.Role;
import com.cg.model.dto.RoleDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IRoleService extends IGeneralService<Role> {
    List<RoleDTO> getAllRoleDTO();

    Role findByName(String name);
}
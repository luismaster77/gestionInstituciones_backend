package com.co.lep.gestion.estudiantes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.lep.gestion.estudiantes.menu.entity.MenuEntity;
import com.co.lep.gestion.estudiantes.menu.impl.service.MenuService;
import com.co.lep.gestion.estudiantes.menu.repository.RoleMenuRepository;
import com.co.lep.gestion.estudiantes.repository.RoleRepository;
import com.co.lep.gestion.estudiantes.security.entity.Role;

@SpringBootTest
class MenuTest {

	@InjectMocks
    private MenuService menuService;

    @Mock
    private RoleRepository roleRepository;
    
    @Mock
    private RoleMenuRepository roleMenuRepository;


    //@Test
    void getMenusByRole() {
        MockitoAnnotations.openMocks(this);

        Role role = new Role();
        role.setName("ADMIN");
        role.setMenus(Collections.emptyList());

        when(roleRepository.findByName("ADMIN")).thenReturn(role);

        List<MenuEntity> menus = menuService.findMenusByRoleName("ADMIN");
        assertEquals(0, menus.size());
    }

}

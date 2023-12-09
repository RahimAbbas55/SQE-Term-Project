package com.jtspringproject.JtSpringProject.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//class UserControllerTest {
//
//    @Test
//    void registerUser() {
//    }
//
//    @Test
//    void buy() {
//    }
//
//    @Test
//    void userlogin() {
//    }
//
//    @Test
//    void testUserlogin() {
//    }
//
//    @Test
//    void getproduct() {
//    }
//
//    @Test
//    void newUseRegister() {
//    }
//}



import com.jtspringproject.JtSpringProject.controller.UserController;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private userService userService;

    @Mock
    private productService productService;

// ...



    @Test
    void userlogin() {
        String username = "testUser";
        String password = "testPassword";
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword(password);

        when(userService.checkLogin(username, password)).thenReturn(mockUser);

        Model model = mock(Model.class);
        ModelAndView modelAndView = userController.userlogin(username, password, model, mockResponse);

        assertEquals("index", modelAndView.getViewName());
        assertEquals(username, ((User) modelAndView.getModel().get("user")).getUsername());
    }



    @Test
    void getproduct() {
        ModelAndView modelAndView = userController.getproduct();
        assertEquals("uproduct", modelAndView.getViewName());
    }

    @Test
    void newUseRegister() {
        User user = new User();
        user.setEmail("test@example.com");

        String result = userController.newUseRegister(user);

        assertEquals("redirect:/", result);
        verify(userService, times(1)).addUser(user);
    }

    @Test
    void testTest() {
        ModelMap modelMap = new ModelMap();
        String result = userController.Test((Model) modelMap);
        assertEquals("test", result);
        assertEquals("jay gajera", modelMap.get("author"));
        assertEquals(40, modelMap.get("id"));
        assertEquals(ArrayList.class, modelMap.get("f").getClass());
    }

    @Test
    void testTest2() {
        ModelAndView modelAndView = userController.Test2();
        assertEquals("test2", modelAndView.getViewName());
        assertEquals("jay gajera 17", modelAndView.getModel().get("name"));
        assertEquals(40, modelAndView.getModel().get("id"));
        assertEquals(ArrayList.class, modelAndView.getModel().get("marks").getClass());
    }
}

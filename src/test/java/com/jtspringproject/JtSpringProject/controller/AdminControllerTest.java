//package com.jtspringproject.JtSpringProject.controller;
////
//import org.junit.jupiter.api.Test;
////
////import static org.junit.jupiter.api.Assertions.*;
////
////class AdminControllerTest {
////
////    @Test
////    void returnIndex() {
////    }
////
////    @Test
////    void index() {
////    }
////
////    @Test
////    void adminlogin() {
////    }
////
////    @Test
////    void adminHome() {
////    }
////
////    @Test
////    void adminlog() {
////    }
////
////    @Test
////    void testAdminlogin() {
////    }
////
////    @Test
////    void getcategory() {
////    }
////
////    @Test
////    void addCategory() {
////    }
////
////    @Test
////    void removeCategoryDb() {
////    }
////
////    @Test
////    void updateCategory() {
////    }
////
////    @Test
////    void getproduct() {
////    }
////
////    @Test
////    void addProduct() {
////    }
////
////    @Test
////    void testAddProduct() {
////    }
////
////    @Test
////    void updateproduct() {
////    }
////
////    @Test
////    void updateProduct() {
////    }
////
////    @Test
////    void removeProduct() {
////    }
////
////    @Test
////    void postproduct() {
////    }
////
////    @Test
////    void getCustomerDetail() {
////    }
////
////    @Test
////    void profileDisplay() {
////    }
////
////    @Test
////    void updateUserProfile() {
////    }
////}
//
////
////import static org.junit.jupiter.api.Assertions.assertEquals;
////import static org.mockito.ArgumentMatchers.anyString;
////import static org.mockito.Mockito.*;
////
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.MockitoAnnotations;
////import org.springframework.web.servlet.ModelAndView;
////
////import com.jtspringproject.JtSpringProject.controller.AdminController;
////import com.jtspringproject.JtSpringProject.models.User;
////import com.jtspringproject.JtSpringProject.services.userService;
////
////public class AdminControllerTest {
////
////    @InjectMocks
////    private AdminController adminController;
////
////    @Mock
////    private userService userService;
////
////    @BeforeEach
////    public void setUp() {
////        MockitoAnnotations.initMocks(this);
////    }
////
////    @Test
////    public void testAdminLogin() {
////        String username = "admin";
////        String password = "password";
////        String role = "ROLE_ADMIN";
////        User mockUser = new User();
////        mockUser.setUsername(username);
////        mockUser.setPassword(password);
////        mockUser.setRole(role);
////        when(userService.checkLogin(anyString(), anyString())).thenReturn(mockUser);
////
////        ModelAndView modelAndView = adminController.adminlogin(username, password);
////
////        assertEquals("adminHome", modelAndView.getViewName());
////
////        assertEquals(username, ((User) modelAndView.getModel().get("admin")).getUsername());
////        assertEquals(password, ((User) modelAndView.getModel().get("admin")).getPassword());
////        assertEquals(role, ((User) modelAndView.getModel().get("admin")).getRole());
////    }
////}
//
//
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//
//import com.jtspringproject.JtSpringProject.controller.AdminController;
//import com.jtspringproject.JtSpringProject.models.User;
//import com.jtspringproject.JtSpringProject.services.userService;
//
//public class AdminControllerTest {
//
//    @InjectMocks
//    private AdminController adminController;
//
//    @Mock
//    private userService userService;
//
//    @Mock
//    private Connection mockConnection;
//
//    @Mock
//    private PreparedStatement mockPreparedStatement;
//
//    @Mock
//    private ResultSet mockResultSet;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testProfileDisplay() throws Exception {
//        // Mock data
//        String username = "admin";
//        User mockUser = new User();
//        mockUser.setUsername(username);
//
//        // Mocking behavior of userService
//        when(userService.checkLogin(anyString(), anyString())).thenReturn(mockUser);
//
//        // Mocking behavior of database interaction
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getInt(anyInt())).thenReturn(1);
//        when(mockResultSet.getString("username")).thenReturn(username);
//
//        // Call the method to test
//        String viewName = adminController.profileDisplay((Model) new ModelMap());
//
//        // Assertions
//        assertEquals("updateProfile", viewName);
//    }
//}
//
//
//

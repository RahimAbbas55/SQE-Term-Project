package com.jtspringproject.JtSpringProject.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerDiffblueTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private categoryService categoryService;

    @MockBean
    private productService productService;

    @MockBean
    private userService userService;

    /**
     * Method under test: {@link AdminController#addCategory(String)}
     */
    @Test
    void testAddCategory() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.addCategory(Mockito.<String>any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/categories")
                .param("categoryname", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("categories"));
    }

    /**
     * Method under test: {@link AdminController#addCategory(String)}
     */
    @Test
    void testAddCategory2() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("foo");
        when(categoryService.addCategory(Mockito.<String>any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/categories")
                .param("categoryname", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("categories"));
    }

    /**
     * Method under test:
     * {@link AdminController#addProduct(String, int, int, int, int, String, String)}
     */
    @Test
    void testAddProduct3() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.getCategory(anyInt())).thenReturn(category);

        Category category2 = new Category();
        category2.setId(1);
        category2.setName("Name");

        Product product = new Product();
        product.setCategory(category2);
        product.setDescription("The characteristics of someone or something");
        product.setId(1);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(1);
        product.setQuantity(1);
        product.setWeight(3);
        when(productService.addProduct(Mockito.<Product>any())).thenReturn(product);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/admin/products/add");
        MockHttpServletRequestBuilder paramResult = postResult.param("categoryid", String.valueOf(1))
                .param("description", "foo")
                .param("name", "foo");
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("price", String.valueOf(1))
                .param("productImage", "foo");
        MockHttpServletRequestBuilder paramResult3 = paramResult2.param("quantity", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult3.param("weight", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/products"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }

    /**
     * Method under test:  {@link AdminController#addProduct()}
     */
    @Test
    void testAddProduct() throws Exception {
        when(categoryService.getCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/products/add");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
                .andExpect(MockMvcResultMatchers.view().name("productsAdd"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("productsAdd"));
    }

    /**
     * Method under test:  {@link AdminController#addProduct()}
     */
    @Test
    void testAddProduct2() throws Exception {
        when(categoryService.getCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/products/add");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
                .andExpect(MockMvcResultMatchers.view().name("productsAdd"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("productsAdd"));
    }

    /**
     * Method under test: {@link AdminController#adminHome(Model)}
     */
    @Test
    void testAdminHome() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/Dashboard");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/loginvalidate"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/loginvalidate"));
    }

    /**
     * Method under test: {@link AdminController#adminHome(Model)}
     */
    @Test
    void testAdminHome2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/Dashboard");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/loginvalidate"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/loginvalidate"));
    }
}

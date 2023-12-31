package com.jtspringproject.JtSpringProject.controller;
import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import com.jtspringproject.JtSpringProject.services.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.cartService;



@Controller
public class UserController{

	@Autowired
	private userService userService;

	@Autowired
	private productService productService;

	@Autowired
	private cartService cartService;




	@GetMapping("/register")
	public String registerUser()
	{
		return "register";
	}

	@GetMapping("/buy")
	public String buy()
	{
		return "buy";
	}


	@GetMapping("/")
	public String userlogin(Model model) {

		return "userLogin";
	}
	@RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
	public ModelAndView userlogin(@RequestParam("username") String username,
								  @RequestParam("password") String pass,
								  Model model,
								  HttpServletResponse res) {

		try {
			User u = this.userService.checkLogin(username, pass);

			if (u != null && u.getUsername().equals(username)) {
				usernameforclass = u.getUsername();
				res.addCookie(new Cookie("username", u.getUsername()));
				ModelAndView mView = new ModelAndView("index");
				mView.addObject("user", u);

				List<Product> products = this.productService.getProducts();

				if (products.isEmpty()) {
					mView.addObject("msg", "No products are available");
				} else {
					mView.addObject("products", products);
				}
				return mView;
			} else {
				ModelAndView mView = new ModelAndView("userLogin");
				mView.addObject("msg", "Please enter correct email and password");
				return mView;
			}
		} catch (Exception e) {
			ModelAndView errorView = new ModelAndView("userLogin");
			errorView.addObject("msg", "An error occurred. Please try again.");
			return errorView;
		}
	}



//	@GetMapping("/user/products")
//	public ModelAndView getproduct() {
//
//		ModelAndView mView = new ModelAndView("uproduct");
//
//		List<Product> products = this.productService.getProducts();
//
//		if(products.isEmpty()) {
//			mView.addObject("msg","No products are available");
//		}else {
//			mView.addObject("products",products);
//		}
//
//		return mView;
//	}
@GetMapping("/user/products")
public ModelAndView getUserProducts(@RequestParam(name = "sort", required = false) String sort) {
	ModelAndView mView = new ModelAndView("uproduct");

	List<Product> products;
	if ("price".equals(sort) || "name".equals(sort)) {
		// Sort products based on the provided sort parameter
		products = productService.getProducts(sort);
	} else {
		// If no sorting parameter is provided or an invalid one is provided, get unsorted products
		products = productService.getProducts();
	}

	if (products.isEmpty()) {
		mView.addObject("msg", "No products are available");
	} else {
		mView.addObject("products", products);
	}

	return mView;
}


	@RequestMapping(value = "newuserregister", method = RequestMethod.POST)
	public String newUseRegister(@ModelAttribute User user)
	{

		System.out.println(user.getEmail());
		user.setRole("ROLE_NORMAL");
		this.userService.addUser(user);

		return "redirect:/";
	}



	//for Learning purpose of model
	@GetMapping("/test")
	public String Test(Model model)
	{
		System.out.println("test page");
		model.addAttribute("author","jay gajera");
		model.addAttribute("id",40);

		List<String> friends = new ArrayList<String>();
		model.addAttribute("f",friends);
		friends.add("xyz");
		friends.add("abc");

		return "test";
	}

	// for learning purpose of model and view ( how data is pass to view)

	@GetMapping("/test2")
	public ModelAndView Test2()
	{
		System.out.println("test page");
		//create modelandview object
		ModelAndView mv=new ModelAndView();
		mv.addObject("name","jay gajera 17");
		mv.addObject("id",40);
		mv.setViewName("test2");

		List<Integer> list=new ArrayList<Integer>();
		list.add(10);
		list.add(25);
		mv.addObject("marks",list);
		return mv;


	}


//	@GetMapping("carts")
//	public ModelAndView  getCartDetail()
//	{
//		ModelAndView mv= new ModelAndView();
//		List<Cart>carts = cartService.getCarts();
//	}


	//Search bar functionality implementation
	@GetMapping("/search")
	public ModelAndView searchProducts(@RequestParam(name = "category", required = false) String categoryName) {
		ModelAndView mView = new ModelAndView("uproduct");

		if (categoryName != null && !categoryName.isEmpty()) {
			// Search for products in the specified category
			List<Product> products = productService.getProductsByCategory(categoryName);

			if (products.isEmpty()) {
				mView.addObject("msg", "No products found in the category: " + categoryName);
			} else {
				mView.addObject("products", products);
			}
		} else {
			mView.addObject("msg", "Please enter a valid category for search");
		}

		return mView;
	}

	@GetMapping("/userhome")
	public ModelAndView userHome() {
		ModelAndView mView  = new ModelAndView("index");
		List<Product> products = this.productService.getProducts();

		if (products.isEmpty()) {
			mView.addObject("msg", "No products are available");
		} else {
			mView.addObject("products", products);
		}
		return mView;
	}


	String usernameforclass = "";
	@GetMapping("profileDisplay")
	public String profileDisplay(Model model) {
		String displayusername, displaypassword, displayemail, displayaddress;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommjava","root","admin");
			PreparedStatement stmt = con.prepareStatement("select * from customer where username = ?;");
			stmt.setString(1, usernameforclass);
			ResultSet rst = stmt.executeQuery();

			if (rst.next()) {
				int userid = rst.getInt(1);
				displayusername = rst.getString(6);
				displayemail = rst.getString(3);
				displaypassword = rst.getString(4);
				displayaddress = rst.getString(2);
				model.addAttribute("userid", userid);
				model.addAttribute("username", displayusername);
				model.addAttribute("email", displayemail);
				model.addAttribute("password", displaypassword);
				model.addAttribute("address", displayaddress);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("Hello");
		return "updateProfile";
	}


	@GetMapping("carts")
	public String  getCartDetail()
	{
		return "cartproduct";
	}

	@GetMapping("/about")
	public String aboutUs() {
		return "aboutus";
	}


//
//	@PostMapping("/add-to-cart")
//	public String addToCart(@RequestParam("productId") Long productId,
//							@RequestParam("quantity") int quantity) {
//		// Get the current user's username (you might want to use Spring Security for this)
//		String username = getUsernameForLoggedInUser();
//
//		// Call the CartService to add the item to the cart
//		this.cartService.addToCart(username, productId, quantity);
//
//		return "redirect:/user/products";
//	}
//


//	@GetMapping("/user/products")
//	public ModelAndView getUserProducts(@RequestParam(name = "sort", required = false) String sort) {
//		ModelAndView mView = new ModelAndView("uproduct");
//
//		List<Product> products;
//		if ("price".equals(sort) || "name".equals(sort)) {
//			// Sort products based on the provided sort parameter
//			products = productService.getProducts(sort);
//		} else {
//			// If no sorting parameter is provided or an invalid one is provided, get unsorted products
//			products = productService.getProducts();
//		}
//
//		if (products.isEmpty()) {
//			mView.addObject("msg", "No products are available");
//		} else {
//			mView.addObject("products", products);
//		}
//
//		return mView;
//	}





}
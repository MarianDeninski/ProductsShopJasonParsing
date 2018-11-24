package productshop.web.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productshop.domain.dtos.binding.CategorySeedDto;
import productshop.domain.dtos.binding.ProductInRangeDto;
import productshop.domain.dtos.binding.ProductSeedDto;
import productshop.domain.dtos.binding.UserSeedDto;
import productshop.domain.dtos.view.AllUsersView;
import productshop.domain.dtos.view.CategoryView;
import productshop.domain.dtos.view.UserView;
import productshop.service.CategoryService;
import productshop.service.ProductService;
import productshop.service.UserService;
import productshop.util.FileIOUtil;
import productshop.util.FileWriterUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductShopController implements CommandLineRunner {

    private final static String USER_FILE_PATH = "src/main/resources/files/users.json";
    private final static String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.json";
    private final static String PRODUCTS_FILE_PATH = "src/main/resources/files/products.json";

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;
    private final Gson gson;
    private final FileWriterUtil fileWriterUtil;


    @Autowired
    public ProductShopController(UserService userService, CategoryService categoryService, ProductService productService, FileIOUtil fileIOUtil, Gson gson, FileWriterUtil fileWriterUtil) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;

        this.fileWriterUtil = fileWriterUtil;
    }

    @Override
    public void run(String... args) throws Exception {
       // this.seedUsers();
       // this.seedCategories();
       // this.seedProducts();
        this.productsInRange();
        this.printSuccessfullyUsers();
        this.printCategoriesOrderByProducts();

        this.printAllUsers();


    }

    private void printAllUsers() throws IOException {

        AllUsersView usersView = this.userService.userWithSoldProducts();
        this.fileWriterUtil.writeFile(gson.toJson(usersView),"all-users-sold");
    }

    private void printCategoriesOrderByProducts() throws IOException {

       List<CategoryView> categories =  this.categoryService.findCategoriesOrderByCountProducts();
       this.fileWriterUtil.writeFile(gson.toJson(categories),"categories-order-by-products");
    }

    private void printSuccessfullyUsers() throws IOException {

        List<UserView> list = this.userService.successfullySoldProducts();
        this.fileWriterUtil.writeFile(gson.toJson(list),"products-in-price-range");

    }
    private void productsInRange() throws IOException {
        List<ProductInRangeDto> productInRangeDtos = this.productService.productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        this.fileWriterUtil.writeFile(gson.toJson(productInRangeDtos),"products-in-range");

    }


    private void seedUsers() throws IOException {
        String usersFileContent = this.fileIOUtil.readFile(USER_FILE_PATH);

        UserSeedDto[] userSeedDtos = this.gson.fromJson(usersFileContent, UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategories() throws IOException {
        String categoriesFileContent = this.fileIOUtil.readFile(CATEGORIES_FILE_PATH);

        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(categoriesFileContent, CategorySeedDto[].class);

        this.categoryService.seedCategories(categorySeedDtos);
    }

    private void seedProducts() throws IOException {
        String productsFileContent = this.fileIOUtil.readFile(PRODUCTS_FILE_PATH);

        ProductSeedDto[] productSeedDtos = this.gson.fromJson(productsFileContent, ProductSeedDto[].class);

        this.productService.seedProducts(productSeedDtos);
    }


}

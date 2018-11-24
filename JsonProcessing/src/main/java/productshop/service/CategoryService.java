package productshop.service;

import productshop.domain.dtos.binding.CategorySeedDto;
import productshop.domain.dtos.view.CategoryView;

import java.util.List;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<CategoryView> findCategoriesOrderByCountProducts();
}

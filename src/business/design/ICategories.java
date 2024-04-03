package business.design;

import business.model.Categories;

import java.util.List;

public interface ICategories extends IGenericDesgin<Categories>{
    List<Categories> getAllCategories();
    void updateStatus(int categoryId , boolean newStatus);
    Categories findById(int id);
}

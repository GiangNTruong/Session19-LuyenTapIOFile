package business.design;

import business.model.Product;

import java.util.List;

public interface IProductDesign extends IGenericDesgin<Product> {
    // Chức năng 4: Cập nhật thông tin sản phẩm theo mã sản phẩm
    Product findById(String id);
    void sortProductsByPrice();

    List<Product> getAllProducts();
void searchByName();
void searchByPriceRange();
}

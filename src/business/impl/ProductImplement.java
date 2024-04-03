package business.impl;

import business.design.IProductDesign;
import business.model.Categories;
import business.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.impl.CategoryImplement.categoriesList;

public class ProductImplement implements IProductDesign {
    static List<Product> productList = new ArrayList<>();
    private static final String FILE_PATH = "product.txt";
    private static Scanner scanner = new Scanner(System.in);

    public ProductImplement() {
        loadFromFile();
    }

    @Override
    public void addNew() {
        if (categoriesList.isEmpty()){
            System.err.println("chua có danh mục , them danh muc trước");
            return;
        }
        System.out.println("Nhập số lượng sản phẩm bạn muốn thêm:");
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Nhập thông tin cho sản pham thứ " + (i + 1) + ":");
            Product product = new Product();
            product.inputData(scanner, productList,categoriesList,false);
            productList.add(product);
            saveToFile();
        }
    }

    @Override
    public void updata() {
        loadFromFile();
        System.out.println("Nhập mã sản phẩm muốn cập nhật:");
        String idUpdate = scanner.nextLine();
        Product product = findById(idUpdate);
        if (product == null) {
            System.out.println("Mã sản phẩm không tồn tại.");
        } else {
            product.inputData(scanner, productList, categoriesList,true);
            saveToFile();
            System.out.println("Đã cập nhật thông tin sản phẩm.");
        }
    }

    @Override
    public void delete() {
        loadFromFile();
        System.out.println("Nhập mã sản phẩm muốn xóa:");
        String idDelete = scanner.nextLine();
        Product product = findById(idDelete);
        if (product == null) {
            System.out.println("Mã sản phẩm không tồn tại.");
        } else {
            productList.remove(product);
            saveToFile();
            System.out.println("Đã xóa sản phẩm.");
        }
    }


    @Override
    public Product findById(String id) {
        for (Product product : productList){
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public void sortProductsByPrice() {
        loadFromFile();
        productList.sort(Comparator.comparing(Product::getPrice));
        System.out.println("Sản phẩm đã được sắp xep theo giá");
    }

    @Override
    public void saveToFile()  {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            objectOutputStream.writeObject(productList);
            System.out.println("Dữ liệu đã được lưu vào file thành công.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Đã xảy ra lỗi khi lưu dữ liệu vào file: " + e.getMessage());
        }
    }

    @Override
    public void loadFromFile()  {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            productList = (List<Product>) objectInputStream.readObject();
            System.out.println("Dữ liệu đã được đọc từ file thành công.");
        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy file. Tạo file mới.");
            productList = new ArrayList<>();
            saveToFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Đã xảy ra lỗi khi đọc dữ liệu từ file: " + e.getMessage());
        }
    }

    @Override
    public void createFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()){
            file.createNewFile();
            System.out.println("file dã được tạo ");
        }else {
            System.out.println(" dã tồn tại");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        loadFromFile();
        for (Product product : productList) {
            product.display();
        }
        return null;
    }



    // Chức năng 6: Tìm kiếm các sản phẩm theo tên sản phẩm
    @Override
    public void searchByName() {
        loadFromFile();
        System.out.println("Nhập tên sản phẩm muốn tìm:");
        String name = scanner.nextLine();
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm.");
        } else {
            for (Product product : result) {
                product.display();
            }
        }
    }

    // Chức năng 7: Tìm kiếm sản phẩm trong khoảng giá a – b
    @Override
    public void searchByPriceRange() {
        loadFromFile();
        System.out.println("Nhập giá thấp nhất:");
        float minPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập giá cao nhất:");
        float maxPrice = Float.parseFloat(scanner.nextLine());
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm trong khoảng giá này.");
        } else {
            for (Product product : result) {
                product.display();
            }
        }
    }

}

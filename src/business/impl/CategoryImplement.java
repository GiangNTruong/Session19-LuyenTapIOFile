package business.impl;

import business.design.ICategories;
import business.model.Categories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryImplement implements ICategories {
    private static Scanner scanner = new Scanner(System.in);
    static List<Categories> categoriesList = new ArrayList<>();
    private static final String FILE_PATH = "categories.txt";

    // Chức năng 5: Cập nhật trạng thái danh mục


    public CategoryImplement() {
        loadFromFile();
    }

    @Override
    public List<Categories> getAllCategories() {

        for (Categories category : categoriesList) {
            category.displayData();
        }
        return null;
    }

    @Override
    public void updateStatus(int categoryId, boolean newStatus) {
        loadFromFile();
        System.out.println("Nhập mã danh mục cần cập nhật trạng thái:");
        int id = Integer.parseInt(scanner.nextLine());
        Categories category = findById(id);
        if (category == null) {
            System.out.println("Mã danh mục không tồn tại.");
        } else {
            category.setCatalogStatus(!category.isCatalogStatus());
            System.out.println("Đã cập nhật trạng thái danh mục.");
            saveToFile();
        }
    }

    @Override
    public Categories findById(int id) {
        for (Categories categories : categoriesList){
            if (categories.getCategoryId()==id){
                return categories;
            }
        }
        return null;
    }

    @Override
    public void addNew() {
        System.out.println("Nhập số lượng danh mục bạn muốn thêm:");
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ " + (i + 1) + ":");
            Categories category = new Categories();
            category.inputData(scanner, categoriesList);
            categoriesList.add(category);
            saveToFile();
        }

    }

    @Override
    public void updata() {
        System.out.println("Nhập ID danh mục bạn muốn cập nhật:");
        int idUpdate = Integer.parseInt(scanner.nextLine());
        for (Categories categories : categoriesList) {
            if (categories.getCategoryId() == idUpdate) {
                System.out.println("Nhập tên danh mục mới:");
                String newName = scanner.nextLine();
                System.out.println("Nhập mô tả danh mục mới:");
                String newDescription = scanner.nextLine();
                System.out.println("Nhập trạng thái danh mục mới (true hoặc false):");
                boolean newStatus = Boolean.parseBoolean(scanner.nextLine());
                categories.setCategoryName(newName);
                categories.setDescriptions(newDescription);
                categories.setCatalogStatus(newStatus);
                System.out.println("Danh mục đã được cập nhật thành công.");
                saveToFile(); // Lưu lại danh sách sau khi cập nhật
                return;
            }
        }
        System.out.println("Không tìm thấy danh mục với ID đã cho.");
    }



    @Override
    public void delete() {
        System.out.println("Nhập ID danh mục bạn muốn xóa:");
        int idDelete = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCategoryId() == idDelete) {
                categoriesList.remove(i);
                System.out.println("Danh mục đã được xóa thành công.");
                saveToFile(); // Lưu lại danh sách sau khi xóa
                return;
            }
        }
        System.out.println("Không tìm thấy danh mục với ID đã cho.");
    }



    @Override
    public void saveToFile()  {
        //Các phương thức lưu và dọc dữ liệu từ tệp
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            objectOutputStream.writeObject(categoriesList);
            System.out.println("Dữ liệu đã đc luuw thành công");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            categoriesList = (List<Categories>) objectInputStream.readObject();
            System.out.println("Dữ liệu đã được đọc từ file thành công.");
        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy file. Tạo file mới.");
            categoriesList = new ArrayList<>();
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
            System.out.println("FIle đuược tạo ok");
        }else {
            System.out.println("Đã tồn tại");
        }
    }
}

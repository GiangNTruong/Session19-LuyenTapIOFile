package business.model;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Categories implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId=1;
    private int categoryId;
    private String categoryName ;
    private String descriptions ;
    private boolean catalogStatus;

    public Categories() {
        this.categoryId = nextId++;
    }

    public Categories(int categoryId, String categoryName, String descriptions, boolean catalogStatus) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
    public void inputData(Scanner scanner, List<Categories> categoriesList){
        this.categoryName = validateCategoryName(scanner, categoriesList);
        this.descriptions = validateDescriptions(scanner);
        this.catalogStatus = validateCatalogStatus(scanner);
    }

    private String validateCategoryName(Scanner scanner, List<Categories> categoriesList) {
        System.out.println("Nhập tên danh mục:");
        while (true){
            String catalogNameInput = scanner.nextLine();
            if (catalogNameInput.length() <= 50 && !isDuplicate(categoriesList, catalogNameInput)) {
                return catalogNameInput;
            }
            System.out.println("Tên danh mục không được quá 50 ký tự và không được trùng lặp. Hãy nhập lại:");
        }
    }

    private String validateDescriptions(Scanner scanner) {
        System.out.println("Nhập mô tả danh mục:");
        return scanner.nextLine();
    }

    private boolean validateCatalogStatus(Scanner scanner) {
        System.out.println("Nhập trạng thái danh mục (true hoặc false):");
        while (!scanner.hasNextBoolean()) {
            System.out.println("Trạng thái danh mục phải là true hoặc false. Hãy nhập lại:");
            scanner.next();
        }
        return scanner.nextBoolean();
    }


    private boolean isDuplicate(List<Categories> arrCategories, String catalogNameInput) {
        return arrCategories.stream()
                .anyMatch(category -> category != null && category.getCategoryName().equals(catalogNameInput));
    }
    public void displayData(){
        System.out.println("Categories{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", catalogStatus=" + (catalogStatus?"Hoạt dong":"Ko hoạt dộng") +
                '}');
    }



}

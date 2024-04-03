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
    public void inputData(Scanner scanner, List<Categories> categoriesList) {
        System.out.println("Nhập tên danh mục:");
        String catalogNameInput = scanner.nextLine();
        while (catalogNameInput.length() > 50 || isDuplicate(categoriesList, catalogNameInput)) {
            System.out.println("Tên danh mục không được quá 50 ký tự và không được trùng lặp. Hãy nhập lại:");
            catalogNameInput = scanner.nextLine();
        }
        this.categoryName = catalogNameInput;

        System.out.println("Nhập mô tả danh mục:");
        this.descriptions = scanner.nextLine();

        System.out.println("Nhập trạng thái danh mục (true hoặc false):");
        while (!scanner.hasNextBoolean()) {
            System.out.println("Trạng thái danh mục phải là true hoặc false. Hãy nhập lại:");
            scanner.next();
        }
        this.catalogStatus = scanner.nextBoolean();
        scanner.nextLine();
    }

    private boolean isDuplicate(List<Categories> arrCategories, String catalogNameInput) {
        for (Categories category : arrCategories) {
            if (category != null && category.getCategoryName().equals(catalogNameInput)) {
                return true;
            }
        }
        return false;
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

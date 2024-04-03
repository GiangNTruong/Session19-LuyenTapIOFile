package manager;

import business.design.ICategories;
import business.impl.CategoryImplement;

import java.util.Scanner;

public class CategoryManagement {
    static ICategories categories = new CategoryImplement();
    public void displayCategoryMenu(){
        Scanner scanner= new Scanner(System.in);
        do {
            System.out.println("*************CATEGORIES MENU************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin các danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    categories.addNew();
                    break;
                case 2:
                    categories.getAllCategories();
                    break;
                case 3:
                    categories.updata();
                    break;
                case 4:
                    categories.delete();
                    break;
                case 5:
                    System.out.println("Nhập ID danh muc cần thay đổi trạng thái:");
                    int idToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Nhập trạng thái mới (true hoặc false):");
                    boolean newStatus = Boolean.parseBoolean(scanner.nextLine());
                    categories.updateStatus(idToUpdate, newStatus);
                    break;
                case 6:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (true);
    }
}

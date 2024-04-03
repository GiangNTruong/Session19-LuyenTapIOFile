package manager;

import business.model.Product;

import java.util.Scanner;

public class ShopManagement {
    private static CategoryManagement categoryManagement = new CategoryManagement();
    private static ProductManagement productManagement = new ProductManagement();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("=======SHOP MENU=========");
            System.out.println("1.Quản lý danh mục sản phẩm ");
            System.out.println("2. Quản lý sản phẩm ");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn chuc năng");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:categoryManagement.displayCategoryMenu();
                break;
                case 2: productManagement.displayProductMenu();
                break;
                case 3:System.exit(0);


            }
        }while (true);
    }
}

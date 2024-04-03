package business.design;

import java.io.IOException;

public interface IGenericDesgin<T> {
      void addNew();

      void updata();
      void delete();

      void saveToFile() ; // Thêm phương thức lưu vào file
      void loadFromFile() ; // Thêm phương thức đọc từ file
      void createFile() throws IOException; // Thêm phương thức tạo file
}

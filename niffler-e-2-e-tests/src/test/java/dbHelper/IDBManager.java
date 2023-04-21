package dbHelper;

import java.util.Map;

public interface IDBManager {
    void deleteCategory(String categoryName);

    Map<String, Object> info();
}

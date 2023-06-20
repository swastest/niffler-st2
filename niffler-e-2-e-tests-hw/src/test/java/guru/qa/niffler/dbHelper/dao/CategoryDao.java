package guru.qa.niffler.dbHelper.dao;

import java.util.Map;

public interface CategoryDao {
    void deleteCategory(String categoryName);

    Map<String, Object> info();
}

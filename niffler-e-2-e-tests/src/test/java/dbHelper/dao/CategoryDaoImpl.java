package dbHelper.dao;

import dbHelper.mangerDb.DataSourceProviderPG;
import dbHelper.mangerDb.ServiceDB;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

public class CategoryDaoImpl implements CategoryDao {
    private DataSource ds = DataSourceProviderPG.INSTANCE.getDataSource(ServiceDB.NIFFLER_SPEND);
    private JdbcTemplate template = new JdbcTemplate(ds);

    @Override
    public void deleteCategory(String categoryName) {
        String sqlRow = "DELETE FROM categories WHERE category = ?";
        template.update(sqlRow, categoryName);
    }

    public Map<String, Object> info() {
        String sql = "SELECT * FROM information_schema.tables WHERE table_name = 'categories'";
        Map<String, Object> result = template.queryForObject(sql, (rs, rowNum) -> {
            Map<String, Object> row = new HashMap<>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; ++i) {
                String columnName = metaData.getColumnName(i);
                Object value = rs.getObject(columnName);
                row.put(columnName, value);
            }
            return row;
        });
        return result;
    }
}

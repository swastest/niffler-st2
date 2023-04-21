package dbHelper;

import com.beust.jcommander.IDefaultProvider;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBManagerImpl implements IDBManager {
    private DataSource ds = DataSourcePG.INSTANCE.getDataSource();
    private JdbcTemplate template = new JdbcTemplate(ds);
    @Override
    public void deleteCategory(String categoryName) {
        String sqlRow = "DELETE FROM categories WHERE category = ?";
        template.update(sqlRow, categoryName);
    }

    public Map<String, Object> info(){
        String sql = "SELECT * FROM information_schema.tables WHERE table_name = 'categories'";
        Map<String, Object> result = template.queryForObject(sql, new RowMapper<Map<String, Object>>() {
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> row = new HashMap<>();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; ++i) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(columnName);
                    row.put(columnName, value);
                }
                return row;
            }
        });
        return result;
    }
}

package dbHelper;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public enum DataSourcePG {
	INSTANCE;
	private DataSource dataSource;

	public DataSource getDataSource() {
		if (dataSource == null) {
			PGSimpleDataSource postgresDataSource = new PGSimpleDataSource();
			postgresDataSource.setUrl("jdbc:postgresql://localhost:5432/niffler-spend");
			postgresDataSource.setUser("postgres");
			postgresDataSource.setPassword("secret");
			dataSource = postgresDataSource;
		}
		return dataSource;
	}
}

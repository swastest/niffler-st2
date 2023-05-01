package dbHelper.mangerDb;

import config.Config;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public enum DataSourceProviderPG {
	INSTANCE;
	private Map<ServiceDB, DataSource> dataSourceMap = new ConcurrentHashMap<>();

	public DataSource getDataSource(ServiceDB serviceDB) {
		/*
		if (!dataSourceMap.containsKey(serviceDB)) {
			PGSimpleDataSource postgresDataSource = new PGSimpleDataSource();
			postgresDataSource.setUrl(serviceDB.getJdbcUrl());
			postgresDataSource.setUser("postgres");
			postgresDataSource.setPassword("secret");
			dataSourceMap.put(serviceDB, postgresDataSource);
		}
		 */
		dataSourceMap.computeIfAbsent(serviceDB, serviceDB1 -> {
			PGSimpleDataSource postgresDataSource = new PGSimpleDataSource();
			postgresDataSource.setUrl(serviceDB1.getJdbcUrl());
			postgresDataSource.setUser(Config.getConfig().getDBLogin());
			postgresDataSource.setPassword(Config.getConfig().getDBPassword());
			dataSourceMap.put(serviceDB1, postgresDataSource);
			return postgresDataSource;
		});
		return dataSourceMap.get(serviceDB);
	}
}

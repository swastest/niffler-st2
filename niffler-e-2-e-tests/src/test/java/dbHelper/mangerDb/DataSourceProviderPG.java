package dbHelper.mangerDb;

import config.Config;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum DataSourceProviderPG {
	INSTANCE;
	private Map<ServiceDB, DataSource> dataSourceMap = new ConcurrentHashMap<>();

	public DataSource getDataSource(ServiceDB serviceDB) {
		return dataSourceMap.computeIfAbsent(serviceDB, service -> {
			PGSimpleDataSource postgresDataSource = new PGSimpleDataSource();
			postgresDataSource.setUrl(service.getJdbcUrl());
			postgresDataSource.setUser(Config.getConfig().getDBLogin());
			postgresDataSource.setPassword(Config.getConfig().getDBPassword());
			return postgresDataSource;
		});
	}
}

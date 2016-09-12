package com.zwh.social.api.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.AbstractDataSource;

/**
 * 可选择的主从模式的数据源
 *
 */
public class DynamicDataSource extends AbstractDataSource  {
	
	/**
	 * 数据源类型
	 * @author 
	 *
	 */
	 public enum DataSourceType {
	        master, slave;
	 }
	 
	 private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<DataSourceType>();
	 
	 public static void setDataSource(DataSourceType type){
		 holder.set(type);
	 }

	/**
	 * 主数据源
	 */
	private DataSource masterDataSource;
	/**
	 * 从数据源
	 */
	private DataSource slaveDataSource;

	public void setMasterDataSource(DataSource masterDataSource) {
		this.masterDataSource = masterDataSource;
	}

	public void setSlaveDataSource(DataSource slaveDataSource) {
		this.slaveDataSource = slaveDataSource;
	}
	
	/**
     * 这里进行判断当前是主库还是从库，默认是主库
     * @return DataSource
     */
    private DataSource getDataSource() {
    	if(holder.get() == DataSourceType.slave){
    		return slaveDataSource;
    	}else{
    		return masterDataSource;
    	}
    }

	@Override
	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return getDataSource().getConnection(username, password);
	}

}

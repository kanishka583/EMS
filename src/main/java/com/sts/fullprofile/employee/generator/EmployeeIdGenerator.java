package com.sts.fullprofile.employee.generator;

import java.sql.Statement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.jdbc.Work;

public class EmployeeIdGenerator implements IdentifierGenerator{
	
	//TODO - explore this way later

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException{
		
		String prefix = "STS";
		String generatedId = prefix;
		
		try {
			session.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					String generatedId = null;
					ResultSet resultSet = null;
					Statement statement = null;
					statement = connection.createStatement();
					resultSet = statement.executeQuery("SELECT MAX(empId) FROM employee;");
					if(resultSet.next()) {
						String lastIndex = resultSet.getString(1);
						long lastIndexInt = Long.parseLong(lastIndex.substring(4));
						generatedId =  prefix + Long.toString(lastIndexInt+2);
					}
				}
			});
			
			
			return generatedId;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

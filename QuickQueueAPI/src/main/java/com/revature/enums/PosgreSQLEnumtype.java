package com.revature.enums;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

public class PosgreSQLEnumtype extends EnumType {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void nullSafeSet(
            PreparedStatement st,
            Object value,
            int index,
            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
            st.setObject(
	            index,
	            value != null ?
	                ((Enum) value).name() :
	                null,
	            Types.OTHER
            );
    }
}

package com.puhui.app.typeHandler;

import com.puhui.app.utils.LendAesUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库加密 handler
 * Created by yhl
 */
@Component
@MappedTypes(value = String.class)
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class EncryptTypeHandler extends BaseTypeHandler<String> {

    /**
     * 入库加密处理
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        //由于BaseTypeHandler中已经把parameter为null的情况做了处理，所以这里我们就不用再判断parameter是否为空了，直接用就可以了
        String encryptString = LendAesUtil.encrypt(parameter);
        ps.setString(i, encryptString);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.getDecryptString(rs.getString(columnName));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.getDecryptString(cs.getString(columnIndex));
    }
    /**
     *  出库解密处理
     */
    private String getDecryptString(String columnValue) {
        if (columnValue == null){
            return null;
        }

        return LendAesUtil.decrypt(columnValue);
    }
}

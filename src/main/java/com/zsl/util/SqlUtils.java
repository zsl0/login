package com.zsl.util;

import com.zsl.common.exception.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author zsl
 * @Date 2022/1/13 15:02
 */
@Component
@Slf4j
public class SqlUtils {
    @Autowired
    DataSource dataSource;

    @Autowired
    MailService mailService;

    /**
     * 从数据源获取连接
     */
    public Connection getConn() throws SQLException {
        return dataSource.getConnection();

    }

    /**
     * 关闭连接
     */
    public void close(Connection conn, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 创建表
     */
    public void createTable(String sql) {
        Connection conn = null;
        Statement statement = null;
        try {
            conn = getConn();
            statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            log.debug("createTable failed. msg: " + e.getMessage());
            mailService.sendWarnMail("createTable failed. sql: [" + sql + "] msg: " + e.getMessage());
            throw new APIException(e.getMessage());
        } finally {
            close(conn, statement);
        }
    }
}

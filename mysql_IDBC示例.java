package dao;

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java_image_server?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    //添加volatile保证内存可见性，如果不加的话，第一个线程结束后第二个线程看到的dataSource不一定是更新的
    private static volatile DataSource dataSource = null;
    public static DataSource getDataSource(){
        //通过这个方法来创建DataSource的实例
        //保证线程安全
        //1.先加锁，加锁低效   2.增加判断  3.volatile
        if(dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    MysqlDataSource tmpDataSource = (MysqlDataSource) dataSource;
                    tmpDataSource.setURL(URL);
                    tmpDataSource.setUser(USERNAME);
                    tmpDataSource.setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }
public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
}
public static void close(Connection connection,PreparedStatement statement, ResultSet resultSet)
{
    try {
        if(resultSet != null)
    {
        resultSet.close();
    }
        if(statement != null)
        {
            statement.close();
        }
        if(connection != null)
        {
            connection.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

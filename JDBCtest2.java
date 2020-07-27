
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JDBCtest2{
    public static void main(String[] arga)throws SQLException {
        //1.创建DataSourse对象（DataSourse对象的生命周期应该是要跟随整个程序）
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java11?characterEncoding=utf8&useSSL=true");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("123456");
        //2.和数据库建立连接,简历连接后就可以进行数据的传输
        Connection connection = dataSource.getConnection();
        //3.拼接SQL语句，用到PrepareStatement对象
        String sql = "select * from students";
        PreparedStatement statement = connection.prepareStatement(sql);
        //4.执行sql语句
        ResultSet resultSet = statement.executeQuery();
        //5,遍历结果集
        //结果集相当于一张表，这个表里有很多行，每一行是一条记录
        //next()判断当前是否存在下一行，如果存在就获取到这一行
        while(resultSet.next()){
            //resultSet指向当前行
            //columnLable必须和数据库表结构中的名称相同
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int classId = resultSet.getInt("classId");
        }
        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}

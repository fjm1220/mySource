import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCtest {
    public static void main(String[] arga)throws SQLException {
        //1.创建DataSourse对象（DataSourse对象的生命周期应该是要跟随整个程序）
        DataSource dataSource = new MysqlDataSource();
        //接下来需要针对datasourse进行一些配置，一边后面能够顺利的访问到数据库服务器
        //主要配置三方面信息，Url,User,Password,需要进行向下转型
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java11?characterEncoding-utf-8&useSSL=true");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("123456");
        //2.和数据库建立连接,简历连接后就可以进行数据的传输
        //Connection的类是java.sql.Connection包中的
         Connection connection = dataSource.getConnection();
         //3.拼接SQL语句，用到PrepareStatement对象
        int id = 1;
        String name = "张三";
        int classId = 10;
        //?为占位符
        String sql = "insert into student values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        //1,2,3相当于？的下标，从1开始
        statement.setInt(1,id);
        statement.setString(2,name);
        statement.setInt(3,classId);
        //4.拼装完毕之后，可以执行SQL了
        //insert delete update 都使用executeUpdate方法来执行
        //select使用exectuteQuery来执行
        int ret = statement.executeUpdate();
        //5.执行完毕后，释放相关资源
        //注意释放顺序，先创建的后释放
         statement.close();
         connection.close();
    }
}

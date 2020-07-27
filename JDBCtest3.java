
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCtest3{
     public static void main(String[] args)throws SQLException {
          Scanner in = new Scanner(System.in);
          System.out.println("请输入要删除学生的姓名：");
          String name = in.nextLine();
          //1.创建DataSourse对象
          DataSource dataSource = new MysqlDataSource();
          ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java11?characterEncoding=utf8&useSSL=true");
          ((MysqlDataSource) dataSource).setUser("root");
          ((MysqlDataSource) dataSource).setPassword("123456");
          //2.创建Collection对象，和数据库建立连接
          Connection connection = dataSource.getConnection();
          //3.拼装sql语句
          String sql = "delete from students where name = ?";
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setString(1,name);
          //4.执行sql语句
          int ret = statement.executeUpdate();
          if(ret == 1){
               System.out.println("删除成功");
          }
          else{
               System.out.println("删除失败");
          }
          //5.关闭连接
           statement.close();
           connection.close();
     }
}

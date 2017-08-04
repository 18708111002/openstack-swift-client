/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Zj
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class Dao {
    private Connection conn = null;
    PreparedStatement statement = null;
 
    void connSQL() {
        String url = "jdbc:mysql://localhost:3306/cipc_clouds_db?characterEncoding=UTF-8"; // 数据库地址，端口，数据库名称，字符集
        String username = "root"; // 数据库用户名
        String password = "123456"; // 数据库密码
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动，必须导入包mysql-connector-java-5.1.6-bin.jar
            conn = DriverManager.getConnection(url, username, password);
        }
        // 捕获加载驱动程序异常
        catch (ClassNotFoundException cnfex) {
            System.err.println("装载 JDBC/ODBC 驱动程序失败。");
            cnfex.printStackTrace();
        }
        // 捕获连接数据库异常
        catch (SQLException sqlex) {
            System.err.println("无法连接数据库");
            sqlex.printStackTrace();
        }
    }
 
    // 关闭数据库
    void deconnSQL() {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.out.println("关闭数据库异常：");
            e.printStackTrace();
        }
    }
 
    /**
     * 执行查询sql语句
     *
     * @param sql
     * @return
     */
    ResultSet selectSQL(String sql) {
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
 
    /**
     * 执行插入sql语句
     *
     * @param sql
     * @return
     */
    boolean insertSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }
 
    /**
     * 执行删除sql语句
     *
     * @param sql
     * @return
     */
    boolean deleteSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("删除数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("删除时出错：");
            e.printStackTrace();
        }
        return false;
    }
 
    /**
     * 执行更新sql语句
     *
     * @param sql
     * @return
     */
    boolean updateSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("更新数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("更新时出错：");
            e.printStackTrace();
        }
        return false;
    }
 
    /**
     * 打印结果集
     *
     * 具体列根据自己的数据库表结构更改
     *
     * @param rs
     */
    void print(ResultSet rs) {
        System.out.println("-----------------");
        System.out.println("查询结果:");
        System.out.println("-----------------");
 
        try {
            while (rs.next()) {
                System.out.println(rs.getInt(0) + "/t/t" + rs.getString(1)
                        + "/t/t" + rs.getInt(2));
            }
        } catch (SQLException e) {
            System.out.println("显示时数据库出错。");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("显示出错。");
            e.printStackTrace();
        }
    }
 
    public static void main(String args[]) {
 
        Dao dao = new Dao();
        dao.connSQL(); // 连接数据库
       // String s = "select * from tree_tb";
 
        String insert = "insert into tree_tb(ID,Name,ParentID) values('10000','10000','10000')";
        String update = "update tree_tb set ParentID =20000 where ID= '10000'";
        String delete = "delete from tree_tb where ID= '10000'";
 
        if (dao.insertSQL(insert) == true) {
            System.out.println("插入成功");
           // ResultSet resultSet = dao.selectSQL(s);
           // dao.print(resultSet);
        }
        if (dao.updateSQL(update) == true) {
            System.out.println("更新成功");
           // ResultSet resultSet = dao.selectSQL(s);
            //dao.print(resultSet);
        }
        if (dao.insertSQL(delete) == true) {
            System.out.println("删除成功");
           // ResultSet resultSet = dao.selectSQL(s);
            //dao.print(resultSet);
        }
 
        dao.deconnSQL(); // 关闭数据库连接
    }
}


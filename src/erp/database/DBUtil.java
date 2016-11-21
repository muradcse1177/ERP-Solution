package erp.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DBUtil {

    public static final String dir = System.getProperty("user.home")
            + "\\ERP_ORDER";
    public static final String dirPhotos = dir + "\\photos";
    public static final String CONFIGURATION = dir + "\\config.ini";
    
    public static final String DEFAULT_CONFIGURATION = "################Database Configuration#############\r\n"
            + "#Database user name \r\n"
            + "userName=root\r\n"
            + "#Database Password\r\n"
            + "password=\r\n"
            + "#Database Server Host Name or IP Address\r\n"
            + "dbHost=localhost\r\n"
            + "#Database Server Port \r\n"
            + "dbPort=3306\r\n"
            + "#Database name for ERP_SOLUTIONS\r\n"
            + "dbName=erpdb";

    private static String USERNAME;
    private static String PASSWORD;
    private static String DATABASE_NAME;
    private static String DATABASE_HOST;
    private static int PORT;
    private static String CONN_STRING;
    private static String CONN_STRING_WITHOUT_DB;

    public static void init() {
        
        Properties config = new Properties();
        try {

            File file = new File(DBUtil.dir);
            File photos = new File(DBUtil.dirPhotos);

            if (!file.exists()) {
                file.mkdirs();
                photos.mkdirs();
                BufferedWriter w = new BufferedWriter(new FileWriter(CONFIGURATION));
                w.write(DEFAULT_CONFIGURATION);
                w.close();
            }

            config.load(new FileInputStream(CONFIGURATION));
            PORT = Integer.parseInt(config.getProperty("dbPort"));
            USERNAME = config.getProperty("userName");
            PASSWORD = config.getProperty("password");
            DATABASE_HOST = config.getProperty("dbHost");
            DATABASE_NAME = config.getProperty("dbName");
            
            CONN_STRING_WITHOUT_DB = "jdbc:mysql://" + DATABASE_HOST + ":"
                    + PORT + "/";
            CONN_STRING = "jdbc:mysql://" + DATABASE_HOST + ":" + PORT + "/"
                    + DATABASE_NAME;

            if (!checkDBExists()) {
                createDatabase();
                initDatabase();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error while connecting to database", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private static void createDatabase() throws SQLException {
        String sql = "CREATE DATABASE IF NOT EXISTS `" + DATABASE_NAME
                + "` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;";
        
        try (Connection conn = DBUtil.getConnectionForCreate();
                Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }

    private static Connection getConnectionForCreate() throws SQLException {
        return DriverManager.getConnection(CONN_STRING_WITHOUT_DB, USERNAME,
                PASSWORD);
    }

    private static boolean checkDBExists() throws Exception {
        try {
            Connection conn = DriverManager.getConnection(
                    CONN_STRING_WITHOUT_DB, USERNAME, PASSWORD);
            ResultSet resultSet = conn.getMetaData().getCatalogs();
            while (resultSet.next()) {

                String databaseName = resultSet.getString(1);
                if (databaseName.equals(DATABASE_NAME)) {
                    return true;
                }
            }
            resultSet.close();
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    private static void initDatabase() throws SQLException {

        String inventoryTable = "CREATE TABLE IF NOT EXISTS `inventory` (\n"
                + "  `material_type` varchar(100) NOT NULL,\n"
                + "  `material_sub_type` varchar(100) NOT NULL DEFAULT '',\n"
                + "  `qty` double(20,5) NOT NULL,\n"
                + "  `price_per_unit` double(20,5) DEFAULT NULL,\n"
                + "  `Unit` varchar(100) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`material_type`,`material_sub_type`)\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        String orderDetailTable = "CREATE TABLE IF NOT EXISTS `order_detail` (\n"
                + "  `id` int(10) NOT NULL DEFAULT '0',\n"
                + "  `client_name` varchar(100) DEFAULT NULL,\n"
                + "  `address` varchar(1000) DEFAULT NULL,\n"
                + "  `order_date` varchar(100) DEFAULT NULL,\n"
                + "  `delivary_date` varchar(100) DEFAULT NULL,\n"
                + "  `finished_product` varchar(1000) DEFAULT NULL,\n"
                + "  `product_category` varchar(100) DEFAULT NULL,\n"
                + "  `design_url` varchar(1000) DEFAULT NULL,\n"
                + "  `quantity` double(20,5) DEFAULT NULL,\n"
                + "  `completed` tinyint(1) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`id`)\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        String productionUpdateTable = "CREATE TABLE IF NOT EXISTS `production_update` (\n"
                + "  `update_no` varchar(100) NOT NULL DEFAULT '',\n"
                + "  `update_date` varchar(20) DEFAULT NULL,\n"
                + "  `update_by` varchar(100) DEFAULT NULL,\n"
                + "  `issue_no` varchar(100) DEFAULT NULL,\n"
                + "  `issue_date` varchar(100) DEFAULT NULL,\n"
                + "  `job_no` varchar(100) DEFAULT NULL,\n"
                + "  `job_date` varchar(100) DEFAULT NULL,\n"
                + "  `item_ref` varchar(100) DEFAULT NULL,\n"
                + "  `ord_qty` varchar(100) DEFAULT NULL,\n"
                + "  `up_qty` varchar(100) DEFAULT NULL,\n"
                + "  `bal_qty` varchar(100) DEFAULT NULL,\n"
                + "  `ex_qty` varchar(100) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`update_no`)\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        String productRequirementTable = "CREATE TABLE IF NOT EXISTS `product_requirement` (\n"
                + "  `product_name` varchar(100) NOT NULL DEFAULT '',\n"
                + "  `product_category` varchar(100) NOT NULL DEFAULT '',\n"
                + "  `material_type` varchar(100) NOT NULL DEFAULT '',\n"
                + "  `material_sub_type` varchar(100) NOT NULL DEFAULT '',\n"
                + "  `Req_Qty` double(20,6) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`product_name`,`product_category`,`material_type`,`material_sub_type`)\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        String rawMatIssueTable = "CREATE TABLE IF NOT EXISTS `raw_mat_issue` (\n"
                + "  `Material_Type` varchar(100) DEFAULT NULL,\n"
                + "  `Material_Sub_Type` varchar(20) DEFAULT NULL,\n"
                + "  `Quantity` double(20,5) DEFAULT NULL,\n"
                + "  `completed` tinyint(1) DEFAULT NULL\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        String rawMatReqTable = "CREATE TABLE IF NOT EXISTS `raw_mat_req` (\n"
                + "  `id` int(20) DEFAULT NULL,\n"
                + "  `material_type` varchar(100) DEFAULT NULL,\n"
                + "  `material_sub_type` varchar(20) DEFAULT NULL,\n"
                + "  `total_qty` double(20,5) DEFAULT NULL,\n"
                + "  `completed` tinyint(1) DEFAULT NULL\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        String userTable = "CREATE TABLE IF NOT EXISTS `users` (\n"
                + "  `username` varchar(100) NOT NULL,\n"
                + "  `password` varchar(100) NOT NULL,\n"
                + "  `permission` int(11) NOT NULL,\n"
                + "  PRIMARY KEY (`username`)\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        try (Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(inventoryTable);
            stmt.executeUpdate(orderDetailTable);
            stmt.executeUpdate(productionUpdateTable);
            stmt.executeUpdate(productRequirementTable);
            stmt.executeUpdate(rawMatReqTable);
            stmt.executeUpdate(rawMatIssueTable);
            stmt.executeUpdate(userTable);
        } catch (SQLException e) {
            throw e;
        }
    }
}

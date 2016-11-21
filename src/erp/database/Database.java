 // <editor-fold defaultstate="collapsed" desc="Package Declaration">  
package erp.database;

import erp.model.Inventory;
import erp.model.OrderDetail;
import erp.model.ProductReq;
import erp.model.RawMatIssue;
import erp.model.RawMatReq;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
// </editor-fold>  

public class Database {

    Connection con = null;

    // <editor-fold defaultstate="collapsed" desc="Init Code">   
    public Database() {
    }

    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Get Something">  
    public static ArrayList<Inventory> getInventoryData()
            throws Exception {
        String sql
                = "SELECT material_type, material_sub_type, qty, price_per_unit, Unit"
                + " FROM inventory;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            ResultSet set = stmt.executeQuery();
            ArrayList<Inventory> arrayList = new ArrayList<>();
            while (set.next()) {
                Inventory rm = new Inventory(set.getString(1), set.getString(2),
                        set.getDouble(3), set.getDouble(4), set.getString(5));
                arrayList.add(rm);
            }
            return arrayList;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }

    public static int getUserLoginInfo(String username, String password) throws Exception{
                String sql
                = "SELECT `permission` FROM `users` WHERE `username`= ? and `password` = ?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet set = stmt.executeQuery();
            
            if(set.next()){
                return set.getInt(1);
            }else
                throw  new Exception("Username and Password doesn't match.");

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        } catch (Exception ex) {
            throw  ex;
        }
    }
    public static ArrayList<ProductReq> getProductReqData() throws SQLException, ClassNotFoundException, Exception {
        String sql
                = "SELECT `product_name`, `product_category`, `material_type`, `material_sub_type`,  "
                + "`Req_Qty` FROM `product_requirement` WHERE 1";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            ResultSet set = stmt.executeQuery();
            ArrayList<ProductReq> arrayList = new ArrayList<>();
            while (set.next()) {
                ProductReq rm = new ProductReq(set.getString(1), set.getString(2),
                        set.getString(3), set.getString(4), set.getDouble(5));
                arrayList.add(rm);
            }
            return arrayList;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }

    }

    public static Object[] getMaterialType() {
        String sql = "SELECT distinct(`material_type`) as material_type FROM `inventory` WHERE 1;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            ResultSet set = stmt.executeQuery();
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add("Select Material Type");

            while (set.next()) {
                arrayList.add(set.getString(1));
            }
            return arrayList.toArray();

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return new Object[]{"Select Material Type"};

    }

    public static Object[] getMaterialSubType(String materialType) {

        String sql = "SELECT `material_sub_type` FROM `inventory` WHERE `material_type` = ?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, materialType);
            ResultSet set = stmt.executeQuery();
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add("Select Material Sub Type");

            while (set.next()) {
                arrayList.add(set.getString(1));
            }
            return arrayList.toArray();

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return new Object[]{"Select Material Sub Type"};
    }

    public static Object[] getProducts() {
        String sql = "SELECT DISTINCT(`product_name`) as product_name FROM `product_requirement` WHERE 1;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            ResultSet set = stmt.executeQuery();
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add("Select Finished Product");

            while (set.next()) {
                arrayList.add(set.getString(1));
            }
            return arrayList.toArray();

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }

        return new Object[]{"Select Finished Product"};
    }

    public static Object[] getProductCategory(String productName) {
        String sql = "SELECT  DISTINCT(`product_category`) as product_category FROM `product_requirement` WHERE `product_name`=?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, productName);
            ResultSet set = stmt.executeQuery();
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add("Select Product Category");

            while (set.next()) {
                arrayList.add(set.getString(1));
            }
            return arrayList.toArray();

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return new Object[]{"Select Product Category"};
    }

    public static int nextOrderId() {
        String sql = "SELECT MAX(id) AS total\n"
                + "FROM `order_detail`\n"
                + "WHERE 1";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                return set.getInt(1) + 1;
            }

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return 0;
    }

    public static int nextRawMatId() {
        String sql = "SELECT MAX(id) AS total\n"
                + "FROM `raw_mat_req`\n"
                + "WHERE 1";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                return set.getInt(1) + 1;
            }

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return 0;
    }

    public static ArrayList<OrderDetail> getOrder() {
        String sql = "SELECT `id`, `client_name`, `address`, `order_date`, `delivary_date`, `finished_product`, `product_category`,"
                + " `design_url`, `quantity`, `completed` FROM `order_detail` WHERE 1;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            ResultSet set = stmt.executeQuery();
            ArrayList<OrderDetail> arrayList = new ArrayList<>();

            while (set.next()) {
                arrayList.add(new OrderDetail(set.getInt(1), set.getString(2), set.getString(3),
                        set.getString(4), set.getString(5), set.getString(6), set.getString(7),
                        set.getString(8), set.getDouble(9), set.getBoolean(10)));
            }
            return arrayList;

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return new ArrayList<>();
    }

    //
    public static ArrayList<Object[]> getRequiredMaterials(String productName, String ProductCategory) {
        String sql = "SELECT `material_type`, `material_sub_type`, `Req_Qty` FROM "
                + "`product_requirement` WHERE `product_name` = ? AND `product_category` = ?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, productName);
            stmt.setString(2, ProductCategory);
            ResultSet set = stmt.executeQuery();

            ArrayList<Object[]> arrayList = new ArrayList<>();

            while (set.next()) {
                arrayList.add(new Object[]{set.getString(1), set.getString(2), set.getDouble(3), 0.0});
            }
            return arrayList;

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return new ArrayList<>();
    }

    public static ArrayList<Object[]> getReqForIssue(boolean flag) {
        String sql;
        if (!flag) {
            sql = "SELECT  `material_type`, `material_sub_type`, SUM(`total_qty`) as total_qty FROM "
                    + " `raw_mat_req` WHERE `completed`=0  GROUP BY `material_type`, `material_sub_type`;\n"
                    + "";
        } else {
            sql = "SELECT  `material_type`, `material_sub_type`, SUM(`total_qty`) as total_qty FROM "
                    + " `raw_mat_req` WHERE `completed`=1  GROUP BY `material_type`, `material_sub_type`;\n"
                    + "";
        }
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            ResultSet set = stmt.executeQuery();
            ArrayList<Object[]> arrayList = new ArrayList<>();
            while (set.next()) {

                arrayList.add(new Object[]{set.getString(1), set.getString(2), set.getDouble(3)});
            }
            System.out.println(arrayList.size());

            return arrayList;

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return new ArrayList<>();
    }
    
    
    public static ArrayList<Object[]> getMatIssue(boolean flag) {
        String sql = "SELECT `Material_Type`, `Material_Sub_Type`, `Quantity` FROM `raw_mat_issue` WHERE `completed`=?;";
       
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setBoolean(1, flag);
            ResultSet set = stmt.executeQuery();
            ArrayList<Object[]> arrayList = new ArrayList<>();
            while (set.next()) {

                arrayList.add(new Object[]{set.getString(1), set.getString(2), set.getDouble(3)});
            }
            System.out.println(arrayList.size());

            return arrayList;

        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);

        }
        return new ArrayList<>();
    }
    
    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="Delete Something Code">  
    public static boolean removeInventory(String matType, String matSUbType) throws SQLException, Exception {

        String sql = "DELETE FROM `inventory` WHERE `material_type`='" + matType + "'" + " AND `material_sub_type` = "
                + "'" + matSUbType + "'" + ";";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }

    public static boolean removeProductRequirement(ProductReq req) throws SQLException, ClassNotFoundException, Exception {

        String sql = "DELETE FROM `product_requirement` WHERE `product_name`=? AND "
                + "`product_category`=? AND `material_type`=? AND `material_sub_type`=? AND `Req_Qty` =?; ";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, req.getProductName());
            stmt.setString(2, req.getProductCategory());
            stmt.setString(3, req.getMaterialType());
            stmt.setString(4, req.getMaterialSubType());
            stmt.setDouble(5, req.getReqQty());
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }
 // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Boolean Check Methods">  
    public static boolean isInventoryMaterialFound(Inventory inv) throws SQLException, ClassNotFoundException, Exception {
        String sql = "SELECT * FROM `inventory` WHERE `material_type`=? and `material_sub_type` = ?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, inv.getMaterialType());
            stmt.setString(2, inv.getMaterialSubType());
            ResultSet set = stmt.executeQuery();
            return set.next();

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="edit something">  
    public static boolean editInventory(Inventory inv) throws SQLException, Exception {

        String sql = "UPDATE `inventory` SET `qty`=?,`price_per_unit`=?,`Unit`= ? WHERE "
                + "`material_type`=? and `material_sub_type` = ?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {

            stmt.setDouble(1, inv.getQuantity());
            stmt.setDouble(2, inv.getPricePerUnit());
            stmt.setString(3, inv.getUnit());
            stmt.setString(4, inv.getMaterialType());
            stmt.setString(5, inv.getMaterialSubType());
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }

    public static boolean compteteReq(int id) throws SQLException, ClassNotFoundException, Exception {
        String sql = "UPDATE `order_detail` SET `completed`= ? WHERE `id`=?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {

            stmt.setBoolean(1, true);
            stmt.setInt(2, id);
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }
//

    public static boolean updateInventory(Inventory inv) throws SQLException, ClassNotFoundException, Exception {
        String sql = "UPDATE `inventory` SET `qty`=? WHERE `material_type`=? AND `material_sub_type`=?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {

            stmt.setDouble(1, inv.getQuantity());
            stmt.setString(2, inv.getMaterialType());
            stmt.setString(3, inv.getMaterialSubType());
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }

    //
    public static boolean updateRawMatReqToBool(String mt, String mst) throws SQLException, ClassNotFoundException, Exception {
        String sql = "UPDATE `raw_mat_req` SET `completed`=1 WHERE `material_type`=? AND `material_sub_type`=?;";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, mt);
            stmt.setString(2, mst);
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }
   public static boolean updateIssue(String mt, String mst) throws SQLException, ClassNotFoundException, Exception {
        String sql = "UPDATE `raw_mat_issue` SET `completed`=? WHERE `Material_Type`=? AND `Material_Sub_Type`=?;\n" +
"";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setBoolean(1, true);
            stmt.setString(2, mt);
            stmt.setString(3, mst);
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Insert Section">  
    public static boolean saveInventory(Inventory inv) throws SQLException, Exception {

        String sql = "INSERT INTO `inventory`(`material_type`, `material_sub_type`,"
                + " `qty`, `price_per_unit`, `Unit`) VALUES (?, ?, ?, ?, ?);";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, inv.getMaterialType());
            stmt.setString(2, inv.getMaterialSubType());
            stmt.setDouble(3, inv.getQuantity());
            stmt.setDouble(4, inv.getPricePerUnit());
            stmt.setString(5, inv.getUnit());
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }

    public static boolean insertProduct(ProductReq pr) throws SQLException, ClassNotFoundException, Exception {

        String sql = "INSERT INTO `product_requirement`(`product_name`, `product_category`, "
                + "`material_type`, `material_sub_type`, `Req_Qty`) VALUES (?, ?, ?, ?, ?);";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, pr.getProductName());
            stmt.setString(2, pr.getProductCategory());
            stmt.setString(3, pr.getMaterialType());
            stmt.setString(4, pr.getMaterialSubType());
            stmt.setDouble(5, pr.getReqQty());
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }

    public static boolean saveOrder(OrderDetail or) throws Exception {
        String sql = "INSERT INTO `order_detail`(`id`, `client_name`, `address`, `order_date`, `delivary_date`, `finished_product`, "
                + "`product_category`, `design_url`, `quantity`, `completed`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, or.getId());
            stmt.setString(2, or.getClientName());
            stmt.setString(3, or.getAddress());
            stmt.setString(4, or.getOrderDate());
            stmt.setString(5, or.getDelevaryDate());
            stmt.setString(6, or.getFinishedProduct());
            stmt.setString(7, or.getProductCategory());
            stmt.setString(8, or.getDesighUrl());
            stmt.setDouble(9, or.getQuantity());
            stmt.setBoolean(10, or.isCompleted());
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }

    public static boolean saveReq(RawMatReq or) throws SQLException, ClassNotFoundException, Exception {
        String sql = "INSERT INTO `raw_mat_req`(`id`, `material_type`, `material_sub_type`, "
                + "`total_qty`, `completed`) VALUES (?, ?, ?, ?, ?);";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, or.getId());
            stmt.setString(2, or.getMaterial_type());
            stmt.setString(3, or.getMaterial_sub_type());
            stmt.setDouble(4, or.getTotal_qty());
            stmt.setBoolean(5, or.isCompleted());

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }
//
       public static boolean saveRaw_Mat_Issue(RawMatIssue or) throws SQLException, ClassNotFoundException, Exception {
        String sql = "INSERT INTO `raw_mat_issue`(`Material_Type`, `Material_Sub_Type`, `Quantity`, `completed`) VALUES (?,?,?,?);";
        try (Connection conn = DBUtil.getConnection();
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, or.getMaterialType());
            stmt.setString(2, or.getMaterialSubType());
            stmt.setDouble(3, or.getQuantity());
            stmt.setBoolean(4, or.isCompleted());

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new Exception(
                    "Server Error! Please try again Leter. "
                    + e.getMessage());
        }
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Unused Code">   
    public static String[] getJobNo() {
//        EntityManagerFactory entityManagerFactory
//                = Persistence.createEntityManagerFactory("ERP_ORDERPU");
//        EntityManager em = entityManagerFactory.createEntityManager();
//        EntityTransaction userTransaction = em.getTransaction();
//        List<RawMaterial> arr
//                = em.createNamedQuery("RawMaterial.findAll").getResultList();
//        Set<String> st = new HashSet<String>();
//
//        String[] strList = new String[arr.size() + 1];
//        strList[0] = "Select Category";
//        int i = 1;
//        for (RawMaterial elm : arr) {
//            st.add(elm.getCategory());
//        }
//        for (String elm : st) {
//            strList[i++] = elm;
//        }
//        em.close();
//        entityManagerFactory.close();
        //  return strList;
        return null;
    }

	// public static String[] getSubCategory(String category) {
    // EntityManagerFactory entityManagerFactory =
    // Persistence.createEntityManagerFactory("ERP_ORDERPU");
    // EntityManager em = entityManagerFactory.createEntityManager();
    // EntityTransaction userTransaction = em.getTransaction();
    // List<RawMaterial> arr =
    // em.createNamedQuery("RawMaterial.findAll").getResultList();
    // ArrayList<String> sub = new ArrayList<>();
    // for (RawMaterial rw : arr) {
    // if (rw.getCategory().equals(category)) {
    // sub.add(rw.getSubCategory());
    // }
    // }
    // String[] str = new String[sub.size() + 1];
    // str[0] = "Select Sub Category";
    // int i = 1;
    // for (String s : sub) {
    // str[i++] = s;
    // }
    // return str;
    // }
    // </editor-fold>  
}

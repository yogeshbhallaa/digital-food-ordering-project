
import com.vmm.JHTTPServer;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverforfoodordering extends JHTTPServer {

    public serverforfoodordering(int port) throws IOException {
        super(port);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {

        Response res = null;

        System.out.println("URI " + uri);

        if (uri.contains("/GetResource")) //request should be of the form /GetResource/one.jpg
        {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            System.out.println(uri + " *** ");
            res = sendCompleteFile(uri);
        } else if (uri.contains("/StreamMedia")) //request should be of the form /GetResource/one.jpg
        {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            System.out.println(uri + " *** ");
            res = streamFile(uri, method, header);
        } else if (uri.contains("/adminlogin")) //any custom logic , except downloading and streaming 
        {
            String username = parms.getProperty("username");
            String password = parms.getProperty("password");

            String ans = "";
            ResultSet rs = DBLoader.executeStatement("select * from admin where username = '" + username + "' and password = '" + password + "'");
            try {
                if (rs.next()) {
                    ans = "success";
                } else {
                    ans = "failed";
                }
            } catch (SQLException ex) {
                Logger.getLogger(serverforfoodordering.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/plain", ans);

        } else if (uri.contains("/adminchangepassword")) //any custom logic , except downloading and streaming 
        {
            String username = parms.getProperty("username");
            String oldpass = parms.getProperty("oldpass");
            String newpass = parms.getProperty("newpass");

            String ans = "";
            ResultSet rs = DBLoader.executeStatement("select * from admin where username = '" + username + "' and password = '" + oldpass + "'");
            try {
                if (rs.next()) {
                    rs.updateString("password", newpass);
                    rs.updateRow();
                    ans = "success";
                } else {
                    ans = "failed";
                }
            } catch (SQLException ex) {
                Logger.getLogger(serverforfoodordering.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/plain", ans);

        } else if (uri.contains("/editfooditem")) //any custom logic , except downloading and streaming 
        {
            String itemname = parms.getProperty("itemname");
            String category = parms.getProperty("category");
            String description = parms.getProperty("description");
            String type = parms.getProperty("type");
            String price = parms.getProperty("price");

            String ans = "";
            ResultSet rs = DBLoader.executeStatement("select * from fooditem where itemname = '" + itemname + "' and category='" + category + "'");
            try {
                if (rs.next()) {

                    rs.updateString("description", description);
                    rs.updateString("type", type);
                    rs.updateString("price", price);
                    rs.updateRow();
                    ans = "success";
                } else {
                    ans = "failed";
                }
            } catch (SQLException ex) {
                Logger.getLogger(serverforfoodordering.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/plain", ans);

        } else if (uri.contains("/signup2")) {
            String mobilenumber = parms.getProperty("mobilenumber");
            String name = parms.getProperty("name");
            String email = parms.getProperty("email");
            String address = parms.getProperty("address");
            boolean flag = Boolean.parseBoolean(parms.getProperty("flag"));

            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from signup where mobilenumber= '" + mobilenumber + "'");
                if (flag == false) {
                    if (rs.next()) {
                        ans = "duplicate";
                    } else {
                        rs.moveToInsertRow();
                        rs.updateString("mobilenumber", mobilenumber);
                        rs.updateString("name", name);
                        rs.updateString("email", email);
                        rs.updateString("address", address);
                        rs.insertRow();
                        ans = "success";
                    }
                } else {
                    if (rs.next()) {
                        rs.updateString("name", name);
                        rs.updateString("email", email);
                        rs.updateString("address", address);
                        rs.updateRow();
                        ans = "success";
                    } else {
                        ans = "failed";
                    }
                }

                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/AddCategory")) {
            String category = parms.getProperty("category");
            String cuisine = parms.getProperty("cuisine");
            String desc = parms.getProperty("desc");

            String filename = "";
            filename = saveFileOnServerWithRandomName(files, parms, "photo", "src/content");

            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from category where Name= '" + category + "'");
                if (rs.next()) {
                    ans = "duplicate";
                } else {
                    rs.moveToInsertRow();
                    rs.updateString("Name", category);
                    rs.updateString("Description", desc);
                    rs.updateString("Cuisine", cuisine);
                    rs.updateString("Photo", "src/content/" + filename);
                    rs.insertRow();
                    ans = "success";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/Addfooditem")) {
            String category = parms.getProperty("category");
            String itemname = parms.getProperty("itemname");
            String type = parms.getProperty("type");
            String price = parms.getProperty("price");
            String desc = parms.getProperty("description");

            String filename = "";
            filename = saveFileOnServerWithRandomName(files, parms, "photo", "src/content");

            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from fooditem where category= '" + category + "' and itemname = '" + itemname + "'");
                if (rs.next()) {
                    ans = "duplicate";
                } else {
                    rs.moveToInsertRow();
                    rs.updateString("category", category);
                    rs.updateString("itemname", itemname);
                    rs.updateString("description", desc);
                    rs.updateString("price", price);
                    rs.updateString("type", type);
                    rs.updateString("photo", "src/content/" + filename);
                    rs.insertRow();
                    ans = "success";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/getcategorybycuisine")) {
            String cuisine = parms.getProperty("cuisine");
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from category where Cuisine= '" + cuisine + "'");
                while (rs.next()) {
                    ans += rs.getString("Name") + "#$%"
                            + rs.getString("Cuisine") + "#$%"
                            + rs.getString("Description") + "#$%"
                            + rs.getString("Photo") + "~!@";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/getcategorynamebycuisine")) {
            String cuisine = parms.getProperty("cuisine");
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from category where Cuisine= '" + cuisine + "'");
                while (rs.next()) {
                    ans += rs.getString("Name") + "#$%";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/deletecategory")) {
            String category = parms.getProperty("category");
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from category where Name= '" + category + "'");
                if (rs.next()) {
                    rs.deleteRow();
                    ans = "success";
                } else {
                    ans = "failed";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/deletefooditem")) {
            String category = parms.getProperty("category");
            String itemname = parms.getProperty("itemname");
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from fooditem where category= '" + category + "' and itemname='" + itemname + "'");
                if (rs.next()) {
                    rs.deleteRow();
                    ans = "success";
                } else {
                    ans = "failed";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/getsignup2")) {
            String mobilenumber = parms.getProperty("mobilenumber");
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from signup where mobilenumber= '" + mobilenumber + "'");
                if (rs.next()) {
                    ans += rs.getString("name") + "#$%"
                            + rs.getString("email") + "#$%"
                            + rs.getString("address");
                } else {
                    ans = "failed";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
                System.out.println("ans : " + ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/getfooditems")) {
            String category = parms.getProperty("category");
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeStatement("select * from fooditem where category= '" + category + "'");
                while (rs.next()) {
                    ans += rs.getString("itemname") + "#$%"
                            + rs.getString("description") + "#$%"
                            + rs.getString("type") + "#$%"
                            + rs.getString("price") + "#$%"
                            + rs.getString("photo") + "~!@";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/FetchProfile")) {
            String email = parms.getProperty("email");
            String result = "";
            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/abc", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from users where email='" + email + "'");
                if (rs.next()) {
                    String name = rs.getString("name");
                    String password = rs.getString("pass");
                    String photo = rs.getString("photo");

                    result = result + name + ";#" + password + ";#" + photo;

                } else {
                    result = "No Such User";
                }

                res = new Response(HTTP_OK, "text/plain", result);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("/insertbill")) {
            try {
                String ans = "";
                String billdate = parms.getProperty("billdate");
                String billtime = parms.getProperty("billtime");
                String mobileno = parms.getProperty("mobileno");
                String amount = parms.getProperty("amount");
                String tax = parms.getProperty("tax");
                String paybleamount = parms.getProperty("paybleamount");
                String status = parms.getProperty("status");
                ResultSet rs = DBLoader.executeStatement("select * from bill");

                rs.moveToInsertRow();
                rs.updateString("mobileno", mobileno);
                rs.updateString("billdate", billdate);
                rs.updateString("billtime", billtime);
                rs.updateString("amount", amount);
                rs.updateString("tax", tax);
                rs.updateString("billamount", paybleamount);
                rs.updateString("status", status);
                rs.insertRow();

                ResultSet rs2 = DBLoader.executeStatement("select max(billid) billid from bill");
                if (rs2.next()) {
                    ans = rs2.getInt("billid") + "";
                } else {
                    ans = "failed";
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (uri.contains("/billdetailinsert")) {
            try {
                String ans = "";
                String billid = parms.getProperty("billid");
                String itemname = parms.getProperty("itemname");
                String price = parms.getProperty("price");
                String qty = parms.getProperty("qty");
                String totalprice = parms.getProperty("totalprice");
                ResultSet rs = DBLoader.executeStatement("select * from billdetail");

                rs.moveToInsertRow();
                rs.updateString("billid", billid);
                rs.updateString("itemname", itemname);
                rs.updateString("price", price);
                rs.updateString("qty", qty);
                rs.updateString("totalprice", totalprice);
                rs.insertRow();
                ans = "success";
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (uri.contains("/getorder")) {
            try {
                String ans = "";
                ResultSet rs = DBLoader.executeStatement("select * from bill where status = 'pending'");
                while (rs.next()) {
                    ans += rs.getString("billid") + "#$%"
                            + rs.getString("mobileno") + "#$%"
                            + rs.getString("billdate") + "#$%"
                            + rs.getString("billtime") + "#$%"
                            + rs.getString("amount") + "#$%"
                            + rs.getString("tax") + "#$%"
                            + rs.getString("billamount") + "~~~~";
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (uri.contains("/get_customer_order")) {
            try {
                String ans = "";
                String mobileno = parms.getProperty("mobileno");
                ResultSet rs = DBLoader.executeStatement("select * from bill where mobileno ='" + mobileno + "'");
                while (rs.next()) {
                    ans += rs.getString("mobileno") + "#$%"
                            + rs.getString("billdate") + "#$%"
                            + rs.getString("billtime") + "#$%"
                            + rs.getString("amount") + "#$%"
                            + rs.getString("tax") + "#$%"
                            + rs.getString("billamount") + "~~~~";
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (uri.contains("/getdetailorder")) {
            try {
                String ans = "";
                int billid = Integer.parseInt(parms.getProperty("billid"));
                ResultSet rs = DBLoader.executeStatement("select * from billdetail where billid =" + billid);
                while (rs.next()) {
                    ans += rs.getString("itemname") + "#$%"
                            + rs.getString("price") + "#$%"
                            + rs.getString("qty") + "#$%"
                            + rs.getString("totalprice") + "~~~~";
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (uri.contains("/updateorder")) {
            try {
                String status = parms.getProperty("status");
                int billid = Integer.parseInt(parms.getProperty("billid"));
                ResultSet rs = DBLoader.executeStatement("select * from bill where billid =" + billid);
                if (rs.next()) {
                    rs.updateString("status", status);
                    rs.updateRow();
                     res = new Response(HTTP_OK, "text/plain", "success");
                }
                
            } catch (Exception ex) {

            }
        } else if (uri.contains("/UploadMultipleFiles")) {
            String title = parms.getProperty("title");
            String desc = parms.getProperty("desc");

            String ans = "";
            ans = ans + " title : " + title + "\n";
            ans = ans + " desc : " + desc + "\n";
            String filename = "";

            Enumeration e = files.propertyNames();
            while (e.hasMoreElements()) {
                String name_of_1_file = e.nextElement().toString();

                filename = saveFileOnServerWithRandomName(files, parms, name_of_1_file, "src/uploaded_pics");

                ans = ans + name_of_1_file + " : " + filename + "\n";

            }
            res = new Response(HTTP_OK, "text/plain", "Files Uploaded successfully\n" + ans);

            return res;
        } else {
            res = new Response(HTTP_OK, "text/html", "<body style='background: #D46A6A; color: #fff'><center><h1>Hello</h1><br> <h3>Welcome To JHTTP Server (Version 1.0)</h3></body></center>");
        }

        return res;
    }

}

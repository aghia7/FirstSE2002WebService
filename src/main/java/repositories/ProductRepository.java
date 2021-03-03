package repositories;

import data.interfaces.IDB;
import entities.Category;
import entities.Product;
import entities.User;
import repositories.interfaces.IProductRepository;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductRepository implements IProductRepository {
    @Inject
    private IDB db;

    @Override
    public boolean create(Product product) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO products(name,description,price,category_id) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setDouble(3, product.getPrice());
            st.setInt(3, product.getCategoryId());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Product get(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT p.id, p.name, p.description, p.price, p.category_id, c.name as category_name, c.parent_id" +
                    " FROM products p, categories c WHERE p.category_id = c.id AND p.id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("category_id"),
                        new Category(
                                rs.getInt("category_id"),
                                rs.getString("category_name"),
                                rs.getInt("parent_id")
                        )

                );

                return product;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

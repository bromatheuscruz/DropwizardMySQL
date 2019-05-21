package iocruz.dropwizard.db;

import iocruz.dropwizard.api.Product;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

@RegisterRowMapper(ProductMapper.class)
public interface ProductDAO {

    @SqlQuery("SELECT * FROM product WHERE id = :id")
    Product getProductById(@Bind("id") Integer id);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO product (name, description) VALUES ( :product.name, :product.description ) ")
    Integer create(@BindBean("product") final Product product);

    @SqlQuery("SELECT * FROM product;")
    List<Product> getAll();
}

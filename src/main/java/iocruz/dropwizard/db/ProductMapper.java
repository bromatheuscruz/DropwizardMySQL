package iocruz.dropwizard.db;

import iocruz.dropwizard.api.Product;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product>
{
    @Override
    public Product map(final ResultSet rs, final StatementContext ctx) throws SQLException {
        return new Product(
          rs.getInt("id"),
          rs.getString("name"),
          rs.getString("description")
        );
    }
}

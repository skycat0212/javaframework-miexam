package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Object object, Connection connection) throws SQLException {
        Integer id = (Integer) object;
        PreparedStatement preparedStatement = connection.prepareStatement("delete from userinfo2 where id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}

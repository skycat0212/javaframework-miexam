//package kr.ac.jejunu.userdao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class GetStatementStrategy implements StatementStrategy {
//    Integer id;
//    public GetStatementStrategy(Integer id) {
//        this.id = id;
//    }
//
//    @Override
//    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo2 where id = ?");
//        preparedStatement.setLong(1, id);
//        return preparedStatement;
//    }
//}

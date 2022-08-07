package com.example.entity;

import com.example.BCrypt.BCrypt;
import com.example.DbUtil;

import java.lang.module.ResolutionException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {


private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id=?";
private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id=?";
private static final  String FIND_ALL_USER_QUERY = "SELECT * FROM users";
private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";
public User create(User user){
    try(Connection conn = DbUtil.getConnection()) {
        PreparedStatement prestmt = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
        prestmt.setString(1, user.getUserName());
        prestmt.setString(2, user.getEmail());
        prestmt.setString(3, hashPassword(user.getPassword()));
        prestmt.executeUpdate();
        ResultSet resultSet = prestmt.getGeneratedKeys();
        while (resultSet.next()){
            user.setId(resultSet.getInt(1));
        }
        return user;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

public User read(int id){
    try(Connection conn =DbUtil.getConnection()) {
        PreparedStatement prestmt = conn.prepareStatement(READ_USER_QUERY);
        prestmt.setInt(1,id);
        ResultSet resultSet = prestmt.executeQuery();
        if(resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUserName(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public void update(User user){
    try(Connection conn =DbUtil.getConnection()){
        PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER_QUERY);
        preparedStatement.setString(1,user.getUserName());
        preparedStatement.setString(2,user.getEmail());
        preparedStatement.setString(3,hashPassword(user.getPassword()));
        preparedStatement.setInt(4,user.getId());
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public List<User> findAll(){
    try(Connection conn = DbUtil.getConnection()) {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL_USER_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUserName(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            userList.add(user);
        }
        return userList;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public String delete(int id){
    try(Connection conn = DbUtil.getConnection()){
        PreparedStatement prestmt = conn.prepareStatement(DELETE_USER_QUERY);
        prestmt.setInt(1,id);
        prestmt.executeUpdate();
        return "Usuwanie zakończone sukcesem";
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}






public String hashPassword(String password){
    return BCrypt.hashpw(password,BCrypt.gensalt());
}





}

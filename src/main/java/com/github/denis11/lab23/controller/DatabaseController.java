package com.github.denis11.lab23.controller;

import com.github.denis11.lab23.modal.Record;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    private final String driverName = "com.mysql.jdbc.Driver";
    private final String connectionString = "jdbc:mysql://localhost:3306/lab";
    private final String login = "root";
    private final String password = "root";
    private Connection connection = null;

    public DatabaseController() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection(connectionString, login, password);
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
            return;
        }
        System.out.println("Successfuly connected");
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
            return;
        }
    }

    public JsonArray getAll() {
        Statement statement = null;
        List<Record> records= new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM records;");
            while(resultSet.next()){
                records.add(new Record(resultSet.getString(2),
                                       resultSet.getString(3),
                                       resultSet.getString(4),
                                       Integer.parseInt(resultSet.getString(1))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonArrayBuilder answer = Json.createArrayBuilder();
        records.forEach(record -> {
            answer.add(
                    Json.createObjectBuilder()
                            .add("id", Integer.toString(record.getId()))
                            .add("company", record.getName())
                            .add("mail", record.getMail())
                            .add("url", record.getUrl())
                            .build()
            );
        });

        return answer.build();
    }

    public void clear() {
        System.out.println("clear");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM records;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void change(Record record) {
        System.out.println("change, " + "UPDATE records SET company = '" + record.getName() + "', mail = '" + record.getMail()+ "', url = '"+record.getUrl()+"' WHERE id= "+Integer.toString(record.getId())+";");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE records SET company = '" + record.getName() + "', mail = '" + record.getMail()+ "', url = '"+record.getUrl()+"' WHERE id= "+Integer.toString(record.getId())+";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        System.out.println("delete");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM records WHERE id="+Integer.toString(id)+";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Record record) {
        System.out.println("add");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT records(company, mail, url) VALUES ('"+record.getName()+"', '"+record.getMail()+"', '"+record.getUrl()+"');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isUserIsExist(String login, String password) {
        Statement statement = null;
        boolean flag = false;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT login, pwd FROM auth WHERE login = '" + login + "';");
            String l;
            String p;
            while(resultSet.next()){
                l = resultSet.getString(1);
                p = resultSet.getString(2);

                if (password.equals(p))
                    flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}

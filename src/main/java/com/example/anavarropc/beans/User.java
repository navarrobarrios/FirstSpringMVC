package com.example.anavarropc.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Table( name = "users" )
@Entity
public class User {

    //region Variables
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @JsonIgnore
    private Integer id;
    @PrimaryKeyJoinColumn
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "user_age")
    private Integer age;
    //endregion

    //region Constructors

    public User() {
    }

    public User(Integer id, String username, String password, String name, String lastname, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.username = resultSet.getString("username");
        this.password = resultSet.getString("password");
        this.name = resultSet.getString("name");
        this.lastname = resultSet.getString("lastname");
        this.age = resultSet.getInt("user_age");
    }

    public User(JSONPObject o) {
    }

    //endregion

    //region Setters & Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //endregion

    //region Methods
    @Override
    public String toString(){
        return ("ID: " + this.getId() + " Username: " + this.getUsername() + " Password: " + this.getPassword() +
                " Name: " + this.getName() + " LastName: " + this.getLastname() +" Age: " + this.getAge());
    }
    //endregion
}

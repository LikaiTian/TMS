package com.example.web.entity;
import javax.persistence.*;

/**
 * @Author Memory
 * @Date 2021/7/7 16:30
 * @Version 1.0
 */
@Entity     //声明表的实体
@Table(name="users")     //修改表的名字
public class User
{
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略
    private Integer id;   //用户id

    @Column(columnDefinition = "varchar(255) default ''")
    private String nickname;    //用户昵称

    @Column(columnDefinition = "varchar(255) default ''")
    private String companyName; //公司名字

    @Column(columnDefinition = "varchar(255) default ''",unique = true)
    private String address;    //头像地址

    @Column(columnDefinition = "varchar(255) default ''",unique = true)
    private String phone;   //联系电话

    @Column(columnDefinition = "varchar(255) default ''")
    private String email;   //邮箱

    @Column(columnDefinition = "varchar(255) default ''")
    private String password;    //密码
    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", companyName='" + companyName + '\'' +
                ", name='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

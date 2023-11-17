package watch.store.smart_web.entity;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@Entity
@Table(name = "staff", schema = "watch_store", catalog = "")
public class StaffEntity extends BaseEntity implements Serializable{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Basic
    @Column(name = "FULL_NAME")
    private String fullName;


    @Basic
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;


    @Basic
    @Column(name = "EMAIL")
    private String email;


    @Basic
    @Column(name = "ADDRESS")
    private String address;


    @Basic
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Basic
    @Column(name = "AVATAR")
    private String avatar;

    @Basic
    @Column(name = "ROLE")
    private String role;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "STATUS")
    private String status;

    @Basic
    @Column(name = "NOTE")
    private String note;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

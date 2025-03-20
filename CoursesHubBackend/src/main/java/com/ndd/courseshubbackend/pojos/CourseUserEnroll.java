/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndd.courseshubbackend.pojos;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Apelion283
 */
@Entity
@Table(name = "course_user_enroll")
@NamedQueries({
    @NamedQuery(name = "CourseUserEnroll.findAll", query = "SELECT c FROM CourseUserEnroll c"),
    @NamedQuery(name = "CourseUserEnroll.findById", query = "SELECT c FROM CourseUserEnroll c WHERE c.id = :id"),
    @NamedQuery(name = "CourseUserEnroll.findByEnrollDate", query = "SELECT c FROM CourseUserEnroll c WHERE c.enrollDate = :enrollDate")})
public class CourseUserEnroll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enroll_date")
    @Temporal(TemporalType.DATE)
    private Date enrollDate;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Payment payment;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public CourseUserEnroll() {
    }

    public CourseUserEnroll(Integer id) {
        this.id = id;
    }

    public CourseUserEnroll(Integer id, Date enrollDate) {
        this.id = id;
        this.enrollDate = enrollDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Course getCourseId() {
        return course;
    }

    public void setCourseId(Course courseId) {
        this.course = courseId;
    }

    public Payment getPaymentId() {
        return payment;
    }

    public void setPaymentId(Payment paymentId) {
        this.payment = paymentId;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseUserEnroll)) {
            return false;
        }
        CourseUserEnroll other = (CourseUserEnroll) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ndd.courseshubbackend.pojos.CourseUserEnroll[ id=" + id + " ]";
    }
    
}

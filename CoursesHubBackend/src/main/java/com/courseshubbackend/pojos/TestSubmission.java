/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseshubbackend.pojos;

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
@Table(name = "test_submission")
@NamedQueries({
    @NamedQuery(name = "TestSubmission.findAll", query = "SELECT t FROM TestSubmission t"),
    @NamedQuery(name = "TestSubmission.findById", query = "SELECT t FROM TestSubmission t WHERE t.id = :id"),
    @NamedQuery(name = "TestSubmission.findByScore", query = "SELECT t FROM TestSubmission t WHERE t.score = :score"),
    @NamedQuery(name = "TestSubmission.findBySubmitDate", query = "SELECT t FROM TestSubmission t WHERE t.submitDate = :submitDate")})
public class TestSubmission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private double score;
    @Basic(optional = false)
    @NotNull
    @Column(name = "submit_date")
    @Temporal(TemporalType.DATE)
    private Date submitDate;
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Test test;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public TestSubmission() {
    }

    public TestSubmission(Integer id) {
        this.id = id;
    }

    public TestSubmission(Integer id, double score, Date submitDate) {
        this.id = id;
        this.score = score;
        this.submitDate = submitDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Test getTestId() {
        return test;
    }

    public void setTestId(Test testId) {
        this.test = testId;
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
        if (!(object instanceof TestSubmission)) {
            return false;
        }
        TestSubmission other = (TestSubmission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ndd.courseshubbackend.pojos.TestSubmission[ id=" + id + " ]";
    }
    
}

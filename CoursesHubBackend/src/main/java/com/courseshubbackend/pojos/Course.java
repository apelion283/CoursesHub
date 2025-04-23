/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseshubbackend.pojos;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Apelion283
 */
@Entity
@Table(name = "course")
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name"),
    @NamedQuery(name = "Course.findByPrice", query = "SELECT c FROM Course c WHERE c.price = :price"),
    @NamedQuery(name = "Course.findByAvearageStar", query = "SELECT c FROM Course c WHERE c.avearageStar = :avearageStar"),
    @NamedQuery(name = "Course.findByCreateDate", query = "SELECT c FROM Course c WHERE c.createDate = :createDate")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "avearage_star")
    private double avearageStar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Chapter> chapterSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CourseProgress> courseProgressSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Certificate> certificateSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CourseUserEnroll> courseUserEnrollSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<ReviewStar> reviewStarSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<ReviewComment> reviewCommentSet;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String name, int price, double avearageStar, Date createDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.avearageStar = avearageStar;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getAvearageStar() {
        return avearageStar;
    }

    public void setAvearageStar(double avearageStar) {
        this.avearageStar = avearageStar;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Chapter> getChapterSet() {
        return chapterSet;
    }

    public void setChapterSet(Set<Chapter> chapterSet) {
        this.chapterSet = chapterSet;
    }

    public Set<CourseProgress> getCourseProgressSet() {
        return courseProgressSet;
    }

    public void setCourseProgressSet(Set<CourseProgress> courseProgressSet) {
        this.courseProgressSet = courseProgressSet;
    }

    public Set<Certificate> getCertificateSet() {
        return certificateSet;
    }

    public void setCertificateSet(Set<Certificate> certificateSet) {
        this.certificateSet = certificateSet;
    }

    public Set<CourseUserEnroll> getCourseUserEnrollSet() {
        return courseUserEnrollSet;
    }

    public void setCourseUserEnrollSet(Set<CourseUserEnroll> courseUserEnrollSet) {
        this.courseUserEnrollSet = courseUserEnrollSet;
    }

    public Set<ReviewStar> getReviewStarSet() {
        return reviewStarSet;
    }

    public void setReviewStarSet(Set<ReviewStar> reviewStarSet) {
        this.reviewStarSet = reviewStarSet;
    }

    public Set<ReviewComment> getReviewCommentSet() {
        return reviewCommentSet;
    }

    public void setReviewCommentSet(Set<ReviewComment> reviewCommentSet) {
        this.reviewCommentSet = reviewCommentSet;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ndd.courseshubbackend.pojos.Course[ id=" + id + " ]";
    }
    
}

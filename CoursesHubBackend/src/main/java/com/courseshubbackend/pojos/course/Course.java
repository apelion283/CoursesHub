/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseshubbackend.pojos.course;

import com.courseshubbackend.pojos.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.*;

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
    @NamedQuery(name = "Course.findByAvearageStar", query = "SELECT c FROM Course c WHERE c.averageStar = :avearageStar"),
    @NamedQuery(name = "Course.findByCreateDate", query = "SELECT c FROM Course c WHERE c.createdDate = :createDate")})
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
    @Column(name = "average_star")
    private double averageStar;
    @Lob
    @Size(max = 65535)
    @Column(name = "image_url")
    @NotNull
    private String imageUrl;
    @Basic(optional = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "category_id")
    private Category category;
    @Transient
    private MultipartFile file;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    private List<Chapter> chapterList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CourseProgress> courseProgressSet = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Certificate> certificateSet = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CourseUserEnroll> courseUserEnrollSet = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<ReviewStar> reviewStarSet = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<ReviewComment> reviewCommentSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "teacher_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Teacher teacher;

    @NotNull
    @ColumnDefault("0")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "course_status", nullable = false)
    private CourseStatusEnum courseStatus = CourseStatusEnum.ACTIVE;

    public CourseStatusEnum getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatusEnum courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String name, int price, double averageStar, Date createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.averageStar = averageStar;
        this.createdDate = createdDate;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdDate == null) {
            this.createdDate = new Date();
        }
        if(this.averageStar == 0){
            this.averageStar = 0;
        }
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

    public double getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(double averageStar) {
        this.averageStar = averageStar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public boolean hasChapters() {
        return chapterList != null && !chapterList.isEmpty();
    }
}

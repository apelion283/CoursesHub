/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseshubbackend.pojos;

import com.courseshubbackend.pojos.course.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Apelion283
 */
@Entity
@Table(name = "chapter")
@NamedQueries({
    @NamedQuery(name = "Chapter.findAll", query = "SELECT c FROM Chapter c"),
    @NamedQuery(name = "Chapter.findById", query = "SELECT c FROM Chapter c WHERE c.id = :id"),
    @NamedQuery(name = "Chapter.findByName", query = "SELECT c FROM Chapter c WHERE c.name = :name"),
    @NamedQuery(name = "Chapter.findByChapterOrder", query = "SELECT c FROM Chapter c WHERE c.chapterOrder = :chapterOrder"),
    @NamedQuery(name = "Chapter.findByCreateDate", query = "SELECT c FROM Chapter c WHERE c.createdDate = :createDate")})
public class Chapter implements Serializable {

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
    @Column(name = "chapter_order")
    private int chapterOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course course;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapter")
    private List<CourseProgress> courseProgressList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapter")
    private List<Test> testList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapter", orphanRemoval = true)
    private List<Lecture> lectureList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapter", orphanRemoval = true)
    private List<Resource> resourceList = new ArrayList<>();

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public Chapter() {
    }

    public Chapter(Integer id) {
        this.id = id;
    }

    public Chapter(Integer id, String name, int chapterOrder, Date createdDate) {
        this.id = id;
        this.name = name;
        this.chapterOrder = chapterOrder;
        this.createdDate = createdDate;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdDate == null) {
            this.createdDate = new Date();
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

    public int getChapterOrder() {
        return chapterOrder;
    }

    public void setChapterOrder(int chapterOrder) {
        this.chapterOrder = chapterOrder;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<CourseProgress> getCourseProgressList() {
        return courseProgressList;
    }

    public void setCourseProgressList(List<CourseProgress> courseProgressList) {
        this.courseProgressList = courseProgressList;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public List<Lecture> getLectureList() {
        return lectureList;
    }

    public void setLectureList(List<Lecture> lectureList) {
        this.lectureList = lectureList;
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
        if (!(object instanceof Chapter)) {
            return false;
        }
        Chapter other = (Chapter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ndd.courseshubbackend.pojos.Chapter[ id=" + id + " ]";
    }
    
}

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
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author Apelion283
 */
@Entity
@Table(name = "course_progress")
@NamedQueries({
    @NamedQuery(name = "CourseProgress.findAll", query = "SELECT c FROM CourseProgress c"),
    @NamedQuery(name = "CourseProgress.findById", query = "SELECT c FROM CourseProgress c WHERE c.id = :id"),
    @NamedQuery(name = "CourseProgress.findByProgress", query = "SELECT c FROM CourseProgress c WHERE c.progress = :progress"),
    @NamedQuery(name = "CourseProgress.findByIsCompleteCourse", query = "SELECT c FROM CourseProgress c WHERE c.isCompleteCourse = :isCompleteCourse")})
public class CourseProgress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "progress")
    private double progress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_complete_course")
    private boolean isCompleteCourse;
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Chapter chapter;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lecture lecture;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public CourseProgress() {
    }

    public CourseProgress(Integer id) {
        this.id = id;
    }

    public CourseProgress(Integer id, double progress, boolean isCompleteCourse) {
        this.id = id;
        this.progress = progress;
        this.isCompleteCourse = isCompleteCourse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public boolean getIsCompleteCourse() {
        return isCompleteCourse;
    }

    public void setIsCompleteCourse(boolean isCompleteCourse) {
        this.isCompleteCourse = isCompleteCourse;
    }

    public Chapter getChapterId() {
        return chapter;
    }

    public void setChapterId(Chapter chapterId) {
        this.chapter = chapterId;
    }

    public Course getCourseId() {
        return course;
    }

    public void setCourseId(Course courseId) {
        this.course = courseId;
    }

    public Lecture getLectureId() {
        return lecture;
    }

    public void setLectureId(Lecture lectureId) {
        this.lecture = lectureId;
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
        if (!(object instanceof CourseProgress)) {
            return false;
        }
        CourseProgress other = (CourseProgress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ndd.courseshubbackend.pojos.CourseProgress[ id=" + id + " ]";
    }
    
}

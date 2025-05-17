/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseshubbackend.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Apelion283
 */
@Entity
@Table(name = "lecture")
@NamedQueries({
    @NamedQuery(name = "Lecture.findAll", query = "SELECT l FROM Lecture l"),
    @NamedQuery(name = "Lecture.findById", query = "SELECT l FROM Lecture l WHERE l.id = :id"),
    @NamedQuery(name = "Lecture.findByName", query = "SELECT l FROM Lecture l WHERE l.name = :name"),
    @NamedQuery(name = "Lecture.findByVideoUrl", query = "SELECT l FROM Lecture l WHERE l.videoUrl = :videoUrl"),
    @NamedQuery(name = "Lecture.findByDocumentUrl", query = "SELECT l FROM Lecture l WHERE l.documentUrl = :documentUrl"),
    @NamedQuery(name = "Lecture.findByLectureOrder", query = "SELECT l FROM Lecture l WHERE l.lectureOrder = :lectureOrder"),
    @NamedQuery(name = "Lecture.findByCreateDate", query = "SELECT l FROM Lecture l WHERE l.createdDate = :createDate")})
public class Lecture implements Serializable {

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
    @Size(max = 255)
    @Column(name = "video_url")
    private String videoUrl;
    @Size(max = 255)
    @Column(name = "document_url")
    private String documentUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lecture_order")
    private int lectureOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private Set<CourseProgress> courseProgressSet;
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Chapter chapter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private Set<Conversation> conversationSet;

    @Transient
    private MultipartFile videoFile;
    @Transient
    private MultipartFile documentFile;

    @PrePersist
    protected void onCreate() {
        if (this.createdDate == null) {
            this.createdDate = new Date();
        }
    }

    public Lecture() {
    }

    public Lecture(Integer id) {
        this.id = id;
    }

    public Lecture(Integer id, String name, int lectureOrder, Date createdDate) {
        this.id = id;
        this.name = name;
        this.lectureOrder = lectureOrder;
        this.createdDate = createdDate;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public int getLectureOrder() {
        return lectureOrder;
    }

    public void setLectureOrder(int lectureOrder) {
        this.lectureOrder = lectureOrder;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<CourseProgress> getCourseProgressSet() {
        return courseProgressSet;
    }

    public void setCourseProgressSet(Set<CourseProgress> courseProgressSet) {
        this.courseProgressSet = courseProgressSet;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }


    public Set<Conversation> getConversationSet() {
        return conversationSet;
    }

    public void setConversationSet(Set<Conversation> conversationSet) {
        this.conversationSet = conversationSet;
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
        if (!(object instanceof Lecture)) {
            return false;
        }
        Lecture other = (Lecture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ndd.courseshubbackend.pojos.Lecture[ id=" + id + " ]";
    }

    public MultipartFile getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(MultipartFile videoFile) {
        this.videoFile = videoFile;
    }

    public MultipartFile getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(MultipartFile documentFile) {
        this.documentFile = documentFile;
    }
}

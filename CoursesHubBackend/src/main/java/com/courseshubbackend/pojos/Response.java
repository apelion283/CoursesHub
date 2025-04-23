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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@Table(name = "response")
@NamedQueries({
    @NamedQuery(name = "Response.findAll", query = "SELECT r FROM Response r"),
    @NamedQuery(name = "Response.findById", query = "SELECT r FROM Response r WHERE r.id = :id"),
    @NamedQuery(name = "Response.findByCreateDate", query = "SELECT r FROM Response r WHERE r.createDate = :createDate"),
    @NamedQuery(name = "Response.findByIsTeacherResponse", query = "SELECT r FROM Response r WHERE r.isTeacherResponse = :isTeacherResponse")})
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_teacher_response")
    private boolean isTeacherResponse;
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conversation conversation;
    @OneToMany(mappedBy = "parentResponse")
    private Set<Response> responseSet;
    @JoinColumn(name = "parent_response_id", referencedColumnName = "id")
    @ManyToOne
    private Response parentResponse;

    public Response() {
    }

    public Response(Integer id) {
        this.id = id;
    }

    public Response(Integer id, String content, Date createDate, boolean isTeacherResponse) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.isTeacherResponse = isTeacherResponse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean getIsTeacherResponse() {
        return isTeacherResponse;
    }

    public void setIsTeacherResponse(boolean isTeacherResponse) {
        this.isTeacherResponse = isTeacherResponse;
    }

    public Conversation getConversationId() {
        return conversation;
    }

    public void setConversationId(Conversation conversationId) {
        this.conversation = conversationId;
    }

    public Set<Response> getResponseSet() {
        return responseSet;
    }

    public void setResponseSet(Set<Response> responseSet) {
        this.responseSet = responseSet;
    }

    public Response getParentResponseId() {
        return parentResponse;
    }

    public void setParentResponseId(Response parentResponseId) {
        this.parentResponse = parentResponseId;
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
        if (!(object instanceof Response)) {
            return false;
        }
        Response other = (Response) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ndd.courseshubbackend.pojos.Response[ id=" + id + " ]";
    }
    
}

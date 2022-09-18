package com.ionutgradinaru.xlsx.domain_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FileMetadata {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  private String name;
  private Long size;
  @CreationTimestamp
  private Date creationDate;
  @CreationTimestamp
  private Date uploadDate;
}

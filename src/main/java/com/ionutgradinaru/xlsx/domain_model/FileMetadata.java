package com.ionutgradinaru.xlsx.domain_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "file_metadata")
public class FileMetadata {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  private String name;
  private Long size;

  @Column(name = "creation_date")
  @CreationTimestamp
  private Date creationDate;

  @Column(name = "upload_date")
  @CreationTimestamp
  private Date uploadDate;
}

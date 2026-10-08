package com.chuseok22.eodaegoserver.domain.course.entity;

import com.chuseok22.eodaegoserver.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "course")
public class Course extends BaseEntity {
  @Column(nullable = false)
  private String title;

  @Builder.Default
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "course_interest_type", joinColumns = @JoinColumn(name = "course_id"))
  @Column(name = "interest_type", nullable = false)
  @OrderColumn(name = "interest_order")
  private List<String> interestTypes = new ArrayList<>();

  @Builder.Default
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "course_tag_label", joinColumns = @JoinColumn(name = "course_id"))
  @Column(name = "tag_label", nullable = false)
  @OrderColumn(name = "tag_order")
  private List<String> tagLabels = new ArrayList<>();

  @Column(nullable = false)
  private Integer estimatedDurationMinutes;

  @Column(nullable = false)
  private String entrance;

  @Column(nullable = false)
  private String exit;

  @Builder.Default
  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @OrderBy("visitOrder ASC")
  private List<CoursePlace> places = new ArrayList<>();
}

package com.chuseok22.eodaegoserver.domain.course.entity;

import com.chuseok22.eodaegoserver.domain.course.CourseRecommendationFailureType;
import com.chuseok22.eodaegoserver.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
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
@Table(name = "course_recommendation_failure_log")
public class CourseRecommendationFailureLog extends BaseEntity {
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CourseRecommendationFailureType failureType;

  @Column(nullable = false, columnDefinition = "text")
  private String message;
}

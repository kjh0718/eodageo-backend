package com.chuseok22.eodaegoserver.domain.facility.entity;

import com.chuseok22.eodaegoserver.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
@Table(name = "facility", uniqueConstraints = {
    @UniqueConstraint(name = "uk_facility_ai_id", columnNames = "ai_facility_id")
})
public class Facility extends BaseEntity {
  @Column(name = "ai_facility_id")
  private Long aiFacilityId;

  private String code;

  private String sourceCategory;

  @Column(nullable = false)
  private String name;

  @Column(columnDefinition = "text")
  private String intro;

  @Column(columnDefinition = "text")
  private String description;

  private Double latitude;

  private Double longitude;

  private String facilityType;

  private LocalDateTime lastSeenAt;

  private LocalTime openTime;

  private LocalTime closeTime;

  @Column(columnDefinition = "text")
  private String operatingNote;
}

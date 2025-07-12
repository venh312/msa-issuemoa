package com.issuemoa.batch.domain.subsidy;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Table(name = "subsidy")
@Entity
public class Subsidy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationDatetime ;
    private String departmentName;
    private String userType;
    private String detailViewUrl;
    private String serviceId;
    private String serviceName;
    private String servicePurposeSummary;
    private String serviceCategory;
    private String eligibilityCriteria;
    private String responsibleAgencyName;
    private String responsibleAgencyType;
    private String responsibleAgencyCode;
    private String lastModifiedDatetime;
    private String applicationPeriod;
    private String applicationMethod;
    private String contactInfo;
    private String receivingAgency;
    private String viewCount;
    private String supportDetails;
    private String eligibleRecipients;
    private String supportType;
    private Long registerId;
    private Long modifyId;
    private LocalDateTime registerTime;
    private LocalDateTime modifyTime;
}

package com.issuemoa.batch.domain.prodcut;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Table(name = "products")
@Entity
public class Product {
    @Id
    private Long goodsId;
    private String name;
    private String unitDivCode;
    private String baseCnt;
    private String smlclsCode;
    private String detailMean;
    private String totalCnt;
    private String totalDivCode;
    private Long registerId;
    private Long modifyId;
    private LocalDateTime registerTime;
    private LocalDateTime modifyTime;
}

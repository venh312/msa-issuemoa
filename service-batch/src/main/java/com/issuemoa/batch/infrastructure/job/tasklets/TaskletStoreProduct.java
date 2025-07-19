package com.issuemoa.batch.infrastructure.job.tasklets;

import com.issuemoa.batch.domain.prodcut.Product;
import com.issuemoa.batch.domain.prodcut.ProductRepository;
import com.issuemoa.batch.domain.store.Store;
import com.issuemoa.batch.infrastructure.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Component
@StepScope
public class TaskletStoreProduct implements Tasklet, StepExecutionListener {
    private String exitCode = "FAILED";
    private String exitMessage = "";
    private int success = 0;
    @Value("#{jobParameters[requestDate]}")
    private String requestDate;
    @Value("${endpoint.open.product.info}")
    private String endpointProductInfo;

    @Value("${api.key.open}")
    private String serviceKey;
    private final HttpUtil httpUtil;
    private final ProductRepository productRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("[beforeStep] => " + stepExecution);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
        final long batchStartTime = System.currentTimeMillis();

        try {
            log.info("[Tasklet 실행 requestDate] => {}", requestDate);
            log.info("==> [TaskletProduct] API 호출 시작");

            // 외부 API 호출
            log.info("==> [ProductReader] API 호출 시작");
            String url = endpointProductInfo + "?serviceKey=" + serviceKey;

            // 외부 API 호출
            NodeList nodeList = httpUtil.sendAndReceiveXml(url, "", false, "application/json", "", "item");

            if (nodeList != null && nodeList.getLength() > 0) {
                List<Product> products = process(nodeList);
                if (!products.isEmpty()) write(products);
            }

            this.exitCode = "COMPLETED";
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage(), e);
            this.exitMessage = e.getMessage();
        }

        log.info("[" + this.getClass().getSimpleName() + "] :: " + ((System.currentTimeMillis() - batchStartTime) / 1000.0) + " 처리 시간(초)");
        log.info("[" + this.getClass().getSimpleName() + "] :: " + success + "건 등록");

        return RepeatStatus.FINISHED;
    }

    public List<Product> process(NodeList nodes) {
        log.info("==> [ProductProcessor] nodes.size :: {}", nodes.getLength());

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Product product = Product.builder()
                    .goodsId(Long.valueOf(getTagValue("goodId", element)))
                    .name(getTagValue("goodName", element))
                    .unitDivCode(getTagValue("goodUnitDivCode", element))
                    .baseCnt(getTagValue("goodBaseCnt", element))
                    .smlclsCode(getTagValue("goodSmlclsCode", element))
                    .detailMean(getTagValue("detailMean", element))
                    .totalCnt(getTagValue("goodTotalCnt", element))
                    .totalDivCode(getTagValue("goodTotalDivCode", element))
                    .registerId(0L)
                    .build();
            products.add(product);
        }

        return products;
    }

    public void write(List<Product> products) {
        log.info("==> [Product Writer] products.size :: {}", products.size());
        LocalDateTime now = LocalDateTime.now();

        // ID 기준 존재 여부 판별
        List<Long> ids = products.stream()
                .map(Product::getGoodsId)
                .filter(Objects::nonNull)
                .toList();

        Set<Long> existingIds = new HashSet<>(productRepository.findAllById(ids).stream()
                .map(Product::getGoodsId)
                .toList());

        for (Product product : products) {
            if (product.getGoodsId() != null && existingIds.contains(product.getGoodsId())) {
                product.setModifyTime(now);
            } else {
                product.setRegisterTime(now);
            }
        }

        success = products.size();
        productRepository.saveAll(products); // JPA가 insert/update 처리
    }

    private String getTagValue(String tagName, Element element) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0)
            return nodeList.item(0).getTextContent();
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("[TaskletProduct 종료] Status => " + stepExecution.getStatus());
        return new ExitStatus(exitCode, exitMessage);
    }
}

package com.issuemoa.batch.infrastructure.processor;

import com.issuemoa.batch.domain.prodcut.price.ProductPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ProductPriceProcessor implements ItemProcessor<NodeList, List<ProductPrice>> {
    private StepExecution stepExecution;

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) { // Step 실행 직전에 StepExecution 주입
        this.stepExecution = stepExecution;
    }

    @Override
    public List<ProductPrice> process(NodeList nodes) {
        log.info("==> [ProductPriceProcessor] nodes.length :: {}", nodes.getLength());
        if (nodes.getLength() == 0) {
            log.info("==> [ProductPriceProcessor] Batch Stop List Empty.");
            stepExecution.setTerminateOnly(); // 현재 step 중단
            return null;
        }

        List<ProductPrice> productPrices = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            ProductPrice productPrice = ProductPrice.builder()
                            .inspectDay(getTagValue("goodInspectDay", element))
                            .entpId(getTagValue("entpId", element))
                            .goodsId(getTagValue("goodId", element))
                            .price(getTagValue("goodPrice", element))
                            .plusOneYn(getTagValue("plusoneYn", element))
                            .dcYn(getTagValue("goodDcYn", element))
                            .dcStartDay(getTagValue("goodDcStartDay", element))
                            .dcEndDay(getTagValue("goodDcEndDay", element))
                            .registerId(0L)
                            .registerTime(LocalDateTime.now())
                            .build();
            productPrices.add(productPrice);
        }

        return productPrices;
    }

    private String getTagValue(String tagName, Element element) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0)
            return nodeList.item(0).getTextContent();
        return null;
    }
}

package com.issuemoa.batch.infrastructure.job.tasklets;

import com.issuemoa.batch.domain.store.Store;
import com.issuemoa.batch.domain.store.StoreRepository;
import com.issuemoa.batch.domain.subsidy.Subsidy;
import com.issuemoa.batch.domain.subsidy.SubsidyRepository;
import com.issuemoa.batch.infrastructure.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
public class TaskletStores implements Tasklet, StepExecutionListener {
    private String exitCode = "FAILED";
    private String exitMessage = "";
    private int success = 0;
    @Value("#{jobParameters[requestDate]}")
    private String requestDate;
    @Value("${endpoint.open.product.store}")
    private String endpointProductStore;
    @Value("${api.key.open}")
    private String serviceKey;
    private final HttpUtil httpUtil;
    private final StoreRepository storeRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("[beforeStep] => " + stepExecution);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
        final long batchStartTime = System.currentTimeMillis();

        try {
            log.info("[Tasklet 실행 requestDate] => {}", requestDate);
            log.info("==> [TaskletStores] API 호출 시작");

            // 외부 API 호출
            String url = endpointProductStore + "?serviceKey=" + serviceKey;
            NodeList nodeList = httpUtil.sendAndReceiveXml(url, "", false, "application/json", "", "iros.openapi.service.vo.entpInfoVO");

            if (nodeList != null && nodeList.getLength() > 0) {
                List<Store> stores = process(nodeList);
                if (!stores.isEmpty()) write(stores);
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

    private List<Store> process(NodeList nodes) {
        log.info("==> [StoreProcessor] nodes.size :: {}", nodes.getLength());

        List<Store> stores = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Store store = Store.builder()
                    .entpId(Long.valueOf(getTagValue("entpId", element)))
                    .name(getTagValue("entpName", element))
                    .typeCode(getTagValue("entpTypeCode", element))
                    .areaCode(getTagValue("entpAreaCode", element))
                    .detailCode(getTagValue("areaDetailCode", element))
                    .tel(getTagValue("entpTelno", element))
                    .postNo(getTagValue("postNo", element))
                    .addr(getTagValue("plmkAddrBasic", element))
                    .addrDetail(getTagValue("plmkAddrDetail", element))
                    .roadAddr(getTagValue("roadAddrBasic", element))
                    .roadAddrDetail(getTagValue("roadAddrDetail", element))
                    .xCoord(getTagValue("xMapCoord", element))
                    .yCoord(getTagValue("yMapCoord", element))
                    .registerId(0L)
                    .build();
            stores.add(store);
        }

        return stores;
    }

    private void write(List<Store> stores) {
        log.info("==> [StoreWriter] stores.size :: {}", stores.size());
        LocalDateTime now = LocalDateTime.now();

        // ID 기준으로 존재 여부 판별 (store.getEntpId() 기준)
        List<Long> ids = stores.stream()
                .map(Store::getEntpId)
                .filter(Objects::nonNull)
                .toList();

        Set<Long> existingIds = new HashSet<>(storeRepository.findAllById(ids).stream()
                .map(Store::getEntpId)
                .toList());

        for (Store store : stores) {
            if (store.getEntpId() != null && existingIds.contains(store.getEntpId())) {
                store.setModifyTime(now);
            } else {
                store.setRegisterTime(now);
            }
        }

        success = stores.size();
        storeRepository.saveAll(stores); // JPA가 insert/update 처리
    }

    private String getTagValue(String tagName, Element element) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0)
            return nodeList.item(0).getTextContent();
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("[TaskletStores 종료] Status => " + stepExecution.getStatus());
        return new ExitStatus(exitCode, exitMessage);
    }
}

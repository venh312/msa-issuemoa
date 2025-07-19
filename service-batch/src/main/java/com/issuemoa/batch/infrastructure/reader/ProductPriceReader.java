package com.issuemoa.batch.infrastructure.reader;

import com.issuemoa.batch.domain.store.Store;
import com.issuemoa.batch.domain.store.StoreRepository;
import com.issuemoa.batch.infrastructure.utils.DateUtil;
import com.issuemoa.batch.infrastructure.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProductPriceReader implements ItemReader<NodeList> {
    private final HttpUtil httpUtil;
    private final StoreRepository storeRepository;

    @Value("${endpoint.open.product.price}")
    private String endpointProductPrice;
    @Value("${api.key.open}")
    private String openApiKey;

    private int page = 0;

    @Override
    public NodeList read() throws InterruptedException {
        // 1초 대기
        Thread.sleep(1000);
        log.info("==> [ProductPriceReader] API 호출 시작 Page :: {}", page);
        int size = 1;

        Page<Store> stores = storeRepository.findAll(PageRequest.of(page, size));

        // 조회 데이터가 없을 때 Reader 종료
        if (stores.isEmpty()) {
            page = 0; // 초기화
            return null;
        }

        String goodInspectDay = DateUtil.getYesterday();
        Long entpId = stores.getContent().get(0).getEntpId();
        String url = endpointProductPrice + "?serviceKey=" + openApiKey + "&goodInspectDay=" + goodInspectDay + "&entpId=" + entpId;

        // 외부 API 호출
        NodeList nodeList = httpUtil.sendAndReceiveXml(url, "", false, "application/json", "", "iros.openapi.service.vo.goodPriceVO");
        page++;

        return nodeList;
    }
}
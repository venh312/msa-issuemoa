package com.issuemoa.batch.infrastructure.job.tasklets;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
@StepScope
public class TaskletSubsidy implements Tasklet, StepExecutionListener {
    private String exitCode = "FAILED";
    private String exitMessage = "";
    private int success = 0;
    private static final int PAGE_SIZE = 500;
    @Value("#{jobParameters[requestDate]}")
    private String requestDate;
    @Value("${endpoint.open.subsidy.list}")
    private String endpointSubsidyList;
    @Value("${api.key.open}")
    private String serviceKey;
    private final HttpUtil httpUtil;
    private static final String BASE_CONDITION = "&cond%5B%EB%93%B1%EB%A1%9D%EC%9D%BC%EC%8B%9C%3A%3AGTE%5D=202001010000000";
    private final SubsidyRepository subsidyRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("[beforeStep] => " + stepExecution);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
        final long batchStartTime = System.currentTimeMillis();

        try {
            log.info("[Tasklet 실행 requestDate] => {}", requestDate);

            log.info("==> [TaskletSubsidy] API 호출 시작");
            List<Subsidy> allSubsidies = new ArrayList<>();
            int page = 1;

            while (true) {
                try {
                    String requestUrl = buildUrl(page);

                    JSONObject response = httpUtil.send(requestUrl, "", false, "application/json", "");

                    int currentCount = response.getInt("currentCount");

                    JSONArray data = response.getJSONArray("data");

                    allSubsidies.addAll(parseSubsidyList(data));

                    if (currentCount < PAGE_SIZE) {
                        success = allSubsidies.size();
                        break;
                    }

                    page++;

                    // 1초 대기
                    Thread.sleep(1000);
                } catch (Exception e) {
                    log.error("API 응답 파싱 중 오류 발생", e);
                    break;
                }
            }

            subsidyRepository.deleteAll();
            subsidyRepository.saveAll(allSubsidies);

            this.exitCode = "COMPLETED";
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage(), e);
            this.exitMessage = e.getMessage();
        }

        log.info("[" + this.getClass().getSimpleName() + "] :: " + ((System.currentTimeMillis() - batchStartTime) / 1000.0) + " 처리 시간(초)");
        log.info("[" + this.getClass().getSimpleName() + "] :: " + success + "건 등록");

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("[TaskletSubsidyDetail 종료] Status => " + stepExecution.getStatus());
        return new ExitStatus(exitCode, exitMessage);
    }

    private String buildUrl(int page) {
        return String.format("%s?serviceKey=%s&page=%d&perPage=%d%s",
                endpointSubsidyList, serviceKey, page, PAGE_SIZE, BASE_CONDITION);
    }

    private List<Subsidy> parseSubsidyList(JSONArray jsonArray) throws JSONException {
        List<Subsidy> subsidyList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            Subsidy subsidy = Subsidy.builder()
                    .registrationDatetime(obj.optString("등록일시"))
                    .departmentName(obj.optString("부서명"))
                    .userType(obj.optString("사용자구분"))
                    .detailViewUrl(obj.optString("상세조회URL"))
                    .serviceId(obj.optString("서비스ID"))
                    .serviceName(obj.optString("서비스명"))
                    .servicePurposeSummary(obj.optString("서비스목적요약"))
                    .serviceCategory(obj.optString("서비스분야"))
                    .eligibilityCriteria(obj.optString("선정기준"))
                    .responsibleAgencyName(obj.optString("소관기관명"))
                    .responsibleAgencyType(obj.optString("소관기관유형"))
                    .responsibleAgencyCode(obj.optString("소관기관코드"))
                    .lastModifiedDatetime(obj.optString("수정일시"))
                    .applicationPeriod(obj.optString("신청기한"))
                    .applicationMethod(obj.optString("신청방법"))
                    .contactInfo(obj.optString("전화문의"))
                    .receivingAgency(obj.optString("접수기관"))
                    .viewCount(String.valueOf(obj.optInt("조회수")))
                    .supportDetails(obj.optString("지원내용"))
                    .eligibleRecipients(obj.optString("지원대상"))
                    .supportType(obj.optString("지원유형"))
                    .registerId(0L)
                    .registerTime(LocalDateTime.now())
                    .build();

            subsidyList.add(subsidy);
        }

        return subsidyList;
    }
}

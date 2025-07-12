package com.issuemoa.batch.infrastructure.job.tasklets;

import com.issuemoa.batch.domain.subsidy.detail.SubsidyDetail;
import com.issuemoa.batch.domain.subsidy.detail.SubsidyDetailRepository;
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
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
@StepScope
public class TaskletSubsidyDetail implements Tasklet, StepExecutionListener {
    private String exitCode = "FAILED";
    private String exitMessage = "";
    private int success = 0;
    private static final int PAGE_SIZE = 500;
    @Value("#{jobParameters[requestDate]}")
    private String requestDate;
    @Value("${endpoint.open.subsidy.detail}")
    private String endpointSubsidyDetail;
    @Value("${api.key.open}")
    private String serviceKey;
    private final HttpUtil httpUtil;
    private final SubsidyDetailRepository subsidyDetailRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("[beforeStep] => " + stepExecution);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
        final long batchStartTime = System.currentTimeMillis();

        try {
            log.info("[Tasklet 실행 requestDate] => {}", requestDate);

            log.info("==> [TaskletSubsidyDetail] API 호출 시작");
            List<SubsidyDetail> allSubsidies = new ArrayList<>();
            int page = 1;

            while (true) {
                try {
                    String requestUrl = buildUrl(page);

                    JSONObject response = httpUtil.send(requestUrl, "", false, "application/json", "");

                    int currentCount = response.getInt("currentCount");

                    JSONArray data = response.getJSONArray("data");

                    allSubsidies.addAll(parseSubsidyDetailList(data));

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

            subsidyDetailRepository.deleteAll();
            subsidyDetailRepository.saveAll(allSubsidies);

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
        return String.format("%s?serviceKey=%s&page=%d&perPage=%d",
                endpointSubsidyDetail, serviceKey, page, PAGE_SIZE);
    }

    private List<SubsidyDetail> parseSubsidyDetailList(JSONArray jsonArray) throws JSONException {
        List<SubsidyDetail> subsidyDetailList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            SubsidyDetail subsidyDetail = SubsidyDetail.builder()
                    .publicServantDocument(obj.optString("공무원확인구비서류", null))
                    .requiredDocuments(obj.optString("구비서류", null))
                    .contactInfo(obj.optString("문의처", null))
                    .legalBasis(obj.optString("법령", null))
                    .personalVerificationDocs(obj.optString("본인확인필요구비서류", null))
                    .serviceId(obj.optString("서비스ID", null))
                    .serviceName(obj.optString("서비스명", null))
                    .servicePurpose(obj.optString("서비스목적", null))
                    .eligibilityCriteria(obj.optString("선정기준", null))
                    .responsibleAgencyName(obj.optString("소관기관명", null))
                    .lastModifiedDate(obj.optString("수정일시", null))
                    .applicationPeriod(obj.optString("신청기한", null))
                    .applicationMethod(obj.optString("신청방법", null))
                    .onlineApplicationUrl(obj.optString("온라인신청사이트URL", null))
                    .localRegulation(obj.optString("자치법규", null))
                    .receivingAgencyName(obj.optString("접수기관명", null))
                    .supportDetails(obj.optString("지원내용", null))
                    .eligibleRecipients(obj.optString("지원대상", null))
                    .supportType(obj.optString("지원유형", null))
                    .administrativeRules(obj.optString("행정규칙", null))
                    .build();
            subsidyDetailList.add(subsidyDetail);
        }

        return subsidyDetailList;
    }
}

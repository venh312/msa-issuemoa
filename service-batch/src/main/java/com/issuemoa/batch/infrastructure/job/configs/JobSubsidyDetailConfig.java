package com.issuemoa.batch.infrastructure.job.configs;

import com.issuemoa.batch.infrastructure.job.tasklets.TaskletSubsidyDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@RequiredArgsConstructor
@Configuration
public class JobSubsidyDetailConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskletSubsidyDetail taskletSubsidyDetail;

    @Bean
    public Job JobSubsidyDetail(Step stepSubsidyDetail) {
        return jobBuilderFactory.get("JobSubsidyDetail")
                .incrementer(new RunIdIncrementer())
                .start(stepSubsidyDetail)
                .build();
    }

    @Bean
    public Step stepSubsidyDetail() {
        DefaultTransactionAttribute transactionAttribute = new DefaultTransactionAttribute();
        transactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
        return stepBuilderFactory.get("stepSubsidyDetail").tasklet(taskletSubsidyDetail).transactionAttribute(transactionAttribute).build();
    }
}

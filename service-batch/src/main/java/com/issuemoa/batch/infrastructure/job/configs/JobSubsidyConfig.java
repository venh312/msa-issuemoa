package com.issuemoa.batch.infrastructure.job.configs;

import com.issuemoa.batch.domain.subsidy.Subsidy;
import com.issuemoa.batch.infrastructure.job.tasklets.TaskletSubsidy;
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
public class JobSubsidyConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskletSubsidy taskletSubsidy;

    @Bean
    public Job JobSubsidy(Step stepSubsidy) {
        return jobBuilderFactory.get("JobSubsidy")
                .incrementer(new RunIdIncrementer())
                .start(stepSubsidy)
                .build();
    }
    @Bean
    public Step stepSubsidy() {
        DefaultTransactionAttribute transactionAttribute = new DefaultTransactionAttribute();
        transactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
        return stepBuilderFactory.get("taskletSubsidy").tasklet(taskletSubsidy).transactionAttribute(transactionAttribute).build();
    }
}

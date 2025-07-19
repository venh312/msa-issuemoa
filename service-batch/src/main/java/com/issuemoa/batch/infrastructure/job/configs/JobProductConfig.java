package com.issuemoa.batch.infrastructure.job.configs;

import com.issuemoa.batch.infrastructure.job.tasklets.TaskletStoreProduct;
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
public class JobProductConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskletStoreProduct taskletStoreProduct;

    @Bean
    public Job jobProduct(Step stepProduct) {
        return jobBuilderFactory.get("jobProduct")
                .incrementer(new RunIdIncrementer())
                .start(stepProduct)
                .build();
    }

    @Bean
    public Step stepProduct() {
        DefaultTransactionAttribute transactionAttribute = new DefaultTransactionAttribute();
        transactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
        return stepBuilderFactory.get("taskletStoreProduct").tasklet(taskletStoreProduct).transactionAttribute(transactionAttribute).build();
    }
}

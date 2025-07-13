package com.issuemoa.batch.infrastructure.job.configs;

import com.issuemoa.batch.infrastructure.job.tasklets.TaskletStores;
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
public class JobStoreConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskletStores taskletStores;

    @Bean
    public Job jobStore(Step stepStore) {
        return jobBuilderFactory.get("jobStore")
                .incrementer(new RunIdIncrementer())
                .start(stepStore)
                .build();
    }

    @Bean
    public Step stepStore() {
        DefaultTransactionAttribute transactionAttribute = new DefaultTransactionAttribute();
        transactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
        return stepBuilderFactory.get("taskletStores").tasklet(taskletStores).transactionAttribute(transactionAttribute).build();
    }
}

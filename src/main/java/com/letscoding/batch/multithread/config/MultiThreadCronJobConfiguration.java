package com.letscoding.batch.multithread.config;

import com.letscoding.batch.multithread.dbmodel.postgress.Employee;
import com.letscoding.batch.multithread.job.ChunkOrientedCrmReader;
import com.letscoding.batch.multithread.job.ChunkOrientedCrmWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class MultiThreadCronJobConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private  final ChunkOrientedCrmReader chunkOrientedCrmReader;
    private final ChunkOrientedCrmWriter chunkOrientedCrmWriter;


    private final JobBuilderFactory jobs;


    public Step jobStep(){
        return stepBuilderFactory.get("chunkOrientedCDbcpXADataSourceWrapperrmCronJobStep")
                .<Employee,Employee>chunk(100)
                .reader(chunkOrientedCrmReader.getEmpList(LocalDate.now()))
                .writer(chunkOrientedCrmWriter)
                .taskExecutor(new SimpleAsyncTaskExecutor("chunkOrientedCrmCronJobStep"))
                .throttleLimit(10)
                .build();
    }

    @Bean
    public Job chunkOrientedCrmCronJob(){
        return jobs
                .get("taskletsJob")
                .start(jobStep()).build();
    }

}

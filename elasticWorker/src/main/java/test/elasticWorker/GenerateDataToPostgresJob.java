package test.elasticWorker;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;

public class GenerateDataToPostgresJob {

    @Bean
    public Job generateDataToPostgresJob(JobRepository jobRepository) {
        return new JobBuilder("generateDataToPostgresJob", jobRepository)
                .start(new Step() {
                    @Override
                    public String getName() {
                        return "test";
                    }

                    @Override
                    public void execute(StepExecution stepExecution) throws JobInterruptedException {

                    }
                })
                .build();
    }

}

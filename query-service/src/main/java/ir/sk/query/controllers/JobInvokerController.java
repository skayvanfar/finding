package ir.sk.query.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/28/2021.
 */
@RestController
public class JobInvokerController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;

    @RequestMapping("/invokejob")
    public String handle() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(processJob, jobParameters);

        return "Batch job has been invoked";
    }

    @ExceptionHandler({ Exception.class })
    public void handleException(HttpClientErrorException e) {
        // TODO: 1/30/2021 for future
    }
}

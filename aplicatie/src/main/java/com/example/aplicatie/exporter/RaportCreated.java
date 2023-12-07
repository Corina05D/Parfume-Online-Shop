package com.example.aplicatie.exporter;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class RaportCreated implements Job {
    public RaportCreated() {}


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Metoda este scheduled!");
    }

}

package com.market.research.ticket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectProcess {

    private Long projectProcessId;
    private String projectName;
    private String firstLevel;
    private String secLevel;
    private String business;
    private String startTime;
    private String progress;
    private String financingShare;
    private String financier;
    private String businessUnit;
    private String lp;

}


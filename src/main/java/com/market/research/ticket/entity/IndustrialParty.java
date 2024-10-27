package com.market.research.ticket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndustrialParty {

    private Long industrialPartyId;
    private String ticketName;
    private String firstLevel;
    private String thirdLevel;
    private String direction;
    private String production;
    private String dockingPerson;
    private String position;
    private String marketTime;

}

package org.graduatetrackingsystem.graduatetracking.reponses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class NotificationResponse {

    private Long id;
    private String header;
    private String body;
    private boolean active;
}

package com.goat.dto;

import lombok.Data;

@Data
public class ChooseOptionRequest {
    private Long careerId;
    private Long eventId;
    private Long optionId;
}

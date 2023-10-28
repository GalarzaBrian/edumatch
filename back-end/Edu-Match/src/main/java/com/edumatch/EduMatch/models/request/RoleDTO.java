package com.edumatch.EduMatch.models.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDTO {

    private String name;

    private String description;

}
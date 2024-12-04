package com.artemis.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-12-04
 * @Description:
 */
@Data
public class Result<T> implements Serializable {
    @JsonProperty("code")
    private String code;

    @JsonProperty("msg")
    private String message;

    @JsonProperty("success")
    private boolean success = false;

    @JsonProperty("data")
    private T data;
}

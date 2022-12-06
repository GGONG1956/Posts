package com.sparta.posts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto { // ResponssDto 최상위 클래스
    private String msg;
    private int statusCode;
    public ResponseDto(String msg, int statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }

}

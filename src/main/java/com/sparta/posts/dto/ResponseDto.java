package com.sparta.posts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto { // ResponssDto 최상위 클래스
    private String msg;

    public ResponseDto(String msg){
        this.msg = msg;
    }

}

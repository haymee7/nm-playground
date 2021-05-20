package kr.co.zzimcar.dto.blog;

import lombok.Data;


import java.util.List;

@Data
public class BlogDataDto {
    private int totalCnt;
    private List<BlogDto> list;

    public BlogDataDto(List<BlogDto> blogDto, int totalCnt) {
        this.list = blogDto;
        this.totalCnt = totalCnt;


    }

}

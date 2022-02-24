package com.portfolio.lagarto.customer.files;

import lombok.Data;

@Data
public class AttachDTO {

    /** 파일 번호 (PK) */
    private int idx;

    /** 게시글 번호 (FK) */
    private int iboard;

    /** 원본 파일명 */
    private String original_name;

    /** 저장 파일명 */
    private String save_name;

    /** 파일 크기 */
    private int size;

    private String insertTime;
}

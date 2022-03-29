package com.example.demo.entity;
import java.util.Date;

import lombok.Data;
/**
 * メッセージ情報 Entity
 */
@Data
public class Message {
    /**
     * ID
     */
    private Long id;
    /**
     * 内容
     */
    private String content;
    /**
     * タイトル
     */
    private String title;
    /**
     * 更新日時
     */
    private Date updatedAt;
    /**
     * 登録日時
     */
    private Date createdAt;

}
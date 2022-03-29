package com.example.demo.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageForm {

    private Long id;

    @NotEmpty (message = "タイトルを入力してください。")
    @Size(min = 1, max = 20, message = "20文字以内で入力してください。")
    private String title;

    @NotEmpty (message = "内容を入力してください。")
    private String content;

    /** 「登録」 or「 変更」 判定用 */
    private Boolean newMessage;



//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")

}
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Message;

public interface MessageService {


    //全データ取得
    List<Message> findAll();

    //1件のデータ取得
    Optional<Message> getMessage(int id);

    //データの登録
    void insert(Message message);

    //データの更新
    void update(Message message);

    //データの削除
    void delete(int id);

}

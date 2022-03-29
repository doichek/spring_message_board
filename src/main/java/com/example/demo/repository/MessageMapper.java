package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Message;
/**
 * メッセージ情報 Mapper
 */
@Mapper
public interface MessageMapper {
    /**
     * メッセージ情報全所得
     * @param
     * @return メッセージ情報
     */
    List<Message> findAll();

    /**
     * メッセージ情報全所得
     * @param
     * @return メッセージ情報
     */
    Optional<Message> getMessage(int id);

    /**
     * メッセージ情報の新規登録
     * @param
     */
    void insert(Message message);

    /**
     * メッセージ情報の更新
     * @param
     */
    void update(Message message);

    /**
     * メッセージ情報の削除
     * @param
     */
    void delete(int id);
}
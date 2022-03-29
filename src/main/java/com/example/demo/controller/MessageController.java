package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;

/**
 * メッセージ情報 Controller
 */
@Controller
@RequestMapping("/message")
public class MessageController {
  /**
   * メッセージ情報 Service
   */
  @Autowired
  MessageService messageService;

  /**
   * メッセージ情報一覧画面を表示
   * @param model Model
   * @return メッセージ情報一覧画面
   */
  @GetMapping(value = "/index")
  public String index(Model model) {
      //Messageのリストを取得する
      List<Message>list = messageService.findAll();

      model.addAttribute("list", list);

    return "message/index";
  }

  /**
   * メッセージ情報の詳細画面を表示
   * @param model Model
   * @param messageForm
   * @param id
   * @return メッセージ情報詳細画面
   */
  @GetMapping(value = "/show/{id}")
  public String show(MessageForm messageForm, @PathVariable int id, Model model) {

      //Messageを取得(Optionalでラップ)
      Optional<Message>messageOpt = messageService.getMessage(id);
      //MessageFormへの詰め直し
      Optional<MessageForm>messageFormOpt = messageOpt.map(t -> makeMessageForm(t));

      //MessageFormがnullでなければ中身を取り出し
      if(messageFormOpt.isPresent()) {
          messageForm = messageFormOpt.get();
      }

      model.addAttribute("messageForm", messageForm);

    return "message/show";
  }


  /**
   * メッセージ情報の編集画面を表示
   * @param model Model
   * @param messageForm
   * @param id
   * @return メッセージ情報編集画面
   */
  @GetMapping(value = "/edit/{id}")
  public String edit(MessageForm messageForm, @PathVariable int id, Model model) {

      //Messageを取得(Optionalでラップ)
      Optional<Message>messageOpt = messageService.getMessage(id);
      //MessageFormへの詰め直し
      Optional<MessageForm>messageFormOpt = messageOpt.map(t -> makeMessageForm(t));

      //MessageFormがnullでなければ中身を取り出し
      if(messageFormOpt.isPresent()) {
          messageForm = messageFormOpt.get();
      }


      messageForm. setNewMessage(false);

      model.addAttribute("title", "メッセージ情報の編集");
      model.addAttribute("messageForm", messageForm);

    return "message/form";
  }


  /**
   * メッセージの新規登録画面を表示します
   * @param taskForm
   * @param model
   * @return
   */
  @GetMapping(value="/newEntry")
  public String newEntry(MessageForm messageForm, Model model) {


      messageForm. setNewMessage(true);

      model.addAttribute("title", "メッセージ情報の登録");
      model.addAttribute("messageForm", messageForm);

      return "message/form";
  }


  /**
   * メッセージデータを一件挿入
   * @param messageForm
   * @param result
   * @param model
   * @return
   */
  @PostMapping("/insert")
  public String insert(@Valid @ModelAttribute MessageForm messageForm,BindingResult result,Model model,RedirectAttributes redirectAttributes) {

      if (!result.hasErrors()) {

         Message message = makeMessage(messageForm);

         Timestamp currentTime = new Timestamp(System.currentTimeMillis());
         message.setCreatedAt(currentTime);
         message.setUpdatedAt(currentTime);

          //一件挿入後リダイレクト
          messageService.insert(message);
          redirectAttributes.addFlashAttribute("complete", "登録が完了しました");

          return "redirect:/message/index";

      } else {

          model.addAttribute("messageForm", messageForm);

          return "message/new";
      }

  }


  /**
   * メッセージデータの更新
   * @param messageForm
   * @param result
   * @param model
   * @return
   */
  @PostMapping("/update")
  public String update(@Valid @ModelAttribute MessageForm messageForm,BindingResult result,Model model,RedirectAttributes redirectAttributes) {

      if (!result.hasErrors()) {

         Message message = makeMessage(messageForm);

         Timestamp currentTime = new Timestamp(System.currentTimeMillis());
         message.setUpdatedAt(currentTime);


          //一件更新後リダイレクト
          messageService.update(message);
          redirectAttributes.addFlashAttribute("complete", "更新が完了しました");

          return "redirect:/message/index";

      } else {

          model.addAttribute("messageForm", messageForm);

          return "message/edit";
      }

  }


  /**
   * タスクidを取得し、一件のデータ削除
   * @param id
   * @param model
   * @return
   */
  @PostMapping("/delete")
  public String delete(@RequestParam("id") int id, Model model,RedirectAttributes redirectAttributes) {

      //タスクを一件削除しリダイレクト
      messageService.delete(id);
      redirectAttributes.addFlashAttribute("complete", "削除が完了しました");

      return "redirect:/message/index";
  }


  /**
   * messageFormのデータをmessageに入れて返す
   * @param messageForm
   * @return
   */
  private Message makeMessage(MessageForm messageForm) {
      Message message = new Message();

      message.setId(messageForm.getId());
      message.setTitle(messageForm.getTitle());
      message.setContent(messageForm.getContent());


      return message;
  }





  /**
   * messageのデータをmessageFormに入れて返す
   * @param message
   * @return
   */
  private MessageForm makeMessageForm(Message message) {

      MessageForm messageForm = new MessageForm();

      messageForm.setId(message.getId());
      messageForm.setTitle(message.getTitle());
      messageForm.setContent(message.getContent());


      return messageForm;
  }


}
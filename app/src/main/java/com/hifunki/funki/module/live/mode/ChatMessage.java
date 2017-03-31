package com.hifunki.funki.module.live.mode;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.mode.ChatMessage.java
 * @link
 * @since 2017-03-31 14:33:33
 */
public class ChatMessage {
    public enum TYPE{
        text,
        person_in,
    }

    public ChatMessage(String name , String message ,TYPE type){
        this.name = name;
        this.message = message;
        this.type = type;
    }

    public TYPE type;

    public String name;

    public String message;


    public TYPE getType(){
        return type;
    }
}

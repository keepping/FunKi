package com.hifunki.funki.net.back;

import java.util.List;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.net.back.Post.java
 * @link
 * @since 2017-03-16 10:07:07
 */
public class Post extends GJBack {

    public Post(int type,List<String> uri,String liveUri){
        this.type = type;
        this.imgageUri = uri;
        this.liveUri = liveUri;
    }

    public int type;

    public List<String> imgageUri;

    public String liveUri;


}

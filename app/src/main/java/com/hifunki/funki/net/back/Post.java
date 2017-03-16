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

    public Post(int type,List<String> uri){
        this.type = type;
        this.imgageUri = uri;
    }

    public int type;

    public List<String> imgageUri;


}

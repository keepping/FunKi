package com.hifunki.funki.module.live.audience.viewholder;

import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.module.live.audience.mode.ChatMessage;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.audience.viewholder.ChatText.java
 * @link
 * @since 2017-03-31 14:32:32
 */
public class ChatText extends PowViewHolder<ChatMessage> {

    @BindView(R.id.host_chat_message)
    TextView host_chat_message;

    public ChatText(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this, mItemView);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.item_chat_text;
    }

    @Override
    protected boolean acceptData(ChatMessage data) {
        return data != null && data.getType() == ChatMessage.TYPE.text;
    }

    @Override
    public void loadData(AdapterDelegate<? super ChatMessage> multipleAdapter, ChatMessage data, int postion) {
        StringBuffer buffer = new StringBuffer();
        String name = data.name == null ? "" : data.name;
        String message = data.message == null ? "" : data.message;
        buffer.append(name);
        buffer.append(":  ");
        buffer.append(message);

        SpannableStringBuilder style = new SpannableStringBuilder(buffer);
        style.setSpan(new ForegroundColorSpan(0xffb9b9b9), 0, name.length() + 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(0xffffffff), name.length() + 3, buffer.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        host_chat_message.setText(style);


    }


}

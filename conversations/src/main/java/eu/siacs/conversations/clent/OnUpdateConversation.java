package eu.siacs.conversations.clent;

import eu.siacs.conversations.entities.Conversation;
import eu.siacs.conversations.entities.Message;

/**
 * Created by powyin on 2017/4/25.
 */

public interface OnUpdateConversation {
    void onFetch(Conversation conversation,  Message... messages);
}

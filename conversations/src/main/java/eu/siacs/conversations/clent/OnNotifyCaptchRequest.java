package eu.siacs.conversations.clent;

import android.graphics.Bitmap;

import eu.siacs.conversations.entities.Account;
import eu.siacs.conversations.xmpp.forms.Data;

/**
 * Created by powyin on 2017/4/26.
 */

public interface OnNotifyCaptchRequest {

    boolean onCaptchaRequested(Account account, String id, Data data, Bitmap bitmap);

}

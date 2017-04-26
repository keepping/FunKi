package eu.siacs.conversations.clent;

import eu.siacs.conversations.entities.Account;

/**
 * Created by powyin on 2017/4/25.
 */

// 创建用户成功操作
public interface OnUpdateAccount {

    public void onCreate(Account account);

}

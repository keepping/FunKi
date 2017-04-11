package com.hifunki.funki.module.live.danmu.bDanMu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;

import com.hifunki.funki.util.TextUtil;

import java.lang.ref.WeakReference;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.AndroidDisplayer;
import master.flame.danmaku.danmaku.model.android.SimpleTextCacheStuffer;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.danmu.bDanMu.FunKiStuffer.java
 * @link
 * @since 2017-04-11 15:10:10
 */
public class FunKiStuffer extends SimpleTextCacheStuffer {


    @Override
    protected void drawText(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, TextPaint paint, boolean fromWorkerThread) {
        left = 0;
        top = 0;

        DanMuData danMuData = (DanMuData)danmaku.tag;

        WeakReference weakReference = danMuData.chachBitmap;
        Bitmap bitmap = weakReference==null ? null : (Bitmap)weakReference.get();
        if(bitmap!=null){
            Rect rect = new Rect((int)left+2 , (int)(top+2 ), (int)(left -2 +canvas.getHeight()), (int)(top + canvas.getHeight() -2));
            canvas.drawBitmap(bitmap,null,rect,paint);
        }

        if(!TextUtils.isEmpty(danMuData.userName)){
            paint.setColor(Color.YELLOW);
            canvas.drawText(danMuData.userName,left +5 + canvas.getHeight(),top + canvas.getHeight()/2 - danmaku.paintHeight*0.07f,paint);
        }

        if(!TextUtils.isEmpty(danMuData.message)){
            paint.setColor(Color.WHITE);
            canvas.drawText(danMuData.message,left +5 + canvas.getHeight() ,top + canvas.getHeight() - danmaku.paintHeight*0.07f ,paint);
        }

    }

    @Override
    public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
        // todo  paintWidth    paintHeight
        DanMuData danMuData = (DanMuData)danmaku.tag;
        float nameWid = !TextUtils.isEmpty(danMuData.userName) ?  paint.measureText(danMuData.userName) : 0;
        float messWid = !TextUtils.isEmpty(danMuData.message) ?  paint.measureText(danMuData.message) : 0;
        float wid = Math.max(nameWid,messWid);
        float hei =  getCacheHeight(danmaku,paint);

        danmaku.paintWidth = wid;
        danmaku.paintHeight = hei*2;

    }


}

package com.ellen.modlocal;

import android.content.Context;

import com.ellen.libcommon.util.ContentProviderUtils;
import com.ellen.library.library.runmode.RunMode;
import com.ellen.library.library.serial.Receiver;
import com.ellen.library.library.serial.Sender;
import com.ellen.library.library.serial.commoninterface.sender.SenderController;

import java.util.List;

public class LocalPresenter extends LocalAgreement.ALocalPresenter {

    @Override
    void getLocalMusics(final Context context) {
         new Sender<List<ContentProviderUtils.Music>>(){
             @Override
             protected void handlerInstruction(SenderController<List<ContentProviderUtils.Music>> senderController) {
                 List<ContentProviderUtils.Music> musicList = mModel.getLocalMusics(context);
                 senderController.sendMessageToNext(musicList);
             }
         }.runOn(RunMode.REUSABLE_THREAD)
                 .setReceiver(new Receiver<List<ContentProviderUtils.Music>>() {

                     @Override
                     protected void handleMessage(List<ContentProviderUtils.Music> message) {
                         mView.getLocalMusicSuccess(message);
                     }

                     @Override
                     protected void handleErrMessage(Throwable throwable) {
                         mView.getLocalMusicFailure(throwable.getMessage());
                     }

                     @Override
                     protected void complete() {

                     }
                 }).runOn(RunMode.MAIN_THREAD).start();
    }
}

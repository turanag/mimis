package pm.application.iTunes;

import pm.application.Application;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;

public class iTunesApplicatie extends Application{
    
    Dispatch iTunesController;
    
    public iTunesApplicatie() {
        connect();
    }
    
    protected void connect() {
        try {
            ComThread.InitMTA(true);
            ActiveXComponent iTunesCom = new ActiveXComponent("iTunes.Application");
            iTunesController = (Dispatch)iTunesCom.getObject();
        } catch (Exception e) { } // Ignore JacobVersion exception
    }

    public void play() {
        System.out.println("play");
        invoke("play");
    }
    
    public void pause() {
        invoke("pause");
    }
    
    public void resume() {
        invoke("resume");
    }
    
    public void exit() {
        ComThread.Release();
    }
    
    protected void invoke(String invocation) {
        Dispatch.call(iTunesController, invocation);
    }

    public static void main(String argv[]) throws Exception {
        new iTunesApplicatie();
    }

}
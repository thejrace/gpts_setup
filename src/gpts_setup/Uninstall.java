/* Gitaş - Obarey Inc 2018 */
package gpts_setup;

import java.io.File;

public class Uninstall extends ActionCommon {

    public void action( String staticDirPrefixVal, ActionCallback cb ){
        final String staticDirPrefix = staticDirPrefixVal + "/gpts/";

        Thread uninstallThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if( Common.deleteDirectory( new File(staticDirPrefix)) ){
                    setStatusProp("Program kaldırıldı!");
                    cb.success("");
                } else {
                    setStatusProp("Program kaldırılken bir hata oluştu!");
                    cb.error("");
                }
            }
        });
        uninstallThread.setDaemon(true);
        uninstallThread.start();

    }

}

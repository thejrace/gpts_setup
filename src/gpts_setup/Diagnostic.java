/* Gitaş - Obarey Inc 2018 */
package gpts_setup;

public class Diagnostic extends ActionCommon{

    public void action( String staticDirPrefixVal, ActionCallback cb ){
        final String staticDirPrefix = staticDirPrefixVal + "/gpts/";
        Thread diagThread = new Thread(new Runnable() {
            @Override
            public void run() {
                setStatusProp("Program klasörleri oluşturuluyor.");
                if( !Common.checkDirectory( staticDirPrefix ) ){
                    setStatusProp("Static dir yok!");
                    cb.error("");
                    return;
                }
                if( !Common.checkFile( staticDirPrefix + "api_user.json"  ) ){
                    setStatusProp("api_user.json dosyası yok!");
                    cb.error("");
                    return;
                }
                if( !Common.checkFile( staticDirPrefix + "employee_groups.json"  ) ){
                    setStatusProp("employee_groups.json dosyası yok!.");
                    cb.error("");
                    return;
                }
                if( !Common.checkFile( staticDirPrefix + "permissions_template.json"  ) ){
                    setStatusProp("permissions_template.json dosyası yok!.");
                    cb.error("");
                    return;
                }
                if( !Common.checkFile( staticDirPrefix + "gpts_config.json"  ) ){
                    setStatusProp("gpts_config.json dosyası yok!.");
                    cb.error("");
                    return;
                }
                if( !Common.checkFile( staticDirPrefix + "GPTS.exe"  ) ){
                    setStatusProp("GPTS.exe dosyası yok!.");
                    cb.error("");
                    return;
                }
                if( !Common.checkFile( staticDirPrefix + "gpts_update_helper.jar"  ) ){
                    setStatusProp("gpts_update_helper.jar dosyası yok!.");
                    cb.error("");
                    return;
                }
                setStatusProp("Başarılı. Eksik dosya yok.");
                cb.success("");
            }
        });
        diagThread.setDaemon(true);
        diagThread.start();
    }
}
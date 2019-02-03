/* Gitaş - Obarey Inc 2018 */
package gpts_setup;

import org.json.JSONObject;

public class Setup extends ActionCommon{

    //private String mDebugFilePrefix = ""; // release
    private String mDebugFilePrefix = "C://Users/Jeppe-PC/IdeaProjects/gpts_setup/out/artifacts/gpts_setup_jar/"; // debug

    public void action( String staticDirPrefix, ActionCallback cb ){

        if( !Common.checkFile(mDebugFilePrefix + "gpts_setup_config.json") ){
            setStatusProp("Yapılandırma dosyası eksik!");
            return;
        }
        JSONObject config = new JSONObject( Common.readJSONData( mDebugFilePrefix + "gpts_setup_config.json" ) );

        System.out.println(config.getString("web_service_url"));
        System.out.println(config.getString("desktop_app_url"));
        System.out.println(config.getString("update_helper_url"));

        Common.STATIC_LOCATION = staticDirPrefix + Common.STATIC_LOCATION_SUFFIX;
        Thread downloadHelpers = new Thread(new Runnable() {
            @Override
            public void run() {
                setStatusProp("Yapılandırma dosyaları indiriliyor..");
                FileDownload.downloadFileFromUrl(config.getString("web_service_url") + config.getString("update_helper_url"), Common.STATIC_LOCATION + "gpts_update_helper.jar", new ActionCallback() {
                    @Override
                    public void success(String msg) {
                        setStatusProp("Kurulum başarılı!");
                        cb.success("");
                    }
                    @Override
                    public void error(String msg) {
                        setStatusProp("Yapılandırma dosyaları indirilken bir hata oluştu.");
                        cb.error( "" );
                    }
                });
            }
        });
        downloadHelpers.setDaemon(true);

        Thread downloadLastVersion = new Thread(new Runnable() {
            @Override
            public void run() {
                setStatusProp("Son versiyon indiriliyor.");
                FileDownload.downloadFileFromUrl(config.getString("web_service_url") + config.getString("desktop_app_url"), Common.STATIC_LOCATION + "GPTS.exe", new ActionCallback() {
                    @Override
                    public void success(String msg) {
                        setStatusProp("Yeni versiyon indirildi!");
                    }
                    @Override
                    public void error(String msg) {
                        setStatusProp("Yeni version indirilken bir hata oluştu.");
                        cb.error( "" );
                        return;
                    }
                });
                downloadHelpers.start();
            }
        });
        downloadLastVersion.setDaemon(true);

        Thread staticFileGenerationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                setStatusProp("Program klasörleri oluşturuluyor.");
                if( !Common.checkDirectory( Common.STATIC_LOCATION ) ){
                    if( !Common.createStaticDirectory() ){
                        setStatusProp("Static dir oluşturulamadı.");
                        cb.error("");
                        return;
                    }
                }
                setStatusProp("Program dosyaları oluşturuluyor.");
                //mStatusProp.setValue("Program dosyaları oluşturuluyor.");
                if( !Common.checkFile( Common.STATIC_LOCATION + "api_user.json"  ) ){
                    if( !Common.createFile(  "api_user", "{ \"init\" : true }" ) ){
                        setStatusProp("api_user.json oluşturulamadı.");
                        cb.error("");
                        return;
                    }
                }
                if( !Common.checkFile( Common.STATIC_LOCATION + "employee_groups.json"  ) ){
                    if( !Common.createFile(  "employee_groups", "[]" ) ){
                        setStatusProp("employee_groups.json oluşturulamadı.");
                        cb.error("");
                        return;
                    }
                }
                if( !Common.checkFile( Common.STATIC_LOCATION + "permissions_template.json"  ) ){
                    if( !Common.createFile(  "permissions_template", "[]" ) ){
                        setStatusProp("permissions_template.json oluşturulamadı.");
                        cb.error("");
                        return;
                    }
                }
                if( !Common.checkFile( Common.STATIC_LOCATION + "gpts_config.json"  ) ){
                    if( !Common.createFile(  "gpts_config", "{ \"static_dir\" : \""+Common.STATIC_LOCATION+"\" }" ) ){
                        setStatusProp("gpts_config.json oluşturulamadı.");
                        cb.error("");
                        return;
                    }
                }
                downloadLastVersion.start();
            }
        });
        staticFileGenerationThread.setDaemon(true);
        staticFileGenerationThread.start();
    }

}

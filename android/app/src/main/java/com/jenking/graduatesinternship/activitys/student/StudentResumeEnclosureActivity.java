package com.jenking.graduatesinternship.activitys.student;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.activitys.common.UserInfoAvatarActivity;
import com.jenking.graduatesinternship.api.BaseAPI;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.EducationExpModel;
import com.jenking.graduatesinternship.models.impl.ResumeEnclosureModel;
import com.jenking.graduatesinternship.presenter.ResumeEnclosurePresenter;
import com.jenking.graduatesinternship.ui.CommonLoading;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentResumeEnclosureActivity extends BaseActivity {

    private ResumeEnclosurePresenter presenter;
    private static final int PERMISSION_STORAGE_CODE = 10001;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String path;
    private String fileUrl;

    private List<ResumeEnclosureModel> datas;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.loading)
    CommonLoading loading;

    @OnClick(R.id.upload)
    void upload(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, PERMISSION_STORAGE_CODE);
                return;
            }
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 1);
    }

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resume_enclosure);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, PERMISSION_STORAGE_CODE);
            }
        }
//        downloadFile("upload/test.doc");
    }

    @Override
    public void initData() {
        super.initData();

        presenter = new ResumeEnclosurePresenter(this);
        presenter.setOnCallBack(new ResumeEnclosurePresenter.OnCallBack() {
            @Override
            public void getMineResumeEnclosureMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<ResumeEnclosureModel> educationExpModels = resultModel.getData();
                        datas = educationExpModels!=null?educationExpModels:datas;
                        if (datas!=null&&datas.size()>=1){
                            String filePath = datas.get(0).getFile_url();
                            downloadFile(filePath);
                        }
                    }
                }else{
                    setLoadingEnable(false);
                    Toast.makeText(StudentResumeEnclosureActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                checkData();
            }

            @Override
            public void addResumeEnclosureMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        downloadFile(fileUrl);
//                        Toast.makeText(StudentResumeEnclosureActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
//                        finish();
                    }
                }else{
                    Toast.makeText(StudentResumeEnclosureActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void modifyResumeEnclosureMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteResumeEnclosureMobile(boolean isSuccess, Object object) {

            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);

            }
        });

        // webview必须设置支持Javascript才可打开
        webView.getSettings().setJavaScriptEnabled(true);

        // 设置此属性,可任意比例缩放
        webView.getSettings().setUseWideViewPort(true);

        //获取我的上传简历数据
        getMineResumeEnclosure();
    }

    void getMineResumeEnclosure(){
        Map<String,String> params = RS.getBaseParams(this);
        params.put("user_id", AccountTool.getLoginUser(this).getId());
        if (presenter!=null){
            presenter.getMineResumeEnclosureMobile(params);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                path = uri.getPath();
                Log.e("path",path+"");
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                path = getPath(this, uri);
                Log.e("path",path+"");
            } else {//4.4以下系统调用方法
                path = getRealPathFromURI(uri);
                Log.e("path",path+"");
            }

            setLoadingEnable(true);
            uploadFile(path);
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(null!=cursor&&cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    /**
     * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
     */
    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    //文件上传
    void uploadFile(String path){
        setLoadingEnable(true);
        /**
         * 自定义实体参数类请参考:
         * 请求注解 {@link org.xutils.http.annotation.HttpRequest}
         * 请求注解处理模板接口 {@link org.xutils.http.app.ParamsBuilder}
         *
         * 需要自定义类型作为callback的泛型时, 参考:
         * 响应注解 {@link org.xutils.http.annotation.HttpResponse}
         * 响应注解处理模板接口 {@link org.xutils.http.app.ResponseParser}
         *
         * 示例: 查看 org.xutils.sample.http 包里的代码
         */
        RequestParams params = new RequestParams(BaseAPI.base_url+"resume_enclosure/uploadFileMobile");
        //        params.setSslSocketFactory(...); // 设置ssl
        //        params.addQueryStringParameter("wd", "xUtils");
        params.setMultipart(true);
        params.addBodyParameter("uploadFile", new File(path));
        //        BaiduParams params = new BaiduParams();
        //        params.wd = "xUtils";
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        // params.setMultipart(true);
        // 上传文件方式 1
        // params.uploadFile = new File("/sdcard/test.txt");
        // 上传文件方式 2
        // params.addBodyParameter("uploadFile", new File("/sdcard/test.txt"));
        Callback.Cancelable cancelable
                = x.http().post(params,
                /**
                 * 1. callback的泛型:
                 * callback参数默认支持的泛型类型参见{@link org.xutils.http.loader.LoaderFactory},
                 * 例如: 指定泛型为File则可实现文件下载, 使用params.setSaveFilePath(path)指定文件保存的全路径.
                 * 默认支持断点续传(采用了文件锁和尾端校验续传文件的一致性).
                 * 其他常用类型可以自己在LoaderFactory中注册,
                 * 也可以使用{@link org.xutils.http.annotation.HttpResponse}
                 * 将注解HttpResponse加到自定义返回值类型上, 实现自定义ResponseParser接口来统一转换.
                 * 如果返回值是json形式, 那么利用第三方的json工具将十分容易定义自己的ResponseParser.
                 * 如示例代码{@link org.xutils.sample.http.BaiduResponse}, 可直接使用BaiduResponse作为
                 * callback的泛型.
                 *
                 * 2. callback的组合:
                 * 可以用基类或接口组合个种类的Callback, 见{@link Callback}.
                 * 例如:
                 * a. 组合使用CacheCallback将使请求检测缓存或将结果存入缓存(仅GET请求生效).
                 * b. 组合使用PrepareCallback的prepare方法将为callback提供一次后台执行耗时任务的机会,
                 * 然后将结果给onCache或onSuccess.
                 * c. 组合使用ProgressCallback将提供进度回调.
                 * ...(可参考{@link org.xutils.image.ImageLoader}
                 * 或 示例代码中的 {@link org.xutils.sample.download.DownloadCallback})
                 *
                 * 3. 请求过程拦截或记录日志: 参考 {@link org.xutils.http.app.RequestTracker}
                 *
                 * 4. 请求Header获取: 参考 {@link org.xutils.http.app.RequestInterceptListener}
                 *
                 * 5. 其他(线程池, 超时, 重定向, 重试, 代理等): 参考 {@link RequestParams}
                 *
                 **/
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("onSuccess",""+result);
                        fileUrl = result;
                        submitData();
                        setLoadingEnable(false);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        setLoadingEnable(false);
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        if (ex instanceof HttpException) { // 网络错误
                            HttpException httpEx = (HttpException) ex;
                            int responseCode = httpEx.getCode();
                            String responseMsg = httpEx.getMessage();
                            String errorResult = httpEx.getResult();
                            // ...
                        } else { // 其他错误
                            // ...
                        }
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {
                        setLoadingEnable(false);
                        Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinished() {
                        setLoadingEnable(false);
                    }
                });
    }

    //文件下载
    public void downloadFile(String url) {

        String filter[] = url.split("/");
        final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"/gi/"+filter[1];

        String downUrl = BaseAPI.base_url+url;
        Log.e("downUrl",downUrl);
        FileDownloader.setup(this);   //ac为activity的上下文对象
        FileDownloader.getImpl().create(downUrl)
                .setPath(filePath)
                .setForceReDownload(true)
                .setListener(new FileDownloadListener() {
                    //等待
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                        progressDialog.show();
                    }
                    //下载进度回调
                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        Log.e("soFarBytes",""+soFarBytes);
                        Log.e("totalBytes",""+totalBytes);
                        Log.e("progress",""+(soFarBytes * 100 / totalBytes));
                    }
                    //完成下载
                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Log.e("download",filePath);
                        showFileToWebView(filePath);
                        setLoadingEnable(false);
                    }
                    //暂停
                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        setLoadingEnable(false);
                    }
                    //下载出错
                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        Toast.makeText(StudentResumeEnclosureActivity.this, "下载出错,请检查网络连接后重试", Toast.LENGTH_SHORT).show();
                        setLoadingEnable(false);
                    }

                    //已存在相同下载
                    @Override
                    protected void warn(BaseDownloadTask task) {
                        showFileToWebView(filePath);
                        setLoadingEnable(false);
                    }
                }).start();
    }

    void submitData(){
        Map<String,String> params = RS.getBaseParams(this);
        params.put("user_id", AccountTool.getLoginUser(this).getId());
        params.put("file_url",fileUrl);
        params.put("status","current");
        if (presenter!=null){
            presenter.addResumeEnclosureMobile(params);
        }
    }

    //loading
    void setLoadingEnable(boolean enable){
        if (loading==null)return;
        if (enable){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }

    private void showFileToWebView(String path){
        Log.e("showFileToWebView",path);
        String name = path.substring(0, path.indexOf("."));
        try {
            if(!(new File(name).exists()))
                new File(name).mkdirs();
            Log.e("showFileToWebView---->",name);
            convert2Html(path,name+".html");
        } catch (Exception e){
            e.printStackTrace();
        }
        //WebView加载显示本地html文件
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        Log.e("webView",name+".html");
        webView.loadUrl("file://"+name+".html");
        empty_show.setVisibility(View.GONE);
    }

    /**
     * word文档转成html格式
     * */
    public void convert2Html(final String fileName, String outPutFile)
            throws TransformerException, IOException,
            ParserConfigurationException {
        Log.e("convert2Html",fileName);
        Log.e("convert2Html",outPutFile);
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

        //设置图片路径
        wordToHtmlConverter.setPicturesManager(new PicturesManager()
        {
            public String savePicture(byte[] content,
                                      PictureType pictureType, String suggestedName,
                                      float widthInches, float heightInches )
            {
                String name = fileName.substring(0,fileName.indexOf("."));
                return name+"/"+suggestedName;
            }
        } );

        //保存图片
        List<Picture> pics=wordDocument.getPicturesTable().getAllPictures();
        if(pics!=null){
            for(int i=0;i<pics.size();i++){
                Picture pic = (Picture)pics.get(i);
                System.out.println( pic.suggestFullFileName());
                try {
                    String name = fileName.substring(0,fileName.indexOf("."));
                    pic.writeImageContent(new FileOutputStream( name + "/"
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();
        //保存html文件

        Log.e("outPutFile",outPutFile);
        writeFile(new String(out.toByteArray()), outPutFile);
    }

    /**
     * 将html文件保存到sd卡
     * */
    public void writeFile(String content, String path) {
        Log.e("writeFile",content);
        Log.e("writeFile",path);
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
            bw.write(content);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
            }
        }
    }

    //检查数据
    void checkData(){
        if (datas==null||datas.size()<=0){
            empty_show.setVisibility(View.VISIBLE);
        }else{
            empty_show.setVisibility(View.GONE);
        }
    }

}

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.activitys.common.RecruitDetailActivity;
import com.jenking.graduatesinternship.api.BaseAPI;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.dialog.CommonBottomListDialog;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.RecruitDeliveryModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.presenter.RecruitDeliveryPresenter;
import com.jenking.graduatesinternship.ui.CommonLoading;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentRecruitDeliveryOperateActivity extends BaseActivity {
    private RecruitDeliveryPresenter recruitDeliveryPresenter;//岗位投递presenter
    private RecruitModel recruitModel;

    private String select_resume_id;
    private String select_resume_name;
    private String upload_enclosure_path;
    private String upload_enclosure_url;

    //用户选择：是重新上传简历
    private boolean isNeedReupload = true;

    private static final int PERMISSION_STORAGE_CODE = 10001;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @BindView(R.id.loading)
    CommonLoading loading;
    @BindView(R.id.resume_name)
    TextView resume_name;
    @BindView(R.id.enclosure_name)
    TextView enclosure_name;

    @BindView(R.id.submit)
    TextView submit;

    @OnClick({R.id.back})
    void back(){
        finish();
    }

    @OnClick({R.id.select_resume})
    void select_resume(){
        selectResume();
    }

    @OnClick({R.id.select_resume_enclosure})
    void select_resume_enclosure(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, PERMISSION_STORAGE_CODE);
                return;
            }
        }

        List<String> types = new ArrayList<>();
        types.add("重新上传");
        types.add("使用默认简历附件");
        CommonBottomListDialog commonBottomListDialog = new CommonBottomListDialog(this,"请选择上传附件方式",types,"",false) {
            @Override
            protected void setOnItemClickListener(String value) {
                switch (value){
                    case "重新上传":
                        isNeedReupload = true;
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 1);
                        break;
                    case "使用默认简历附件":
                        isNeedReupload = false;
                        break;
                }
            }
        };
        commonBottomListDialog.show();
    }

    @OnClick(R.id.submit)
    public void submit(){
        if (recruitModel!=null){
            CommonTipsDialog.create(this,"温馨提示","确定要申请该招聘吗？",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {
                            if (isNeedReupload){//判断是否需要重新上传简历
                                if (StringUtil.isNotEmpty(select_resume_id)
                                        &&StringUtil.isNotEmpty(select_resume_name)
                                        &&StringUtil.isNotEmpty(upload_enclosure_path)){
                                    uploadFile(upload_enclosure_path);
                                    setLoadingEnable(true);
                                }else{
                                    Toast.makeText(StudentRecruitDeliveryOperateActivity.this, "请选择简历和选择简历附件", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                if (StringUtil.isNotEmpty(select_resume_id)
                                        &&StringUtil.isNotEmpty(select_resume_name)){
                                    upload_enclosure_url = "";
                                    finalSubmitData();
                                    setLoadingEnable(true);
                                }else{
                                    Toast.makeText(StudentRecruitDeliveryOperateActivity.this, "请选择简历", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    }).show();
            if (isNeedReupload){

            }else{

            }


        }

    }

    //最终上传
    public void finalSubmitData(){
        Map<String,String> params = RS.getBaseParams(StudentRecruitDeliveryOperateActivity.this);
        params.put("user_id", AccountTool.getLoginUser(StudentRecruitDeliveryOperateActivity.this).getId());
        params.put("recruit_id",recruitModel.getId());
        params.put("resume_id",select_resume_id);
        params.put("enclosure",upload_enclosure_url);
        params.put("status", RecruitDeliveryModel.DELIVERY_STATUS_INIT);
        recruitDeliveryPresenter.addRecruitmentDeliveryMobile(params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recruit_delivery_operate);
    }

    @Override
    public void initData() {
        super.initData();

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            recruitModel = new Gson().fromJson(modelJson,RecruitModel.class);
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, PERMISSION_STORAGE_CODE);
            }
        }

        initRecruitDelivery();
    }

    //选择简历
    void selectResume(){
        Intent intent = new Intent(this,StudentResumeSelectActivity.class);
        startActivityForResult(intent,StudentResumeSelectActivity.selectResumeCode);
    }

    void initRecruitDelivery(){
        recruitDeliveryPresenter = new RecruitDeliveryPresenter(this);
        recruitDeliveryPresenter.setOnCallBack(new RecruitDeliveryPresenter.OnCallBack() {
            @Override
            public void getMineRecruitmentDeliveryMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void addRecruitmentDeliveryMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentRecruitDeliveryOperateActivity.this, "投递成功，请前往我的申请查看", Toast.LENGTH_SHORT).show();
                        submit.setEnabled(false);
                        submit.setText("已经投递过该职位");
                    }else{
                        CommonTipsDialog.showTip(StudentRecruitDeliveryOperateActivity.this,"温馨提示",resultModel.getMessage()+"",false);
                    }
                }else{
                    Toast.makeText(StudentRecruitDeliveryOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
                setLoadingEnable(false);
            }

            @Override
            public void modifyRecruitmentDeliveryMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteRecruitmentDeliveryMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void checkIsDelivery(boolean isSuccess, Object object) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case StudentResumeSelectActivity.selectResumeCode:
                select_resume_id = data.getStringExtra("resume_id");
                select_resume_name = data.getStringExtra("resume_name");
                resume_name.setText("已选择简历："+data.getStringExtra("resume_name"));
                break;
                case Activity.RESULT_OK:
                    Uri uri = data.getData();
                    if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                        upload_enclosure_path = uri.getPath();
                        Log.e("path",upload_enclosure_path+"");
                        return;
                    }
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                        upload_enclosure_path = getPath(this, uri);
                        Log.e("path",upload_enclosure_path+"");
                    } else {//4.4以下系统调用方法
                        upload_enclosure_path = getRealPathFromURI(uri);
                        Log.e("path",upload_enclosure_path+"");
                    }
                    enclosure_name.setText("已选择简历附件:"+upload_enclosure_path);
                    break;
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
                        upload_enclosure_url = result;
                        finalSubmitData();
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
                        Log.e("onFinished","onFinished");
                        setLoadingEnable(false);
                    }
                });
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


}

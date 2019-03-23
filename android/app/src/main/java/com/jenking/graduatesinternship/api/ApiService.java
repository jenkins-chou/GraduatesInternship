package com.jenking.graduatesinternship.api;

import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.EducationExpModel;
import com.jenking.graduatesinternship.models.impl.InternshipExperienceModel;
import com.jenking.graduatesinternship.models.impl.PersonalCertModel;
import com.jenking.graduatesinternship.models.impl.PersonalSkillModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.models.impl.UserModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhouzhenjian on 2018/3/26.
 */

public interface ApiService {

    //模板接口
    @FormUrlEncoded
    @POST("user/login")
    Observable<ResultModel> template(@FieldMap Map<String, String> body);


    //用户信息部分
    @FormUrlEncoded
    @POST("user/loginMobile")
    Observable<ResultModel<UserModel>> loginMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/registerMobile")
    Observable<ResultModel<UserModel>> registerMobile(@FieldMap Map<String, String> body);

    //用户信息部分
    @FormUrlEncoded
    @POST("user/updateUser")
    Observable<ResultModel<UserModel>> updateUser(@FieldMap Map<String, String> body);

    //岗位招聘部分
    @FormUrlEncoded
    @POST("recruit/getAllRecruitMobile")
    Observable<ResultModel<RecruitModel>> getAllRecruit(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruit/getFilterRecruitMobile")
    Observable<ResultModel<RecruitModel>> getFilterRecruitMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruit/searchRecruitMobile")
    Observable<ResultModel<RecruitModel>> searchRecruitMobile(@FieldMap Map<String, String> body);


    //实习经历部分
    @FormUrlEncoded
    @POST("internship_experience/addInternshipMobile")
    Observable<ResultModel<InternshipExperienceModel>> addInternshipMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("internship_experience/modifyInternshipMobile")
    Observable<ResultModel<InternshipExperienceModel>> modifyInternshipMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("internship_experience/deleteInternshipMobile")
    Observable<ResultModel<InternshipExperienceModel>> deleteInternshipMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("internship_experience/getMineInternshipMobile")
    Observable<ResultModel<InternshipExperienceModel>> getMineInternshipMobile(@FieldMap Map<String, String> body);


    //个人技能
    @FormUrlEncoded
    @POST("personal_skill/getMinePersonalSkillMobile")
    Observable<ResultModel<PersonalSkillModel>> getMinePersonalSkillMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("personal_skill/addPersonalSkillMobile")
    Observable<ResultModel<PersonalSkillModel>> addPersonalSkillMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("personal_skill/modifyPersonalSkillMobile")
    Observable<ResultModel<PersonalSkillModel>> modifyPersonalSkillMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("personal_skill/deletePersonalSkillMobile")
    Observable<ResultModel<PersonalSkillModel>> deletePersonalSkillMobile(@FieldMap Map<String, String> body);

    //个人证书
    @FormUrlEncoded
    @POST("personal_cert/getMinePersonalCertMobile")
    Observable<ResultModel<PersonalCertModel>> getMinePersonalCertMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("personal_cert/addPersonalCertMobile")
    Observable<ResultModel<PersonalCertModel>> addPersonalCertMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("personal_cert/modifyPersonalCertMobile")
    Observable<ResultModel<PersonalCertModel>> modifyPersonalCertMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("personal_cert/deletePersonalCertMobile")
    Observable<ResultModel<PersonalCertModel>> deletePersonalCertMobile(@FieldMap Map<String, String> body);


    //个人教育经历
    @FormUrlEncoded
    @POST("education_experience/getMineEducationExperienceMobile")
    Observable<ResultModel<EducationExpModel>> getMineEducationExperienceMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("education_experience/addEducationExperienceMobile")
    Observable<ResultModel<EducationExpModel>> addEducationExperienceMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("education_experience/modifyEducationExperienceMobile")
    Observable<ResultModel<EducationExpModel>> modifyEducationExperienceMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("education_experience/deleteEducationExperienceMobile")
    Observable<ResultModel<EducationExpModel>> deleteEducationExperienceMobile(@FieldMap Map<String, String> body);

}

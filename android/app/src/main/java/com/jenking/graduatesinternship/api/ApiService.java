package com.jenking.graduatesinternship.api;

import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.EducationExpModel;
import com.jenking.graduatesinternship.models.impl.EnterpriseModel;
import com.jenking.graduatesinternship.models.impl.InternshipExperienceModel;
import com.jenking.graduatesinternship.models.impl.PersonalCertModel;
import com.jenking.graduatesinternship.models.impl.PersonalSkillModel;
import com.jenking.graduatesinternship.models.impl.RecruitCollectionModel;
import com.jenking.graduatesinternship.models.impl.RecruitDeliveryModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.models.impl.ResumeEnclosureModel;
import com.jenking.graduatesinternship.models.impl.ResumeModel;
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

    //个人简历
    @FormUrlEncoded
    @POST("resume/getMineResumeMobile")
    Observable<ResultModel<ResumeModel>> getMineResumeMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("resume/addResumeMobile")
    Observable<ResultModel<ResumeModel>> addResumeMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("resume/modifyResumeMobile")
    Observable<ResultModel<ResumeModel>> modifyResumeMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("resume/deleteResumeMobile")
    Observable<ResultModel<ResumeModel>> deleteResumeMobile(@FieldMap Map<String, String> body);

    //个人简历附件
    @FormUrlEncoded
    @POST("resume_enclosure/getMineResumeEnclosureMobile")
    Observable<ResultModel<ResumeEnclosureModel>> getMineResumeEnclosureMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("resume_enclosure/addResumeEnclosureMobile")
    Observable<ResultModel<ResumeEnclosureModel>> addResumeEnclosureMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("resume_enclosure/modifyResumeEnclosureMobile")
    Observable<ResultModel<ResumeEnclosureModel>> modifyResumeEnclosureMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("resume_enclosure/deleteResumeEnclosureMobile")
    Observable<ResultModel<ResumeEnclosureModel>> deleteResumeEnclosureMobile(@FieldMap Map<String, String> body);

    //岗位收藏
    @FormUrlEncoded
    @POST("recruitment_collection/getMineRecruitmentCollectionMobile")
    Observable<ResultModel<RecruitModel>> getMineRecruitmentCollectionMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruitment_collection/addRecruitmentCollectionMobile")
    Observable<ResultModel<RecruitCollectionModel>> addRecruitmentCollectionMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruitment_collection/modifyRecruitmentCollectionMobile")
    Observable<ResultModel<RecruitCollectionModel>> modifyRecruitmentCollectionMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruitment_collection/deleteRecruitmentCollectionMobile")
    Observable<ResultModel<RecruitCollectionModel>> deleteRecruitmentCollectionMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruitment_collection/checkIsCollect")
    Observable<ResultModel<RecruitDeliveryModel>> checkIsCollect(@FieldMap Map<String, String> body);

    //岗位投递
    @FormUrlEncoded
    @POST("recruitment_delivery/getMineRecruitmentDeliveryMobile")
    Observable<ResultModel<RecruitDeliveryModel>> getMineRecruitmentDeliveryMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruitment_delivery/addRecruitmentDeliveryMobile")
    Observable<ResultModel<RecruitDeliveryModel>> addRecruitmentDeliveryMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruitment_delivery/modifyRecruitmentDeliveryMobile")
    Observable<ResultModel<RecruitDeliveryModel>> modifyRecruitmentDeliveryMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruitment_delivery/deleteRecruitmentDeliveryMobile")
    Observable<ResultModel<RecruitDeliveryModel>> deleteRecruitmentDeliveryMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("recruitment_delivery/checkIsDelivery")
    Observable<ResultModel<RecruitDeliveryModel>> checkIsDelivery(@FieldMap Map<String, String> body);

    //企业、单位
    @FormUrlEncoded
    @POST("enterprise/getEnterpriseById")
    Observable<ResultModel<EnterpriseModel>> getEnterpriseById(@FieldMap Map<String, String> body);

}

package com.rb.tb;

import com.tb.rx_retrofit.http_presenter.JsonBody;


/**
 * @Desc: -表单提交,post提交  提供统一参数封装
 * -
 * create by zhusw on 2018-9-25 11:36
 */
public class RequestFormat implements JsonBody {

    private String appSign="laksjdlk123";
    private String cno="linkZoom_test";
    private String sdkVersion="v1.3";
    private String IMEI="0198230912830980";
    private String platform="android";
    private String version="1.5";
    private String clientSignature="laksjdlk123";//将用于生成sdk用户uid,请保持唯一性
    private String nickName = "heihei";//用户昵称
    private String absoluteImageUrl = "heihei";//用户头像图片地址（看宿主技术支持的方式不同来修改）
    private Integer gender = 1;//0 男  1 女
    private String birthday = "";//生日
    private String area = "";//地区
}

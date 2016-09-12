package com.zwh.social.api.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -7825005326425008329L;

    private Integer userid;
    private String username;
    private String password;
    private String nickname;
    private String email;
    //0 man 1 woman
    private int sex;
    //性格
    private int characterId;
    //教育
    private int educationId = 0;
    //薪水
    private int workMoneyId;
    private int age;
    //生日
    private String birthday = "";
    //属相
    private String animal = "";
    //星座
    private int constellationId = 1;
    private String constellationName;
    //手机号码
    private String mobile = "";
    //身高
    private int stature;
    //体重
    private int avoirdupois;
    //体型
    private int bodybuildId;
    //长相描述
    private int faceDescId;
    //国籍
    private int nationId;
    //血型
    private int bloodId;
    //居住省
    private int inhabitProvinceId;
    //居住地
    private int inhabitCityId;
    //祖籍省
    private int homeplaceProvinceId;
    //祖籍地
    private int homeplaceCityId;
    //婚姻状态
    private int marriageId;
    //宗教
    private int religionId;
    //是否有小孩
    private int haveBabeId;
    //是否要小孩
    private int needBabeId;
    //是否喝酒 未使用
    private int beerId;
    //是否吸烟
    private int smokingId;
    //语言
    private int languageId;
    //汽车
    private int autoId;
    //住房
    private int houseId;
    //自我描述
    private String desc = "";
    //身份证
    private String identityCard = "";
    //用户等级
    private int level;
    //资料等级
    private int infoLevel;
    //小头像
    private String iconSmall = "";
    //大头像
    private String icon = "";
    //注册时间
    private String regDate = "";
    //最后活动时间
    private String lastLoginDate = "";
    //上次登录间隔
    private String beforLastLoginDate = "";
    //用户类型
    private int regType;
    //照片数量
    private int photoCount = 0;
    //犯错等级
    private int functionLevel = 0;
    //照片可见属性
    private int photoLimit = 0;
    //来自哪个通道
    private String fromChannel;

    //包月会员
    private boolean isMonthUser;
    //高级会员
    private boolean isVipUser;
    //爱情直通车会员
    private boolean isAdvertiseUser;
    //诚信(1-5)
    private int credibility;
    //恋爱状态 true 恋爱中 false 寻爱中
    private boolean loveStatus;
    //豆币会员
    private boolean beanLevel;
    //自动回招呼
    private int autoReply;

    //审核中 昵称
    private String checkingNickName;
    //审核中 内心独白
    private String checkingDesc;
    //是否是系统自动分配昵称 1：是  0：不是
    private int isAutoNickname;

    //资料完善勋章 1照片>3 2评分>80;
    private boolean infoMedal;
    // 是否实名认证。1->已验证，0->未验证
    private int isAuthentication;
    private int maxClick;

    public int getMaxClick() {
        return maxClick;
    }

    public void setMaxClick(int maxClick) {
        this.maxClick = maxClick;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public int getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(int isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public int getWorkMoneyId() {
        return workMoneyId;
    }

    public void setWorkMoneyId(int workMoneyId) {
        this.workMoneyId = workMoneyId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public int getConstellationId() {
        return constellationId;
    }

    public void setConstellationId(int constellationId) {
        this.constellationId = constellationId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStature() {
        return stature;
    }

    public void setStature(int stature) {
        this.stature = stature;
    }

    public int getAvoirdupois() {
        return avoirdupois;
    }

    public void setAvoirdupois(int avoirdupois) {
        this.avoirdupois = avoirdupois;
    }

    public int getBodybuildId() {
        return bodybuildId;
    }

    public void setBodybuildId(int bodybuildId) {
        this.bodybuildId = bodybuildId;
    }

    public int getFaceDescId() {
        return faceDescId;
    }

    public void setFaceDescId(int faceDescId) {
        this.faceDescId = faceDescId;
    }

    public int getNationId() {
        return nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

    public int getBloodId() {
        return bloodId;
    }

    public void setBloodId(int bloodId) {
        this.bloodId = bloodId;
    }

    public int getInhabitProvinceId() {
        return inhabitProvinceId;
    }

    public void setInhabitProvinceId(int inhabitProvinceId) {
        this.inhabitProvinceId = inhabitProvinceId;
    }

    public int getInhabitCityId() {
        return inhabitCityId;
    }

    public void setInhabitCityId(int inhabitCityId) {
        this.inhabitCityId = inhabitCityId;
    }

    public int getHomeplaceProvinceId() {
        return homeplaceProvinceId;
    }

    public void setHomeplaceProvinceId(int homeplaceProvinceId) {
        this.homeplaceProvinceId = homeplaceProvinceId;
    }

    public int getHomeplaceCityId() {
        return homeplaceCityId;
    }

    public void setHomeplaceCityId(int homeplaceCityId) {
        this.homeplaceCityId = homeplaceCityId;
    }

    public int getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(int marriageId) {
        this.marriageId = marriageId;
    }

    public int getReligionId() {
        return religionId;
    }

    public void setReligionId(int religionId) {
        this.religionId = religionId;
    }

    public int getHaveBabeId() {
        return haveBabeId;
    }

    public void setHaveBabeId(int haveBabeId) {
        this.haveBabeId = haveBabeId;
    }

    public int getNeedBabeId() {
        return needBabeId;
    }

    public void setNeedBabeId(int needBabeId) {
        this.needBabeId = needBabeId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getSmokingId() {
        return smokingId;
    }

    public void setSmokingId(int smokingId) {
        this.smokingId = smokingId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getInfoLevel() {
        return infoLevel;
    }

    public void setInfoLevel(int infoLevel) {
        this.infoLevel = infoLevel;
    }

    public String getIconSmall() {
        return iconSmall;
    }

    public void setIconSmall(String iconSmall) {
        this.iconSmall = iconSmall;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getBeforLastLoginDate() {
        return beforLastLoginDate;
    }

    public void setBeforLastLoginDate(String beforLastLoginDate) {
        this.beforLastLoginDate = beforLastLoginDate;
    }

    public int getRegType() {
        return regType;
    }

    public void setRegType(int regType) {
        this.regType = regType;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public int getFunctionLevel() {
        return functionLevel;
    }

    public void setFunctionLevel(int functionLevel) {
        this.functionLevel = functionLevel;
    }

    public int getPhotoLimit() {
        return photoLimit;
    }

    public void setPhotoLimit(int photoLimit) {
        this.photoLimit = photoLimit;
    }

    public String getFromChannel() {
        return fromChannel;
    }

    public void setFromChannel(String fromChannel) {
        this.fromChannel = fromChannel;
    }

    public boolean isMonthUser() {
        return isMonthUser;
    }

    public void setMonthUser(boolean monthUser) {
        isMonthUser = monthUser;
    }

    public boolean isVipUser() {
        return isVipUser;
    }

    public void setVipUser(boolean vipUser) {
        isVipUser = vipUser;
    }

    public boolean isAdvertiseUser() {
        return isAdvertiseUser;
    }

    public void setAdvertiseUser(boolean advertiseUser) {
        isAdvertiseUser = advertiseUser;
    }

    public int getCredibility() {
        return credibility;
    }

    public void setCredibility(int credibility) {
        this.credibility = credibility;
    }

    public boolean isLoveStatus() {
        return loveStatus;
    }

    public void setLoveStatus(boolean loveStatus) {
        this.loveStatus = loveStatus;
    }

    public boolean isBeanLevel() {
        return beanLevel;
    }

    public void setBeanLevel(boolean beanLevel) {
        this.beanLevel = beanLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAutoReply() {
        return autoReply;
    }

    public void setAutoReply(int autoReply) {
        this.autoReply = autoReply;
    }

    public String getConstellationName() {
        return constellationName;
    }

    public void setConstellationName(String constellationName) {
        this.constellationName = constellationName;
    }

    public String getCheckingNickName() {
        return checkingNickName;
    }

    public void setCheckingNickName(String checkingNickName) {
        this.checkingNickName = checkingNickName;
    }

    public String getCheckingDesc() {
        return checkingDesc;
    }

    public void setCheckingDesc(String checkingDesc) {
        this.checkingDesc = checkingDesc;
    }

    public boolean isInfoMedal() {
        return infoMedal;
    }

    public void setInfoMedal(boolean infoMedal) {
        this.infoMedal = infoMedal;
    }

    public int getIsAutoNickname() {return isAutoNickname;}

    public void setIsAutoNickname(int isAutoNickname) { this.isAutoNickname = isAutoNickname; }
}
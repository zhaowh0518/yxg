package com.zwh.social.api.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class User {
    private Integer userId;

    private Integer userType;

    private Integer userNo;

    private String channel;

    private String username;

    private String pwd;

    private String nickname;

    private Integer sex;

    private Integer age;

    private String birthday;

    private String sign;

    private Integer height;

    private Integer weight;

    private Integer educationId;

    private Integer bloodId;

    private Integer workId;

    private Integer constellationId;

    private String tag;

    private Integer inhabitProvinceId;

    private Integer inhabitCityId;

    private Integer rootProvinceId;

    private Integer hukouProvinceId;

    private Integer hukouCityId;

    private Integer salaryId;

    private Integer marriageId;

    private String prehead;

    private String head;

    private String phone;

    private Byte hasHouse;

    private Byte hasCar;

    private Integer seekInhabitId;

    private Integer seekAgeMin;

    private Integer seekAgeMax;

    private Integer seekEducationId;

    private Integer seekRoootId;

    private Integer seekHeightMin;

    private Integer seekHeightMax;

    private Integer seekSalaryId;

    private Integer habitChildId;

    private Integer habitLongLoveId;

    private Integer habitPremaritalSex;

    private Integer habitWithParentid;

    private Integer habitPointId;

    private Integer habitBobbyId;

    private Integer habitCharacterId;

    private Integer habitSmokeId;

    private Integer habitDrinkId;

    private Byte isMonthUser;

    private String monthEndDate;

    private Byte isVipUser;

    private Integer gold;

    private String dateReg;

    private String dataLastLogin;

    private Integer photoCount;

    private Integer state;
    
    private List<UserPhoto> photoList;    

    public List<UserPhoto> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<UserPhoto> photoList) {
		this.photoList = photoList;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getEducationId() {
        return educationId;
    }

    public void setEducationId(Integer educationId) {
        this.educationId = educationId;
    }

    public Integer getBloodId() {
        return bloodId;
    }

    public void setBloodId(Integer bloodId) {
        this.bloodId = bloodId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getConstellationId() {
        return constellationId;
    }

    public void setConstellationId(Integer constellationId) {
        this.constellationId = constellationId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getInhabitProvinceId() {
        return inhabitProvinceId;
    }

    public void setInhabitProvinceId(Integer inhabitProvinceId) {
        this.inhabitProvinceId = inhabitProvinceId;
    }

    public Integer getInhabitCityId() {
        return inhabitCityId;
    }

    public void setInhabitCityId(Integer inhabitCityId) {
        this.inhabitCityId = inhabitCityId;
    }

    public Integer getRootProvinceId() {
        return rootProvinceId;
    }

    public void setRootProvinceId(Integer rootProvinceId) {
        this.rootProvinceId = rootProvinceId;
    }

    public Integer getHukouProvinceId() {
        return hukouProvinceId;
    }

    public void setHukouProvinceId(Integer hukouProvinceId) {
        this.hukouProvinceId = hukouProvinceId;
    }

    public Integer getHukouCityId() {
        return hukouCityId;
    }

    public void setHukouCityId(Integer hukouCityId) {
        this.hukouCityId = hukouCityId;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(Integer marriageId) {
        this.marriageId = marriageId;
    }

    public String getPrehead() {
    	if(StringUtils.isNotEmpty(head)){
    		return head + "@50p";
    	}
        return prehead;
    }

    public void setPrehead(String prehead) {
        this.prehead = prehead;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(Byte hasHouse) {
        this.hasHouse = hasHouse;
    }

    public Byte getHasCar() {
        return hasCar;
    }

    public void setHasCar(Byte hasCar) {
        this.hasCar = hasCar;
    }

    public Integer getSeekInhabitId() {
        return seekInhabitId;
    }

    public void setSeekInhabitId(Integer seekInhabitId) {
        this.seekInhabitId = seekInhabitId;
    }

    public Integer getSeekAgeMin() {
        return seekAgeMin;
    }

    public void setSeekAgeMin(Integer seekAgeMin) {
        this.seekAgeMin = seekAgeMin;
    }

    public Integer getSeekAgeMax() {
        return seekAgeMax;
    }

    public void setSeekAgeMax(Integer seekAgeMax) {
        this.seekAgeMax = seekAgeMax;
    }

    public Integer getSeekEducationId() {
        return seekEducationId;
    }

    public void setSeekEducationId(Integer seekEducationId) {
        this.seekEducationId = seekEducationId;
    }

    public Integer getSeekRoootId() {
        return seekRoootId;
    }

    public void setSeekRoootId(Integer seekRoootId) {
        this.seekRoootId = seekRoootId;
    }

    public Integer getSeekHeightMin() {
        return seekHeightMin;
    }

    public void setSeekHeightMin(Integer seekHeightMin) {
        this.seekHeightMin = seekHeightMin;
    }

    public Integer getSeekHeightMax() {
        return seekHeightMax;
    }

    public void setSeekHeightMax(Integer seekHeightMax) {
        this.seekHeightMax = seekHeightMax;
    }

    public Integer getSeekSalaryId() {
        return seekSalaryId;
    }

    public void setSeekSalaryId(Integer seekSalaryId) {
        this.seekSalaryId = seekSalaryId;
    }

    public Integer getHabitChildId() {
        return habitChildId;
    }

    public void setHabitChildId(Integer habitChildId) {
        this.habitChildId = habitChildId;
    }

    public Integer getHabitLongLoveId() {
        return habitLongLoveId;
    }

    public void setHabitLongLoveId(Integer habitLongLoveId) {
        this.habitLongLoveId = habitLongLoveId;
    }

    public Integer getHabitPremaritalSex() {
        return habitPremaritalSex;
    }

    public void setHabitPremaritalSex(Integer habitPremaritalSex) {
        this.habitPremaritalSex = habitPremaritalSex;
    }

    public Integer getHabitWithParentid() {
        return habitWithParentid;
    }

    public void setHabitWithParentid(Integer habitWithParentid) {
        this.habitWithParentid = habitWithParentid;
    }

    public Integer getHabitPointId() {
        return habitPointId;
    }

    public void setHabitPointId(Integer habitPointId) {
        this.habitPointId = habitPointId;
    }

    public Integer getHabitBobbyId() {
        return habitBobbyId;
    }

    public void setHabitBobbyId(Integer habitBobbyId) {
        this.habitBobbyId = habitBobbyId;
    }

    public Integer getHabitCharacterId() {
        return habitCharacterId;
    }

    public void setHabitCharacterId(Integer habitCharacterId) {
        this.habitCharacterId = habitCharacterId;
    }

    public Integer getHabitSmokeId() {
        return habitSmokeId;
    }

    public void setHabitSmokeId(Integer habitSmokeId) {
        this.habitSmokeId = habitSmokeId;
    }

    public Integer getHabitDrinkId() {
        return habitDrinkId;
    }

    public void setHabitDrinkId(Integer habitDrinkId) {
        this.habitDrinkId = habitDrinkId;
    }

    public Byte getIsMonthUser() {
        return isMonthUser;
    }

    public void setIsMonthUser(Byte isMonthUser) {
        this.isMonthUser = isMonthUser;
    }

    public String getMonthEndDate() {
        return monthEndDate;
    }

    public void setMonthEndDate(String monthEndDate) {
        this.monthEndDate = monthEndDate;
    }

    public Byte getIsVipUser() {
        return isVipUser;
    }

    public void setIsVipUser(Byte isVipUser) {
        this.isVipUser = isVipUser;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public String getDateReg() {
        return dateReg;
    }

    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }

    public String getDataLastLogin() {
        return dataLastLogin;
    }

    public void setDataLastLogin(String dataLastLogin) {
        this.dataLastLogin = dataLastLogin;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
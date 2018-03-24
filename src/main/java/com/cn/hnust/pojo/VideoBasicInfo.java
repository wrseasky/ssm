package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

public class VideoBasicInfo implements Serializable{

	private static final long serialVersionUID = -3275588918686919567L;

	private Long id;
	
	private String name;
	
	private String channelType;
	
	private String tags;
	
	private Integer network;
	
	private Date releaseTime;
	
	private Date offLineTime;
	
	private Integer oldFlag;
	
	private Date updateTime;
	
	private Integer editedFlag;
	
	private String area;
	
	private String editedField;
	
	@Transient
	private List<String> editedFields;
	
	private Integer episodeNum;
	
	private Integer totalEpisodeNum;
	
	private String producer;
	
	private String director;
	
	private String mainActor;
	
	private Integer isVip;
	
	private Integer notVipNum;
	
	private Integer duration;
	
	private Date notVipDownTime;
	
	@Column(name = "IPOriginalType")
	private String ipOriginalType;
	
	@Column(name = "IPOriginalName")
	private String ipOriginalName;
	
	private String hpURL;
	
	private String vipTime;
	
	@Transient
	private Date vipDate;
	
	private String notVipTime;
	
	@Transient
	private Date notVipDate;
	
	private String baikeUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getNetwork() {
		return network;
	}

	public void setNetwork(Integer network) {
		this.network = network;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Date getOffLineTime() {
		return offLineTime;
	}

	public void setOffLineTime(Date offLineTime) {
		this.offLineTime = offLineTime;
	}

	public Integer getOldFlag() {
		return oldFlag;
	}

	public void setOldFlag(Integer oldFlag) {
		this.oldFlag = oldFlag;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getEditedFlag() {
		return editedFlag;
	}

	public void setEditedFlag(Integer editedFlag) {
		this.editedFlag = editedFlag;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEditedField() {
		return editedField;
	}

	public void setEditedField(String editedField) {
		this.editedField = editedField;
	}

	public Integer getEpisodeNum() {
		return episodeNum;
	}

	public void setEpisodeNum(Integer episodeNum) {
		this.episodeNum = episodeNum;
	}

	public Integer getTotalEpisodeNum() {
		return totalEpisodeNum;
	}

	public void setTotalEpisodeNum(Integer totalEpisodeNum) {
		this.totalEpisodeNum = totalEpisodeNum;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMainActor() {
		return mainActor;
	}

	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getNotVipNum() {
		return notVipNum;
	}

	public void setNotVipNum(Integer notVipNum) {
		this.notVipNum = notVipNum;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getNotVipDownTime() {
		return notVipDownTime;
	}

	public void setNotVipDownTime(Date notVipDownTime) {
		this.notVipDownTime = notVipDownTime;
	}

	public String getIpOriginalType() {
		return ipOriginalType;
	}

	public void setIpOriginalType(String ipOriginalType) {
		this.ipOriginalType = ipOriginalType;
	}

	public String getIpOriginalName() {
		return ipOriginalName;
	}

	public void setIpOriginalName(String ipOriginalName) {
		this.ipOriginalName = ipOriginalName;
	}

	public String getHpURL() {
		return hpURL;
	}

	public void setHpURL(String hpURL) {
		this.hpURL = hpURL;
	}

	public String getVipTime() {
		return vipTime;
	}

	public void setVipTime(String vipTime) {
		this.vipTime = vipTime;
	}

	public String getNotVipTime() {
		return notVipTime;
	}

	public void setNotVipTime(String notVipTime) {
		this.notVipTime = notVipTime;
	}

	public String getBaikeUrl() {
		return baikeUrl;
	}
	
	public void setBaikeUrl(String baikeUrl) {
		this.baikeUrl = baikeUrl;
	}
	
	public List<String> getEditedFields() {
		return editedFields;
	}

	public void setEditedFields(List<String> editedFields) {
		this.editedFields = editedFields;
	}

	public Date getVipDate() {
		return vipDate;
	}

	public void setVipDate(Date vipDate) {
		this.vipDate = vipDate;
	}

	public Date getNotVipDate() {
		return notVipDate;
	}

	public void setNotVipDate(Date notVipDate) {
		this.notVipDate = notVipDate;
	}

	@Override
	public String toString() {
		return "VideoBasicInfo [id=" + id + ", name=" + name + ", channelType=" + channelType + ", tags=" + tags + ", network=" + network + ", releaseTime=" + releaseTime + ", offLineTime=" + offLineTime + ", oldFlag=" + oldFlag + ", updateTime=" + updateTime + ", editedFlag=" + editedFlag
				+ ", area=" + area + ", editedField=" + editedField + ", episodeNum=" + episodeNum + ", totalEpisodeNum=" + totalEpisodeNum + ", producer=" + producer + ", director=" + director + ", mainActor=" + mainActor + ", isVip=" + isVip + ", notVipNum=" + notVipNum + ", duration=" + duration
				+ ", notVipDownTime=" + notVipDownTime + ", IPOriginalType=" + ipOriginalType + ", IPOriginalName=" + ipOriginalName + ", hpURL=" + hpURL + ", vipTime=" + vipTime + ", notVipTime=" + notVipTime + ", baikeUrl=" + baikeUrl + "]";
	}

}

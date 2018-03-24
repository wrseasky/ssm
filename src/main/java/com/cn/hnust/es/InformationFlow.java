package com.cn.hnust.es;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

@SuppressWarnings("all")
public class InformationFlow {
	private Integer status;

	private Integer topicID;

	private Long updateTime;

	private Integer updateFreq;

	private List videoURL = Collections.EMPTY_LIST;

	private String name;

	// 提醒
	private String title;

	private String contentTitle;

	private String url;

	private List data = Collections.EMPTY_LIST;

	private List<Map<String, String>> imageURL;

	private List tags;

	private List<Long> child;

	@Transient
	private List<InformationFlow> child2;

	private String content;

	private String hpURL;

	private Integer intervalUpdateTime;

	private List musicURL = Collections.EMPTY_LIST;

	private Integer nameID;

	private String type;

	private String channel;

	private Long MCID;

	@Transient
	private String _id;

	@Transient
	private String _index;

	private Integer weixin2Rank;

	private String sourceName;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_index() {
		return _index;
	}

	public void set_index(String _index) {
		this._index = _index;
	}

	public Long getMCID() {
		return MCID;
	}

	public void setMCID(Long mCID) {
		MCID = mCID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTopicID() {
		return topicID;
	}

	public void setTopicID(Integer topicID) {
		this.topicID = topicID;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateFreq() {
		return updateFreq;
	}

	public void setUpdateFreq(Integer updateFreq) {
		this.updateFreq = updateFreq;
	}

	public List getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(List videoURL) {
		this.videoURL = videoURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public List<Map<String, String>> getImageURL() {
		return imageURL;
	}

	public void setImageURL(List<Map<String, String>> imageURL) {
		this.imageURL = imageURL;
	}

	public List getTags() {
		return tags;
	}

	public void setTags(List tags) {
		this.tags = tags;
	}

	public List<Long> getChild() {
		return child;
	}

	public void setChild(List<Long> child) {
		this.child = child;
	}

	public List<InformationFlow> getChild2() {
		return child2;
	}

	public void setChild2(List<InformationFlow> child2) {
		this.child2 = child2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHpURL() {
		return hpURL;
	}

	public void setHpURL(String hpURL) {
		this.hpURL = hpURL;
	}

	public Integer getIntervalUpdateTime() {
		return intervalUpdateTime;
	}

	public void setIntervalUpdateTime(Integer intervalUpdateTime) {
		this.intervalUpdateTime = intervalUpdateTime;
	}

	public List getMusicURL() {
		return musicURL;
	}

	public void setMusicURL(List musicURL) {
		this.musicURL = musicURL;
	}

	public Integer getNameID() {
		return nameID;
	}

	public void setNameID(Integer nameID) {
		this.nameID = nameID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getWeixin2Rank() {
		return weixin2Rank;
	}

	public void setWeixin2Rank(Integer weixin2Rank) {
		this.weixin2Rank = weixin2Rank;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((_index == null) ? 0 : _index.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformationFlow other = (InformationFlow) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (_index == null) {
			if (other._index != null)
				return false;
		} else if (!_index.equals(other._index))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InformationFlow [status=" + status + ", topicID=" + topicID + ", updateTime=" + updateTime + ", updateFreq=" + updateFreq + ", videoURL=" + videoURL + ", name=" + name + ", title=" + title + ", url=" + url + ", data=" + data + ", imageURL=" + imageURL + ", tags=" + tags + ", child="
				+ child + ", child2=" + child2 + ", content=" + content + ", hpURL=" + hpURL + ", intervalUpdateTime=" + intervalUpdateTime + ", musicURL=" + musicURL + ", nameID=" + nameID + ", type=" + type + ", channel=" + channel + ", MCID=" + MCID + ", _id=" + _id + ", _index=" + _index + "]";
	}
}

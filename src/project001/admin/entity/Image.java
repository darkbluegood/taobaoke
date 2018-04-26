package project001.admin.entity;

public class Image {
	private int id;
	private String tagUuid;
	private String url;
	private String createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTagUuid() {
		return tagUuid;
	}
	public void setTagUuid(String tagUuid) {
		this.tagUuid = tagUuid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}

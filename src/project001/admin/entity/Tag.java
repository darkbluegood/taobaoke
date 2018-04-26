package project001.admin.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="TAG")
public class Tag {
	@Id
	@Column(name="ID",length=11)
	private int id;
	@Column(name="FID",length=11)
	private int fid;
	@Column(name="TITLE")
	private String title;
	@Column(name="SORT",length=11)
	private int sort;
	@Column(name="TYPE")
	private String type;
	@Column(name="TYPE_NAME")
	private String type_name;
	@Column(name="UUID")
	private String uuid;
	@Column(name="URL")
	private String url;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="KEYWORD")
	private String keyword;
	@Column(name="SRC")
	private String src;
	@Column(name="CREATETIME")
	private String createTime;
	@Column(name="COLOR")
	private String color;
	@Transient
	private MultipartFile image;
	@Transient
	private ArrayList<Tag> subset;
	@Transient
	private String q;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public ArrayList<Tag> getSubset() {
		return subset;
	}
	public void setSubset(ArrayList<Tag> subset) {
		this.subset = subset;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	
}

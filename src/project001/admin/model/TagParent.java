package project001.admin.model;

import java.util.ArrayList;

import project001.admin.entity.Category;

public class TagParent<T> {
	private int id;
	private int fid;
	private String title;
	private ArrayList<T> subset;
	private String type;
	private String type_name;
	private String fid_title;
	private int sort;
	private String imageUrl;
	private String src;
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
	public ArrayList<T> getSubset() {
		return subset;
	}
	public void setSubset(ArrayList<T> subset) {
		this.subset = subset;
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
	public String getFid_title() {
		return fid_title;
	}
	public void setFid_title(String fid_title) {
		this.fid_title = fid_title;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
}

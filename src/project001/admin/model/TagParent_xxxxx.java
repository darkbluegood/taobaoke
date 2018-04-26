package project001.admin.model;

import java.util.ArrayList;


import project001.admin.entity.Tag;

public class TagParent_xxxxx {
	private int id;
	private int fid;
	private String title;
	private ArrayList<Tag> subset;
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
	public ArrayList<Tag> getSubset() {
		return subset;
	}
	public void setSubset(ArrayList<Tag> subset) {
		this.subset = subset;
	}
	
}

package project001.admin.model;

public class AddCategory {
	private int id;
	private int fid;
	private String title;
	private String fid_title;
	private int sort;
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
	
}

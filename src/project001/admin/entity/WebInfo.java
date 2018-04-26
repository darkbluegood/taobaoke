package project001.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "t_menu")
public class WebInfo {
	@Id
	@Column(name = "id", length = 36)
	private int id;
	@Column(name = "title", length = 100)
	private String title;
	@Column(name = "keyword", length = 100)
	private String keyword;
	@Column(name = "description", length = 100)
	private String description;
	@Column(name = "copyright", length = 100)
	private String copyright;
	@Column(name = "name", length = 100)
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

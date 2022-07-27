package model.bean;

import java.util.Date;

public class Song {

	private int id;
	private String name;
	private String preview_text;
	private String detail_text;
	private Date date_create;
	private int cat_id;
	private String picture;
	private int counter;
	private int song;
	private String cats;
	
	
	public Song(int id, String name, String picture, int counter, String cats) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.counter = counter;
		this.cats = cats;
	}
	public Song(int id, String name, String preview_text, String detail_text, Date date_create, int cat_id,
			String picture, String cats, int counter) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.cat_id = cat_id;
		this.picture = picture;
		
		this.cats = cats;
		this.counter = counter;
	}
	public String getCats() {
		return cats;
	}
	public void setCats(String cats) {
		this.cats = cats;
	}
	public Song(int song) {
		super();
		this.song = song;
	}
	public int getSong() {
		return song;
	}
	public void setSong(int song) {
		this.song = song;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview_text() {
		return preview_text;
	}
	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}
	public String getDetail_text() {
		return detail_text;
	}
	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}
	public Date getDate_create() {
		return date_create;
	}
	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Song(int id, String name, String preview_text, String detail_text, Date date_create, int cat_id,
			String picture, int counter) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.cat_id = cat_id;
		this.picture = picture;
		this.counter = counter;
	}
	public Song(int id, String name, String preview_text, String detail_text, Date date_create, int cat_id,
			String picture, int counter, int song, String cats) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.cat_id = cat_id;
		this.picture = picture;
		this.counter = counter;
	
		this.cats = cats;
	}
	
	
}

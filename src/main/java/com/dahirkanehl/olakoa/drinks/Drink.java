package com.dahirkanehl.olakoa.drinks;

import java.net.URL;

public class Drink {
	private String id;
	private URL thumbnail;
	private String description;
	private String name;
	private int unitCost;
	private String ownerId;
	private boolean posted;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public URL getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(URL thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(int unitCost) {
		this.unitCost = unitCost;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public boolean isPosted() {
		return posted;
	}

	public void setPosted(boolean posted) {
		this.posted = posted;
	}

	private Drink(Builder b) {
		this.description = b.description;
		this.thumbnail = b.thumbnail;
		this.unitCost = b.unitCost;
		this.name = b.name;
		this.ownerId = b.ownerId;
		this.id = b.id;
		this.posted = b.posted;
	}
	
	@Override
	public String toString() {
		return id + "," + name + "," + name + "," + thumbnail + ","+ description + "," + unitCost + "," + ownerId + "," + posted;
	}

	public static class Builder {
		private String description;
		private URL thumbnail;
		private String name;
		private int unitCost;
		private String ownerId;
		private String id;
		private boolean posted;
		
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		
		public Builder thumbnail(URL thumbnail) {
			this.thumbnail = thumbnail;
			return this;
		}
		
		public Builder unitCost(int unitCost) {
			this.unitCost = unitCost;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder ownerId(String ownerId) {
			this.ownerId = ownerId;
			return this;
		}
		
		public Builder posted(boolean posted) {
			this.posted = posted;
			return this;
		}
		
		public Drink build() {
			return new Drink(this);
		}
	}
}

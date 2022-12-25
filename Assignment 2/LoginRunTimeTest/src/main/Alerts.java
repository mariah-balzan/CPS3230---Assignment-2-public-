package main;

public class Alerts{
	private String id;
	private int alertType;
	private String heading;
	private String description;
	private String url;
	private String imageURL;
	private String postedBy;
	private int priceInCents;
	private String postDate;
	
	
	//Constructor
	public Alerts(int alertType, String heading, String description, String url,String imageURL,  String postedBy,int priceInCents){
		this.alertType = alertType;
		this.heading = heading;
		this.description = description;
		this.url = url;
		this.imageURL = imageURL;
		this.postedBy = postedBy;
		this.priceInCents = priceInCents;
		
	}
	
//Old methods - Calling getters and setters
//	public String getId(){
//		return id;
//	}
//	
//	public void setId(String id){
//		this.id = id;
//	}
//	
//	public int getAlertType(){
//		return alertType;
//	}
//	
//	public void setAlertType(int alertType){
//		this.alertType = alertType;
//	}
//	
//	public String getHeading(){
//		return heading;
//	}
//	
//	public void setHeading(String heading){
//		this.heading = heading;
//	}
//	
//	public String getDescription(){
//		return description;
//	}
//	
//	public void setDescription(String description){
//		this.description = description;
//	}
//	
//	public String getUrl(){
//		return url;
//	}
//	
//	public void setUrl(String url){
//		this.url = url;
//	}
//	
//
//	public String getImageURL(){
//		return imageURL;
//	}
//	
//	public void setImageURL(String imageURL){
//		this.imageURL = imageURL;
//	}
//	
//	public String getPostedBy(){
//		return postedBy;
//	}
//	
//	public void setPostedBy(String postedBy){
//		this.postedBy = postedBy;
//	}
//	
//	public int getPriceInCents(){
//		return priceInCents;
//	}
//	
//	public void setPriceInCents(int priceInCents){
//		this.priceInCents = priceInCents;
//	}
//	
//	public String getPostDate(){
//		return postDate;
//	}
//	
//	public void setPostDate(String postDate){
//		this.postDate = postDate;
//	}
//	
//	
//	//Creating to string
//	public String toString(){
//		return "alerts [id=" 
//				+  id 
//				+ ", heading ="
//				+ heading
//				+ ", description ="
//				+ description
//				+ ", url ="
//				+ url
//				+ ", imageUrl ="
//				+ imageURL
//				+ ", postedBy ="
//				+ postedBy
//				+ ", priceInCents ="
//				+ priceInCents + "]";
//				
//	}
}


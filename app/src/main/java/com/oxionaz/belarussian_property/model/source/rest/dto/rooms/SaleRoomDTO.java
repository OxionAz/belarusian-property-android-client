package com.oxionaz.belarussian_property.model.source.rest.dto.rooms;

import java.util.Date;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleRoomDTO {

    @SerializedName("additionally")
    @Expose
    private String additionally;
    @SerializedName("agent")
    @Expose
    private String agent;
    @SerializedName("balcony")
    @Expose
    private String balcony;
    @SerializedName("ceiling_height")
    @Expose
    private Float ceilingHeight;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("corp")
    @Expose
    private String corp;
    @SerializedName("date_time")
    @Expose
    private Date dateTime;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("floor")
    @Expose
    private Integer floor;
    @SerializedName("floors")
    @Expose
    private String floors;
    @SerializedName("house")
    @Expose
    private Integer house;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kitchen")
    @Expose
    private Float kitchen;
    @SerializedName("metro")
    @Expose
    private String metro;
    @SerializedName("metro_branch")
    @Expose
    private String metroBranch;
    @SerializedName("metro_time")
    @Expose
    private String metroTime;
    @SerializedName("modified_date")
    @Expose
    private Date modifiedDate;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("numbers")
    @Expose
    private List<String> numbers;
    @SerializedName("own")
    @Expose
    private String own;
    @SerializedName("phone")
    @Expose
    private boolean phone;
    @SerializedName("photos")
    @Expose
    private List<String> photos;
    @SerializedName("plan")
    @Expose
    private String plan;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("psm")
    @Expose
    private Integer psm;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("repair")
    @Expose
    private String repair;
    @SerializedName("rooms")
    @Expose
    private String rooms;
    @SerializedName("sales_terms")
    @Expose
    private String salesTerms;
    @SerializedName("section")
    @Expose
    private Integer section;
    @SerializedName("site")
    @Expose
    private String site;
    @SerializedName("square")
    @Expose
    private Float square;
    @SerializedName("square_useful")
    @Expose
    private Float squareUseful;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("total_floors")
    @Expose
    private Integer totalFloors;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("wc")
    @Expose
    private String wc;
    @SerializedName("year_of_construction")
    @Expose
    private Integer yearOfConstruction;

    public String getAdditionally() {
        return additionally;
    }

    public void setAdditionally(String additionally) {
        this.additionally = additionally;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getKitchen() {
        return kitchen;
    }

    public void setKitchen(Float kitchen) {
        this.kitchen = kitchen;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getMetroBranch() {
        return metroBranch;
    }

    public void setMetroBranch(String metroBranch) {
        this.metroBranch = metroBranch;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPsm() {
        return psm;
    }

    public void setPsm(Integer psm) {
        this.psm = psm;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getSalesTerms() {
        return salesTerms;
    }

    public void setSalesTerms(String salesTerms) {
        this.salesTerms = salesTerms;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public Float getSquareUseful() {
        return squareUseful;
    }

    public void setSquareUseful(Float squareUseful) {
        this.squareUseful = squareUseful;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(Integer totalFloors) {
        this.totalFloors = totalFloors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getWc() {
        return wc;
    }

    public void setWc(String wc) {
        this.wc = wc;
    }

    public Integer getYearOfConstruction() {
        return yearOfConstruction;
    }

    public void setYearOfConstruction(Integer yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    public Float getCeilingHeight() {
        return ceilingHeight;
    }

    public void setCeilingHeight(Float ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getMetroTime() {
        return metroTime;
    }

    public void setMetroTime(String metroTime) {
        this.metroTime = metroTime;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isPhone() {
        return phone;
    }

    public void setPhone(boolean phone) {
        this.phone = phone;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}

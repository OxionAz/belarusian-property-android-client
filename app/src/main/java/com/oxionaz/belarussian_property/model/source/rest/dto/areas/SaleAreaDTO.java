package com.oxionaz.belarussian_property.model.source.rest.dto.areas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;

public class SaleAreaDTO {

    @SerializedName("additionally")
    @Expose
    private String additionally;
    @SerializedName("agent")
    @Expose
    private String agent;
    @SerializedName("built_area")
    @Expose
    private String builtArea;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("coord_map")
    @Expose
    private String coordMap;
    @SerializedName("coord_nav")
    @Expose
    private String coordNav;
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
    @SerializedName("electricity")
    @Expose
    private String electricity;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gas")
    @Expose
    private String gas;
    @SerializedName("heating")
    @Expose
    private String heating;
    @SerializedName("house")
    @Expose
    private Integer house;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kitchen")
    @Expose
    private Float kitchen;
    @SerializedName("land_area")
    @Expose
    private Float landArea;
    @SerializedName("levels")
    @Expose
    private Integer levels;
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
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("ready")
    @Expose
    private Integer ready;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("roof")
    @Expose
    private String roof;
    @SerializedName("rooms")
    @Expose
    private Integer rooms;
    @SerializedName("sales_terms")
    @Expose
    private String salesTerms;
    @SerializedName("section")
    @Expose
    private Integer section;
    @SerializedName("sewer")
    @Expose
    private String sewer;
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
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("view")
    @Expose
    private String view;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("wall")
    @Expose
    private String wall;
    @SerializedName("water")
    @Expose
    private String water;
    @SerializedName("year_of_construction")
    @Expose
    private Integer yearOfConstruction;

    public String getAdditionally() {
        return additionally;
    }

    public void setAdditionally(String additionally) {
        this.additionally = additionally;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoordMap() {
        return coordMap;
    }

    public void setCoordMap(String coordMap) {
        this.coordMap = coordMap;
    }

    public String getCoordNav() {
        return coordNav;
    }

    public void setCoordNav(String coordNav) {
        this.coordNav = coordNav;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getLandArea() {
        return landArea;
    }

    public void setLandArea(Float landArea) {
        this.landArea = landArea;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
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

    public Integer getReady() {
        return ready;
    }

    public void setReady(Integer ready) {
        this.ready = ready;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
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

    public String getSewer() {
        return sewer;
    }

    public void setSewer(String sewer) {
        this.sewer = sewer;
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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getBuiltArea() {
        return builtArea;
    }

    public void setBuiltArea(String builtArea) {
        this.builtArea = builtArea;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Float getKitchen() {
        return kitchen;
    }

    public void setKitchen(Float kitchen) {
        this.kitchen = kitchen;
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

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
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

    public Integer getYearOfConstruction() {
        return yearOfConstruction;
    }

    public void setYearOfConstruction(Integer yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }
}

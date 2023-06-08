package com.example.demo.accreditation;

public class Accreditation {
    private boolean activeFlag;
    private String name;
    private String description;
    private String accreditationId;
    private String imagePath;
    private String imageType;

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccreditationId() {
        return accreditationId;
    }

    public void setAccreditationId(String accreditationId) {
        this.accreditationId = accreditationId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public AccreditationJsonBean toJsonBean(String imagePath) {
        AccreditationJsonBean accreditationJson = new AccreditationJsonBean();
        accreditationJson.setName(this.getName());
        accreditationJson.setDescription(this.getDescription());
        accreditationJson.setAccreditationId(this.getAccreditationId());
        accreditationJson.setImagePath(imagePath);
        return accreditationJson;
    }
}

package ru.ilya.shopcraftercore.yookasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YookasaRequestDto {
    private String type;
    private String event;
    private YookasaRequestObjectDto object;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public YookasaRequestObjectDto getObject() {
        return object;
    }

    public void setObject(YookasaRequestObjectDto object) {
        this.object = object;
    }
}

package ru.ilya.shopcraftercore.yookasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YookasaRequestObjectDto {
    private String id;
    private String status;
    private AmountDto amountDto;
    private AmountDto incomeAmount;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AmountDto getAmountDto() {
        return amountDto;
    }

    public void setAmountDto(AmountDto amountDto) {
        this.amountDto = amountDto;
    }

    public AmountDto getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(AmountDto incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

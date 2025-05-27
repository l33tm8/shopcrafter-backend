package ru.ilya.shopcraftercore.yookasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponseDto {
    private String id;
    private String status;
    private Confirmation confirmation;

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

    public Confirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Confirmation confirmation) {
        this.confirmation = confirmation;
    }

    public static class Confirmation {
        private String confirmation_url;

        public String getConfirmation_url() {
            return confirmation_url;
        }

        public void setConfirmation_url(String confirmation_url) {
            this.confirmation_url = confirmation_url;
        }
    }

}

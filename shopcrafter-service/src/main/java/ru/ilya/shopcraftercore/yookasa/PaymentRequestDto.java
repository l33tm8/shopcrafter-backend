package ru.ilya.shopcraftercore.yookasa;

public class PaymentRequestDto {
    private AmountDto amount;
    private PaymentMethodData payment_method_data;
    private Confirmation confirmation;
    private String description;
    private boolean capture = true;

    public AmountDto getAmount() {
        return amount;
    }

    public void setAmount(AmountDto amount) {
        this.amount = amount;
    }

    public PaymentMethodData getPayment_method_data() {
        return payment_method_data;
    }

    public void setPayment_method_data(PaymentMethodData payment_method_data) {
        this.payment_method_data = payment_method_data;
    }

    public Confirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Confirmation confirmation) {
        this.confirmation = confirmation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCapture() {
        return capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public static class PaymentMethodData {
        private String type = "bank_card";

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Confirmation {
        private String type = "redirect";
        private String return_url = "https://google.com/";

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReturn_url() {
            return return_url;
        }

        public void setReturn_url(String return_url) {
            this.return_url = return_url;
        }
    }
}

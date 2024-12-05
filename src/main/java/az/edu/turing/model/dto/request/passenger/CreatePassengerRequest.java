package az.edu.turing.model.dto.request.passenger;

import java.time.LocalDateTime;

public class CreatePassengerRequest {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CreatePassengerRequest() {}

    public CreatePassengerRequest(CreatePassengerEntityRequestBuilder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static class CreatePassengerEntityRequestBuilder {

        private String name;
        private String surname;
        private String email;
        private String phoneNumber;

        public static CreatePassengerEntityRequestBuilder builder() {
            return new CreatePassengerEntityRequestBuilder();
        }

        public CreatePassengerEntityRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreatePassengerEntityRequestBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public CreatePassengerEntityRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CreatePassengerEntityRequestBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CreatePassengerRequest build() {
            return new CreatePassengerRequest(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

package az.edu.turing.model.dto.request.passenger;

import java.time.LocalDateTime;

public class UpdatePassengerRequest {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean isDeleted;
    private LocalDateTime updatedAt;

    public UpdatePassengerRequest(UpdatePassengerRequestBuilder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.isDeleted = builder.isDeleted;
        this.updatedAt = LocalDateTime.now();
    }

    public static class UpdatePassengerRequestBuilder {

        private String name;
        private String surname;
        private String email;
        private String phoneNumber;
        private boolean isDeleted;

        public static UpdatePassengerRequestBuilder builder() {
            return new UpdatePassengerRequestBuilder();
        }

        public UpdatePassengerRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdatePassengerRequestBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public UpdatePassengerRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UpdatePassengerRequestBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UpdatePassengerRequestBuilder isDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public UpdatePassengerRequest build() {
            return new UpdatePassengerRequest(this);
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

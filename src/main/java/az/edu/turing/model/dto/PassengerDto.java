package az.edu.turing.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class PassengerDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private PassengerDto() {
    }

    public PassengerDto(PassengerDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.isDeleted = builder.isDeleted;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class PassengerDtoBuilder {
        private Long id;
        private String name;
        private String surname;
        private String email;
        private String phoneNumber;
        private Boolean isDeleted;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static PassengerDtoBuilder builder() {
            return new PassengerDtoBuilder();
        }

        public PassengerDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PassengerDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PassengerDtoBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public PassengerDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PassengerDtoBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PassengerDtoBuilder isDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public PassengerDtoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PassengerDtoBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public PassengerDto build() {
            return new PassengerDto(this);
        }
    }

    public Long getId() {
        return id;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerDto that = (PassengerDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phoneNumber, isDeleted, createdAt);
    }

    @Override
    public String toString() {
        return String.format("PassengerEntity{id=%d, name=%s, surname=%s, email=%s, phoneNumber=%s, isDeleted=%s, createdAt=%s, updatedAt=%s}",
                id, name, surname, email, phoneNumber, isDeleted, createdAt, updatedAt);
    }
}

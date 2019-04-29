package co.anbora.labs.cleanlogin.domain.model;

public final class User {

    private final String id;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;

    private User(String id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public static Builder builder(User user) {
        return new Builder()
                .id(user.id)
                .name(user.name)
                .phone(user.phone)
                .email(user.email)
                .address(user.address);
    }

    public static class Builder {

        private String id;
        private String name;
        private String phone;
        private String email;
        private String address;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = id;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this.id, this.name, this.phone, this.email, this.address);
        }

    }

}

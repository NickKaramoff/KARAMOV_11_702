package ru.karamoff;

import java.time.LocalDate;

public class Remote {
    private Television attachedTelevision;
    private String remoteName, serialNumber, brandName, manufacturer;
    private LocalDate lifetime;

    public void switchTo(String channelName) {
        attachedTelevision.runningNow(channelName);
    }

    public void scheduleOf(String channelName) {
        attachedTelevision.displaySchedule(channelName);
    }

    public Remote(Builder builder) {
        this.attachedTelevision = builder.attachedTelevision;
        this.remoteName = builder.remoteName;
        this.serialNumber = builder.serialNumber;
        this.brandName = builder.brandName;
        this.manufacturer = builder.manufacturer;
        this.lifetime = builder.lifetime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Television attachedTelevision;
        private String remoteName = "Default";
        private String serialNumber = "AB123456";
        private String brandName = "NoName";
        private String manufacturer = "China";
        private LocalDate lifetime = LocalDate.of(2020, 1,1);

        public Builder attachedTelevision(Television attachedTelevision) {
            this.attachedTelevision = attachedTelevision;
            return this;
        }

        public Builder remoteName(String remoteName) {
            this.remoteName = remoteName;
            return this;
        }

        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder brandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public Builder manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder lifetime(LocalDate lifetime) {
            this.lifetime = lifetime;
            return this;
        }

        public Remote build() {
            return new Remote(this);
        }
    }

    public Television getAttachedTelevision() {
        return attachedTelevision;
    }

    public String getRemoteName() {
        return remoteName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public LocalDate getLifetime() {
        return lifetime;
    }
}

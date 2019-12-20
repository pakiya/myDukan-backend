package com.universal.entity.response.security;

import com.universal.entity.enums.DeviceTypes;

public class UserAgent {
    private String browserAgent;
    private String deviceId;
    private String brand;
    private String OsVersion;
    private DeviceTypes deviceType;
    private String appVersion;
    private String raw;

    public String getBrowserAgent() {
        return browserAgent;
    }

    public void setBrowserAgent(String browserAgent) {
        this.browserAgent = browserAgent;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOsVersion() {
        return OsVersion;
    }

    public void setOsVersion(String osVersion) {
        OsVersion = osVersion;
    }

    public DeviceTypes getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypes deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((OsVersion == null) ? 0 : OsVersion.hashCode());
        result = prime * result + ((appVersion == null) ? 0 : appVersion.hashCode());
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((browserAgent == null) ? 0 : browserAgent.hashCode());
        result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
        result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserAgent other = (UserAgent) obj;

        if (appVersion == null) {
            if (other.appVersion != null)
                return false;
        } else if (!appVersion.equals(other.appVersion))
            return false;
        if (brand == null) {
            if (other.brand != null)
                return false;
        } else if (!brand.equals(other.brand))
            return false;

        if (deviceId == null) {
            if (other.deviceId != null)
                return false;
        } else if (!deviceId.equals(other.deviceId))
            return false;
        return deviceType == other.deviceType;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}

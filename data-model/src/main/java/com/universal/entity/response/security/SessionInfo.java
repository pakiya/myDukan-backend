package com.universal.entity.response.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.universal.constants.CommonConstants;
import com.universal.utils.Util;
import net.sf.json.JSONObject;

import java.sql.Timestamp;

public class SessionInfo {
    private String id;
    private String userId;
    private boolean active;
    private Timestamp stampCreated;
    private Timestamp stampModified;
    private Timestamp expiryTimeStamp;
    private JSONObject sessionData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getStampCreated() {
        return stampCreated;
    }

    public void setStampCreated(Timestamp stampCreated) {
        this.stampCreated = stampCreated;
    }

    public Timestamp getStampModified() {
        return stampModified;
    }

    public void setStampModified(Timestamp stampModified) {
        this.stampModified = stampModified;
    }

    public JSONObject getSessionData() {
        return sessionData;
    }

    public void setSessionData(JSONObject sessionData) {
        this.sessionData = sessionData;
    }

    @JsonIgnore
    public boolean isLoggedIn() {
        if (sessionData == null)
            return false;

        return sessionData.getBoolean(CommonConstants.SessionConstants.IS_LOGGED_IN);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getExpiryTimeStamp() {
        return expiryTimeStamp;
    }

    public void setExpiryTimeStamp(Timestamp expiryTimeStamp) {
        this.expiryTimeStamp = expiryTimeStamp;
    }

    @JsonIgnore
    public String getSessionDataStr() {
        return Util.convertObjectToString(sessionData);
    }

    public Object getValue(String key) {
        if (sessionData == null) {
            return null;
        } else
            return sessionData.get(key);
    }
}

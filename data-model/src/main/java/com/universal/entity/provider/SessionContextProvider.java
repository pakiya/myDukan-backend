package com.universal.entity.provider;


import com.universal.constants.CommonConstants;
import com.universal.entity.response.security.SessionInfo;
import com.universal.entity.response.security.UserAgent;
import com.universal.utils.Util;
import sun.plugin.util.UserProfile;

/**
 * @author pankaj
 */
public class SessionContextProvider {

    private static InheritableThreadLocal<SessionInfo> sessionInfoHolder = new InheritableThreadLocal<SessionInfo>();
    private static InheritableThreadLocal<UserProfile> userProfileHolder = new InheritableThreadLocal<>();
    private static InheritableThreadLocal<String> visitIdHolder = new InheritableThreadLocal<>();

    public static void set(SessionInfo sessionInfo) {
        sessionInfoHolder.set(sessionInfo);
    }
    public static void setProfile(UserProfile userProfile) {
        userProfileHolder.set(userProfile);
    }
    public static void setVisitId(String visitId) {visitIdHolder.set(visitId);}

    public static SessionInfo get() {
        return sessionInfoHolder.get();
    }

    public static void reset() {
        sessionInfoHolder.remove();
        userProfileHolder.remove();
        visitIdHolder.remove();
    }

    public static String getUserId() {
        if (sessionInfoHolder.get() == null) {
            return null;
        } else
            return sessionInfoHolder.get().getUserId();
    }

    public static UserProfile getUserProfile() {
        if (userProfileHolder.get() == null) {
            return null;
        } else
            return userProfileHolder.get();
    }

    public static String getSessionId() {
        if (sessionInfoHolder.get() == null) {
            return null;
        } else
            return sessionInfoHolder.get().getId();
    }

    public static String getValue(String key) {
        if (sessionInfoHolder.get() == null)
            return null;
        else
            return sessionInfoHolder.get().getSessionData().getString(key);
    }

    public static UserAgent getUserAgent() {
        if (sessionInfoHolder.get() == null)
            return null;

        try {
            String userAgentStr = sessionInfoHolder.get().getSessionData()
                    .getString(CommonConstants.USER_AGENT_KEY);

            UserAgent userAgent = Util.convertStringToObject(userAgentStr, UserAgent.class);
            return userAgent;
        } catch (Exception ex) {

            return null;
        }
    }

    public static String getVisitId() {
        return visitIdHolder.get();
    }

}

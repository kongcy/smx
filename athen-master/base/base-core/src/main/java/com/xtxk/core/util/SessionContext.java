package com.xtxk.core.util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author kuangcy
 * @version 1.0
 * @ClassName SessionContext
 * @create: 2022-03-23 17:38
 */
public class SessionContext {

    private static SessionContext instance;

    private HashMap<String, HttpSession> sessionMap;

    private SessionContext() {
        sessionMap = new HashMap<>();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null && sessionMap.containsKey(session.getId())) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}

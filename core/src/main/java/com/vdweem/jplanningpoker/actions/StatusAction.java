package com.vdweem.jplanningpoker.actions;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.Result;
import com.vdweem.jplanningpoker.Util;
import com.vdweem.jplanningpoker.session.Session;
import com.vdweem.jplanningpoker.session.SessionManager;

/**
 * com.vdweem.jplanningpoker.actions.StatusAction
 *
 * Shows all users and the scores they entered.
 *
 * @author       Niels
 */
public class StatusAction {

    public Result execute() {
        Collection<Session> sessions = SessionManager.getSessions();
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Session session : sessions) {
            if (Util.isEmpty(session.getName())) continue;
            JSONObject user = new JSONObject();
            user.put("name", session.getName());
            user.put("score", session.getPoker() == null ? "" : session.getPoker());
            arr.put(user);
        }
        obj.put("users", arr);

        return new JSONResult(obj);
    }
}

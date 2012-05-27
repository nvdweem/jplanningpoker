package com.vdweem.jplanningpoker.actions;

import com.opensymphony.xwork2.Result;
import com.vdweem.jplanningpoker.session.Session;
import com.vdweem.jplanningpoker.session.SessionManager;

/**
 * com.vdweem.jplanningpoker.actions.ResetAction
 *
 * Resets the score of all users.
 *
 * @author       Niels
 */
public class ResetAction {

    public Result execute() {
        for (Session session : SessionManager.getSessions()) {
            session.setPoker(null);
        }
        return new JSONResult("success");
    }

}

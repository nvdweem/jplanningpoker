package com.vdweem.jplanningpoker.actions;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Result;
import com.vdweem.jplanningpoker.Util;

/**
 * com.vdweem.jplanningpoker.actions.AdminAction
 *
 * Determines if the user who did the request is the localhost. If so, the user is the admin user.
 * @author       Niels
 */
public class AdminAction {

    private static final String localv4 = "127.0.0.1";
    private static final String localv6 = "0:0:0:0:0:0:0:1";
    public Result execute() {
        return new JSONResult("admin", isAdmin() + "");
    }

    public static boolean isAdmin() {
        String remote = ServletActionContext.getRequest().getRemoteAddr();
        String local = ServletActionContext.getRequest().getLocalAddr();
        return localv4.equals(remote) || localv6.equals(remote) || Util.nullableEquals(local, remote);
    }
}

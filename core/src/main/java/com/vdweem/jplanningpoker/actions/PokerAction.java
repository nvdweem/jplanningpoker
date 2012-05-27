package com.vdweem.jplanningpoker.actions;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Result;
import com.vdweem.jplanningpoker.Util;
import com.vdweem.jplanningpoker.session.SessionManager;

/**
 * com.vdweem.jplanningpoker.actions.PokerAction
 *
 * Allows a user to change his poker card.
 *
 * @author       Niels
 */
public class PokerAction {
    private String number;

    /**
     * Determines if the request is a POST or a GET and executes the correct method.
     * @return
     */
    public Result execute() {
        if ("POST".equalsIgnoreCase(ServletActionContext.getRequest().getMethod()))
            return post();
        return get();
    }
    /**
     * Show the current poker action.
     * @return
     */
    public Result get() {
        Double poker = SessionManager.getSession().getPoker();
        String result;
        if (poker == null) result = "";
        else if (poker == -1) result = "?";
        else if (poker == -2) result = "Coffee";
        else if (poker == .5) result = "1/2";
        else result = poker.intValue() + "";
        return new JSONResult("poker", result);
    }
    /**
     * Changes the poker card for the current user.
     * @return
     */
    public Result post() {
        double card = 0;
        if ("1/2".equals(this.number.trim()))
            card = .5;
        else if ("coffee".equalsIgnoreCase(this.number.trim()))
            card = -2;
        else if ("?".equals(this.number.trim()))
            card = -1;
        else
            card = Util.parseLong(this.number);
        SessionManager.getSession().setPoker(card);
        return new JSONResult("ok");
    }
    /**
     * @param number The number to set.
     */
    public void setNumber(String number) {
        this.number = number;
    }

}

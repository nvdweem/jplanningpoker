package com.vdweem.jplanningpoker.session;

import java.util.Calendar;
import java.util.Date;

/**
 * com.vdweem.jplanningpoker.session.Session
 *
 * The name and score of a user. Also keeps track of the last activity to determine if
 * a user is still active.
 *
 * @author       Niels
 */
public class Session {
    public static final int INACTIVE_AFTER_MINUTES = 3;
    private Date lastAction;
    private String name;
    private Double poker;

    /**
     * Constructs the session and sets now as the last action time.
     */
    public Session() {
        touch();
    }

    /**
     * Sets the last action time to now.
     * @return
     */
    public Session touch() {
        this.lastAction = Calendar.getInstance().getTime();
        return this;
    }
    /**
     * Determines if this session is still active.
     * @return
     */
    public boolean isActive() {
        if (this.lastAction == null) return false;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -INACTIVE_AFTER_MINUTES);
        return c.getTime().before(this.lastAction);
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The name to set.
     */
    public Session setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return Returns the poker.
     */
    public Double getPoker() {
        return this.poker;
    }

    /**
     * @param poker The poker to set.
     */
    public Session setPoker(Double poker) {
        this.poker = poker;
        return this;
    }

}

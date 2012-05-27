package com.vdweem.jplanningpoker.session;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * com.vdweem.jplanningpoker.session.SessionInterceptor
 *
 * Makes sure all actions trigger an action in the SessionManager, and
 * that the SessionManager knows which user is performing the action.
 *
 * @author       Niels
 */
@SuppressWarnings("serial")
public class SessionInterceptor implements Interceptor {

    /**
     * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
     */
    public void destroy() {}

    /**
     * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
     */
    public void init() {}

    /**
     * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     */
    public String intercept(ActionInvocation invocation) throws Exception {
        SessionManager.init();
        return invocation.invoke();
    }

}

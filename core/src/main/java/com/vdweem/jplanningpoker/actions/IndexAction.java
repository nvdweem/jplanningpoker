package com.vdweem.jplanningpoker.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import com.opensymphony.xwork2.Result;

/**
 * com.vdweem.jplanningpoker.actions.IndexAction
 *
 * Redirects to the index.html page.
 * @author       Niels
 */
public class IndexAction {

    @Actions({
        @Action(""),
        @Action("index")
    })
    public Result execute() {
        return new ServletDispatcherResult("index.html");
    }

}

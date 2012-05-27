package com.vdweem.jplanningpoker.actions;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.vdweem.jplanningpoker.Util;

/**
 * com.vdweem.jplanningpoker.actions.JSONResult
 *
 * Result for JSON types.
 * @author       Niels
 */
@SuppressWarnings("serial")
public class JSONResult implements Result {

    private final String result;

    /**
     * JSON result for a JOSN object.
     * @param obj
     */
    public JSONResult(JSONObject obj) {
        String result = Util.nonNullString(obj);
        if (Util.isEmpty(result))
            result = "{}";
        this.result = result;
    }
    /**
     * JSON result for a JSON array.
     * @param arr
     */
    public JSONResult(JSONArray arr) {
        String result = Util.nonNullString(arr);
        if (Util.isEmpty(result))
            result = "[]";
        this.result = result;
    }
    /**
     * JSON result which will print the message in a JSON object in the following format:
     * {
     *   "message": [input string]
     * }
     * @param str
     */
    public JSONResult(String str) {
        JSONObject result = new JSONObject();
        result.put("message", str);
        this.result = result.toString();
    }
    /**
     * Constructs a JSON object and uses it for the result,
     * Odd numbered parameters are used as keys, the even numbered parameters are the values.
     * @param json
     */
    public JSONResult(String... json) {
        JSONObject result = new JSONObject();
        for (int i = 0; i < json.length / 2; i++)
            result.put(json[i*2], json[i*2+1]);
        this.result = result.toString();
    }

    /**
     * Prints the content type and the actual result.
     * @see com.opensymphony.xwork2.Result#execute(com.opensymphony.xwork2.ActionInvocation)
     */
    public void execute(ActionInvocation invocation) throws Exception {
        ServletActionContext.getResponse().setContentType("application/json");
        ServletActionContext.getResponse().getWriter().print(this.result);
    }

}

package com.vdweem.jplanningpoker.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StreamResult;

import com.opensymphony.xwork2.Result;

/**
 * com.vdweem.jplanningpoker.actions.QrcodeAction
 *
 * Generates a QR image which contains the url to the application for easy participation.
 *
 * @author       Niels
 */
public class QrcodeAction {

    /**
     * Generates the QR code image.
     * @return
     */
    public Result execute() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String url;
        try {
            url = (request.getProtocol().toLowerCase().contains("https") ? "https" : "http") + "://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getServerPort() + request.getContextPath();
        } catch (UnknownHostException e) {
            url = (request.getProtocol().toLowerCase().contains("https") ? "https" : "http") + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        }
        ByteArrayOutputStream out = QRCode.from(url).to(ImageType.PNG).withSize(800, 800).stream();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        StreamResult result = new StreamResult(in);
        result.setContentType("image/png");
        return result;
    }
}

package com.nic.nrlm_aajeevika.helper;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PageModel {

    private static int PAGE = 0;
    private static int SIZE = 10;
    private  int offset = 0;
    private  int limit = 20;
    private HttpServletRequest request;
    
    public PageModel(HttpServletRequest request) {
        this.request = request;
    }
    public void initPageAndSize(){
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            PAGE = Integer.parseInt(request.getParameter("page")) - 1;
        }else {
        	this.PAGE = 0;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
        	SIZE = Integer.parseInt(request.getParameter("size"));
        }
        
    }
    public void initOffsetAndLimit(){
        if (request.getParameter("offset") != null && !request.getParameter("offset").isEmpty()) {
            setOffset(Integer.parseInt(request.getParameter("offset")));
        }else {
        	this.setOffset(0);
        }
        if (request.getParameter("limit") != null && !request.getParameter("limit").isEmpty()) {
        	setLimit(Integer.parseInt(request.getParameter("limit")));
        }
        else {
        	setLimit(20);
        }
        
    }
    public static void setSIZE(int SIZE) {
        PageModel.SIZE = SIZE;
    }
    public static int getPAGE() {
        return PAGE;
    }
    public static int getSIZE() {
        return SIZE;
    }
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
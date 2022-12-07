package com.nic.nrlm_aajeevika.data;

import org.springframework.web.multipart.MultipartFile;

import com.nic.nrlm_aajeevika.request.TickerPopupSliderRequest;

import lombok.Data;

@Data
public class MyData {
private Object request;
private MultipartFile[] file;

public Object getRequest() {
	return request;
}
public void setRequest(Object request) {
	this.request = request;
}
public MultipartFile[] getFile() {
	return file;
}
public void setFile(MultipartFile[] file) {
	this.file = file;
}
public MyData(Object request, MultipartFile[] file) {
	super();
	this.request = request;
	this.file = file;
}



}

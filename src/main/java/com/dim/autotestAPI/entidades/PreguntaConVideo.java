package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.ConVideo;

public class PreguntaConVideo extends PreguntaConID implements ConVideo{

	private String videoURL;
	
	@Override
	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	
	@Override
	public Adjunto getAdjunto() {
		return Adjunto.video;
	}
	
	public static String extraerIdVideoYoutube(String url) {
		String respuesta = url;
	    if (url != null) {
	        if (url.contains("watch?v=")) {
	            String videoId = url.split("\\?v=")[1];
	            int ampersandPosition = videoId.indexOf("&");
	            if (ampersandPosition != -1) {
	                return videoId.substring(0, ampersandPosition);
	            }
	            respuesta = videoId;
	        } else if (url.contains("youtu.be/")) {
	            String videoId = url.split("youtu.be/")[1];
	            int ampersandPosition = videoId.indexOf("&");
	            if (ampersandPosition != -1) {
	                return videoId.substring(0, ampersandPosition);
	            }
	            respuesta = videoId;
	        }
	    }
	    return respuesta;
	}

}

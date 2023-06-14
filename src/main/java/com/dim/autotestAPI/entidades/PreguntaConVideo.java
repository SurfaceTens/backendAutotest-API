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

}

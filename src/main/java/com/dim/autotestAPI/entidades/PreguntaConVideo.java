package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.ConVideo;

public class PreguntaConVideo extends PreguntaConID implements ConVideo{

	private String videoURL;
	
	@Override
	public String getVideoURL() {
		return videoURL;
	}

}
